package com.github.lucasjalves.projetoles.controller.rest;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.lucasjalves.projetoles.controller.ControllerBase;
import com.github.lucasjalves.projetoles.entidade.Carrinho;
import com.github.lucasjalves.projetoles.entidade.CartaoCredito;
import com.github.lucasjalves.projetoles.entidade.CartaoCreditoPagamento;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.Pedido;
import com.github.lucasjalves.projetoles.enums.StatusPedido;
import com.github.lucasjalves.projetoles.helper.EstoqueHelper;
import com.github.lucasjalves.projetoles.helper.PedidoHelper;
import com.github.lucasjalves.projetoles.helper.TicketHelper;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.util.CalculoUtil;

@RestController
@CrossOrigin
@RequestMapping("rest/pedido")
public class RestPedidoController extends ControllerBase{

	@Autowired
	private PedidoHelper pedidoHelper;
	
	@Autowired
	private EstoqueHelper estoqueHelper;
	
	@GetMapping("/getByClient/{cpf}")
	public Resultado getByClient(@PathVariable String cpf) {
		Cliente cli = (Cliente) facade.consultar(new Cliente().withCpf(cpf)).getEntidades().get(0);
		Resultado r = new Resultado();
		
		r.setEntidades(cli.getPedidos());
		return r;
	}
	
	@GetMapping("/todos")
	public Resultado consultarTodos() {
		Resultado resultado = this.facade.consultar(new Pedido());
		List<Pedido> lista = (List<Pedido>) resultado.getEntidades();
		lista = lista.stream()
				.sorted(Comparator.comparing(Pedido::getId))
				.collect(Collectors.toList());
		Resultado resultadoRetorno = new Resultado();
		resultadoRetorno.setEntidades(lista);
		return resultadoRetorno;
	}
	
	@PostMapping("/cadastrar/{cpf}")
	public Resultado confirmar(@RequestBody Carrinho carrinho, @PathVariable String cpf) throws Exception {
		//return facade.consultar(new Pedido());
		Cliente cliente = (Cliente) facade.consultar(new Cliente().withCpf(cpf)).getEntidades().get(0);
		Pedido pedido = pedidoHelper.gerarPedido(carrinho.getEndereco(), carrinho, cliente);
		Resultado resultadoPedido = facade.salvar(pedido);
		if(resultadoPedido.getMensagem().isEmpty()) {
			estoqueHelper.atualizarEstoque(carrinho);
			cliente.getPedidos().add(pedido);
			Resultado resultadoInsercaoPedido = facade.alterar(cliente);
			if(resultadoInsercaoPedido.getMensagem().isEmpty()) {
				Cliente c = (Cliente) facade.consultar(new Cliente().withCpf(cpf)).getEntidades().get(0);
				List<Pedido> p = c.getPedidos().stream().filter(pe -> pe.equalsPedido(pedido))
					.collect(Collectors.toList());
				
				Resultado r = new Resultado();
				r.setEntidades(p);
				
				return r;
			} else {
				return resultadoInsercaoPedido;
			}
		}
		return resultadoPedido;
	}
	
	@GetMapping("get/{id}")
	public Resultado getById(@PathVariable Long id) {
		return facade.consultar(new Pedido().withId(id));
	}
	
	@PostMapping("efetivar/{id}")
	public Resultado efetivar(@PathVariable Long id, @RequestBody List<CartaoCreditoPagamento> cartoes) {
		Resultado resultado = facade.consultar(new Pedido().withId(id));
		Pedido pedido = (Pedido) resultado.getEntidades().get(0);
		Resultado res = facade.consultar(new CartaoCredito());
		
		pedido = PedidoHelper.atualizarPedidoComCartoes(pedido, cartoes, (List<CartaoCredito>)res.getEntidades());
		return facade.alterar(pedido);
	}
	
	@GetMapping("/cancelar/{id}")
	public Resultado cancelar(@PathVariable Long id, @RequestParam Long idCliente) {
		Cliente cliente = (Cliente) facade.consultar(new Cliente().withId(idCliente)).getEntidades().get(0);
		try {
			atualizarDados(id, cliente);
			return new Resultado();
		} catch(Exception e) {
			return new Resultado(e.getMessage());
		}
	}
	private void atualizarDados(Long id, Cliente cliente) throws Exception {
		@SuppressWarnings("unchecked")
		Optional<Pedido> pedidoConsulta = 
				(Optional<Pedido>) facade.consultar(new Pedido().withId(id)).getEntidades()
				.stream()
				.findFirst();
		
		if(!pedidoConsulta.isPresent()) {
			throw new Exception("Pedido com não encontrado " +id);
		}
		Pedido pedido = pedidoConsulta.get();
		if(pedido.getStatus().equals(StatusPedido.PAGO)) {
			Double valor = TicketHelper.gerarCredito(pedido, cliente);
			cliente.setCreditoDisponivel(CalculoUtil.formatMoney(valor));
			Resultado r = facade.alterar(cliente);
			if(!r.getMensagem().isEmpty()) {
				throw new Exception(r.getMensagem().get(0));
			}
		}
		estoqueHelper.atualizarQuantidadeEstoque(pedido.getItensPedido());
		pedido.setStatus(StatusPedido.CANCELADO);
		Resultado r = facade.alterar(pedido);
		if(!r.getMensagem().isEmpty()) {
			throw new Exception(r.getMensagem().get(0));
		}		
	}
}

