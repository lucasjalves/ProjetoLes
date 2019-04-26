package com.github.lucasjalves.projetoles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/ticket")
@Controller
public class TicketController {
	private static final String CONSULTA = "painel/iframes/tickets";
	private static final String CONSULTA_ADMIN = "painel/admin/tickets";
	
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
