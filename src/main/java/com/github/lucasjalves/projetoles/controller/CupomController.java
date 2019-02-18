package com.github.lucasjalves.projetoles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cupom")
public class CupomController {

	@RequestMapping("/cadastro")
	public ModelAndView paginaCadastroProduto(ModelAndView modelView) {
		modelView.setViewName("cupom/cadastro");
		return modelView;
	}
	
	@RequestMapping("/consulta")
	public ModelAndView paginaConsultaProduto(ModelAndView modelView) {
		modelView.setViewName("cupom/consulta");
		return modelView;
	}
	
	@RequestMapping("/alteracao")
	public ModelAndView paginaAlteracaoProduto(ModelAndView modelView) {
		modelView.setViewName("cupom/alterar");
		return modelView;
	}
}
