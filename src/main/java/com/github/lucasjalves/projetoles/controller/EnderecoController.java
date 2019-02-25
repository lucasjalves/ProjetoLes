package com.github.lucasjalves.projetoles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/endereco")
public class EnderecoController {

	@RequestMapping("/consulta")
	public ModelAndView consulta(ModelAndView modelView) {
		modelView.setViewName("painel/iframes/enderecos");
		return modelView;
	}
}
