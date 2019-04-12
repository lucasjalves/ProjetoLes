package com.github.lucasjalves.projetoles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		
		Pedido pedido = pedidoHelper.gerarPedido(endereco, carrinho, cliente);
		Resultado resultado = facade.salvar(pedido);
		if(resultado.getMensagem().isEmpty()) {
			estoqueHelper.atualizarEstoque(carrinho);
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
	public ModelAndView confirmacao(ModelAndView modelView, @RequestParam Long id) {
		Pedido pedido = (Pedido)httpSession.getAttribute("pedido");
		
		Cliente cliente =
				(Cliente) httpSession.getAttribute("cliente");
		
		cliente = (Cliente) facade.consultar(cliente).getEntidades().get(0);
		if(pedido == null) {
			pedido = (Pedido) facade.consultar(new Pedido().withId(id)).getEntidades().get(0);
		}
		httpSession.removeAttribute("pedido");
		modelView.addObject("pedido", pedido);
		modelView.addObject("cliente", cliente);
		modelView.addObject("creditoZerado", CalculoUtil.isValorZerado(cliente.getCreditoDisponivel()));
		return modelView;
	}
	
	@RequestMapping("/efetivacao")
	public ModelAndView efetivacao(ModelAndView modelView) {
		modelView.setViewName(EFETIVACAO);
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
