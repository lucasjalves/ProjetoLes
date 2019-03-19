package com.github.lucasjalves.projetoles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.lucasjalves.projetoles.entidade.Carrinho;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.Endereco;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Controller
@RequestMapping("/pedido")
public class PedidoController extends ControllerBase{

	private static final String PAGINA = "pedido/confirmacao";
	private static final String EFETIVACAO = "pedido/efetivacao";
	private static final String CONSULTA = "painel/iframes/pedidos";
	private static final String CONSULTA_ADMIN = "painel/admin/pedidos";
	
	@Autowired
	private Facade facade;
	
	@RequestMapping("/confirmar")
	public Resultado confirmar(@ModelAttribute Endereco endereco) {
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
		
		
	}
	
	@RequestMapping("/confirmacao")
	public ModelAndView confirmacao(ModelAndView modelView) {
		modelView.setViewName(PAGINA);
		return modelView;
	}
	
	@RequestMapping("/efetivacao")
	public ModelAndView efetivacao(ModelAndView modelView) {
		modelView.setViewName(EFETIVACAO);
		return modelView;
	}
	
	@RequestMapping("/consulta")
	public ModelAndView consulta(ModelAndView modelView) {
		modelView.setViewName(CONSULTA);
		return modelView;
	}
	
	@RequestMapping("/consultaAdmin")
	public ModelAndView consultaAdmin(ModelAndView modelView) {
		modelView.setViewName(CONSULTA_ADMIN);
		return modelView;
	}
		
}
