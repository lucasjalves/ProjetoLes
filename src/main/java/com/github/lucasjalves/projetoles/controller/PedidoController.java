package com.github.lucasjalves.projetoles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	private static final String PAGINA = "pedido/confirmacao";
	private static final String EFETIVACAO = "pedido/efetivacao";
	private static final String CONSULTA = "painel/iframes/pedidos";
	private static final String CONSULTA_ADMIN = "painel/admin/pedidos";
	
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
