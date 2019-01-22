package com.github.lucasjalves.projetoles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@RequestMapping("/cadastro")
	public ModelAndView paginaCadastroCliente(ModelAndView modelView) {
		modelView.setViewName("cliente/cadastro");
		return modelView;
	}
}
