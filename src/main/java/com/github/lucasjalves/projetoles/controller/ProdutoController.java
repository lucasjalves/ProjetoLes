package com.github.lucasjalves.projetoles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@RequestMapping("/cadastro")
	public ModelAndView paginaCadastroProduto(ModelAndView modelView) {
		modelView.setViewName("produto/cadastro");
		return modelView;
	}
	
	@RequestMapping("/consulta")
	public ModelAndView paginaConsultaProduto(ModelAndView modelView) {
		modelView.setViewName("produto/consulta");
		return modelView;
	}
	
	@RequestMapping("/alteracao")
	public ModelAndView paginaAlteracaoProduto(ModelAndView modelView) {
		modelView.setViewName("produto/alterar");
		return modelView;
	}
		
}
