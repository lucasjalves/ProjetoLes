package com.github.lucasjalves.projetoles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.lucasjalves.projetoles.service.SlaService;

@Controller
@RequestMapping("/sla")
public class SlaController {

	private static final String PAGINA_CADASTRO_SLA = "/sla/cadastrarSla";
	
	@Autowired
	private SlaService service;
	
	@RequestMapping("/cadastrar")
	public ModelAndView abrirPaginaCadastroSla(ModelAndView modelView) {
		modelView.setViewName(PAGINA_CADASTRO_SLA);
		return modelView;
	}
}
