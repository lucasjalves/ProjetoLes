package com.github.lucasjalves.projetoles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

	@RequestMapping("")
	public ModelAndView carrinho(ModelAndView modelView) {
		modelView.setViewName("carrinho/carrinho");
		return modelView;
	}
}
