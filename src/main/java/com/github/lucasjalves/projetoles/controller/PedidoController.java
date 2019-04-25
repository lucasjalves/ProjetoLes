package com.github.lucasjalves.projetoles.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.lucasjalves.projetoles.entidade.Carrinho;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.Endereco;
import com.github.lucasjalves.projetoles.entidade.Pedido;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.helper.ClienteHelper;
import com.github.lucasjalves.projetoles.helper.EfetivacaoPedidoHelper;
import com.github.lucasjalves.projetoles.helper.EstoqueHelper;
import com.github.lucasjalves.projetoles.helper.PedidoHelper;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.util.CalculoUtil;

@Controller
@RequestMapping("/pedido")
public class PedidoController extends ControllerBase{

	private static final String PAGINA = "pedido/confirmacao";
	private static final String EFETIVACAO = "pedido/efetivacao";
	private static final String CONSULTA = "painel/iframes/pedidos";
	private static final String CONSULTA_ADMIN = "painel/admin/pedidos";
	
	@Autowired
	private Facade facade;
	
	@Autowired
	private PedidoHelper pedidoHelper;
	
	@Autowired
	private EstoqueHelper estoqueHelper;
	
	@ResponseBody
	@RequestMapping("/confirmar")
	public Resultado confirmar(@ModelAttribute Endereco endereco) throws Exception {
		Cliente cliente =
				(Cliente) httpSession.getAttribute("cliente");
		
		Carrinho carrinho =
				(Carrinho) httpSession.getAttribute("carrinho");
		
		if(cliente == null) {
			return new Resultado("Cliente não logado!");
		}
		if(carrinho == null) {
			return new Resultado("Carrinho inexistente!");
		}
		if(carrinho.getItensCarrinho().isEmpty()) {
			return new Resultado("Itens no carrinho vazios!");
		}
		Pedido pedido = pedidoHelper.gerarPedido(endereco, carrinho, cliente);
		Resultado resultado = facade.salvar(pedido);
		if(resultado.getMensagem().isEmpty()) {
			try {
				estoqueHelper.atualizarEstoque(carrinho);
			}catch(Exception e) {
				return new Resultado(e.getMessage());
			}
			
			Pedido p = (Pedido) resultado.getEntidades().get(0);
			cliente.getPedidos().add(pedido);
			resultado = facade.alterar(cliente);
			cliente.getPedidos().remove(pedido);
			
			httpSession.setAttribute("pedido", p);
			httpSession.removeAttribute("carrinho");
		}
		
		return resultado;
				
	}
	
	@RequestMapping("/confirmacao")
	public ModelAndView confirmacao(ModelAndView modelView, @RequestParam Long id) throws Exception {
		Pedido pedido = (Pedido)httpSession.getAttribute("pedido");
	
		Cliente cliente =
				(Cliente) httpSession.getAttribute("cliente");
		
		cliente = (Cliente) facade.consultar(cliente).getEntidades().get(0);
		if(pedido != null) {
			final Pedido pedidoClone = pedido;
			
			Optional<Pedido> pedidoConfirmado = cliente.getPedidos()
					.stream().filter(p -> p.equalsPedido(pedidoClone))
					.findFirst();
			
			if(!pedidoConfirmado.isPresent()) {
				throw new Exception("Pedido não encontrado");
			}
			
			pedido = pedidoConfirmado.get();
		}
		if(pedido == null) {
			pedido = (Pedido) facade.consultar(new Pedido().withId(id)).getEntidades().get(0);
		}
		httpSession.removeAttribute("pedido");
		modelView.addObject("pedido", pedido);
		modelView.addObject("cliente", cliente);
		modelView.addObject("creditoZerado", CalculoUtil.isValorZerado(cliente.getCreditoDisponivel()));
		return modelView;
	}
	
	@ResponseBody
	@RequestMapping("/efetivar")
	public Resultado efetivar(@RequestBody Pedido pedido) throws Exception {
		
		Cliente cliente =
				(Cliente) httpSession.getAttribute("cliente");
		
		Resultado resultado = 
				facade.consultar(pedido);
		
		Optional<Pedido> pedidoConsulta =
				(Optional<Pedido>) resultado.getEntidades().stream().findFirst();
		
		if(!pedidoConsulta.isPresent()) {
			return new Resultado("Pedido " + pedido.getId() + " não encontrado");
		}
		
		Pedido pedidoAEfetivar = pedidoConsulta.get();
		pedidoAEfetivar.setCartoes(pedido.getCartoes());
		pedido.setTotalCompra(pedidoConsulta.get().getTotalCompra());
		Resultado resultadoValidacao = 
				EfetivacaoPedidoHelper.validarPedido(pedido);
		
		if(!resultadoValidacao.getMensagem().isEmpty()) {
			return resultadoValidacao;
		}
		
		Pedido pedidoEfetivacao = 
				EfetivacaoPedidoHelper.setarDadosParaEfetivar(pedidoAEfetivar, pedido, cliente);
		
		cliente = ClienteHelper.atualizarCreditoUtilizado(pedidoAEfetivar, cliente);
		
		if(CalculoUtil.isValorZerado(pedidoAEfetivar.getCreditoUtilizado())) {
			return facade.alterar(pedidoEfetivacao);
		}
		
		Resultado resultadoAlteracaoCredito = facade.alterar(cliente);
		if(!resultadoAlteracaoCredito.getMensagem().isEmpty()) {
			return resultado;
		} 
			
		return facade.alterar(pedidoEfetivacao);
		
	}
	
	@RequestMapping("/efetivacao")
	public ModelAndView efetivacao(ModelAndView modelView, @RequestParam Long id) throws Exception {
		Resultado resultado = 
				facade.consultar(new Pedido().withId(id));
		
		Optional<Pedido> buscaPedido = 
				(Optional<Pedido>) resultado.getEntidades().stream().findFirst();
		
		if(!buscaPedido.isPresent()) {
			throw new Exception("Pedido " + id + " não encontrado");
		}
		
		modelView.addObject("pedido", buscaPedido.get());
		modelView.setViewName(EFETIVACAO);
		modelView.addObject("creditoZerado", CalculoUtil.isValorZerado(buscaPedido.get().getCreditoUtilizado()));
		return modelView;
	}
	
	@RequestMapping("/consulta")
	public ModelAndView consulta(ModelAndView modelView) throws JsonProcessingException {
		
		Cliente cliente =
				(Cliente) httpSession.getAttribute("cliente");
		
		cliente = (Cliente) facade.consultar(cliente).getEntidades().get(0);
		
		modelView.setViewName(CONSULTA);
		modelView.addObject("pedidos", mapper.writeValueAsString(cliente.getPedidos()));
		return modelView;
	}
	
	@RequestMapping("/consultaAdmin")
	public ModelAndView consultaAdmin(ModelAndView modelView) {
		modelView.setViewName(CONSULTA_ADMIN);
		return modelView;
	}
		
}
