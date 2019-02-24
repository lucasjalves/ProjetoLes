package com.github.lucasjalves.projetoles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	private static final String PAGINA = "pedido/confirmacao";
	private static final String EFETIVACAO = "pedido/efetivacao";
	
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
}
