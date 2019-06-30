package com.github.lucasjalves.projetoles.controller.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.lucasjalves.projetoles.controller.ControllerBase;
import com.github.lucasjalves.projetoles.entidade.Carrinho;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.Pedido;
import com.github.lucasjalves.projetoles.helper.EstoqueHelper;
import com.github.lucasjalves.projetoles.helper.PedidoHelper;
import com.github.lucasjalves.projetoles.rns.Resultado;

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
	
	@PostMapping("/cadastrar/{cpf}")
	public Resultado confirmar(@RequestBody Carrinho carrinho, @PathVariable String cpf) throws Exception {
		return facade.consultar(new Pedido());
//		Cliente cliente = (Cliente) facade.consultar(new Cliente().withCpf(cpf)).getEntidades().get(0);
//		Pedido pedido = pedidoHelper.gerarPedido(carrinho.getEndereco(), carrinho, cliente);
//		Resultado resultadoPedido = facade.salvar(pedido);
//		if(resultadoPedido.getMensagem().isEmpty()) {
//			estoqueHelper.atualizarEstoque(carrinho);
//			cliente.getPedidos().add(pedido);
//			Resultado resultadoInsercaoPedido = facade.alterar(cliente);
//			if(resultadoInsercaoPedido.getMensagem().isEmpty()) {
//				Cliente c = (Cliente) facade.consultar(new Cliente().withCpf(cpf)).getEntidades().get(0);
//				List<Pedido> p = c.getPedidos().stream().filter(pe -> pe.equalsPedido(pedido))
//					.collect(Collectors.toList());
//				
//				Resultado r = new Resultado();
//				r.setEntidades(p);
//				
//				return r;
//			} else {
//				return resultadoInsercaoPedido;
//			}
//		}
//		return resultadoPedido;
	}
}
