package com.github.lucasjalves.projetoles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/servico")
public class ServicoController {

	private static final String PAGINA_CADASTOR_SERVICO = "servico/cadastrarServico";
	@RequestMapping("/paginaCadastro")
	public ModelAndView paginaCadastroServico(ModelAndView modelView) {
		modelView.setViewName(PAGINA_CADASTOR_SERVICO);
		return modelView;
	}
}
