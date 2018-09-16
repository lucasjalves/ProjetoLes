package com.github.lucasjalves.projetoles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/horasNegocio")
public class HorasNegocioController {

	private static final String PAGINA_CADASTRO_HORAS_NEGOCIO = "/horasNegocio/cadastrarHorasNegocio";
	
	@RequestMapping("/cadastrar")
	public ModelAndView paginaCadastro(ModelAndView modelView) {
		modelView.setViewName(PAGINA_CADASTRO_HORAS_NEGOCIO);
		return modelView;
	}
}
