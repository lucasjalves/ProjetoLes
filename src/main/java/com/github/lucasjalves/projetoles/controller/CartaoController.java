package com.github.lucasjalves.projetoles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cartao")
public class CartaoController {

	@RequestMapping("/consulta")
	public ModelAndView consulta(ModelAndView modelView) {
		modelView.setViewName("painel/iframes/cartoes");
		return modelView;
	}
	
}
