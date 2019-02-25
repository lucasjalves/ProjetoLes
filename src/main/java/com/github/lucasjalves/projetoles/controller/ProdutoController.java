package com.github.lucasjalves.projetoles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProdutoController {
	@RequestMapping("")
	public ModelAndView home(ModelAndView modelView) {
		modelView.setViewName("home");
		return modelView;
	}

	@RequestMapping("/produto/cadastro")
	public ModelAndView paginaCadastroProduto(ModelAndView modelView) {
		modelView.setViewName("produto/cadastro");
		return modelView;
	}
	
	@RequestMapping("/produto/consulta")
	public ModelAndView paginaConsultaProduto(ModelAndView modelView) {
		modelView.setViewName("produto/consulta");
		return modelView;
	}
	
	@RequestMapping("/produto/alteracao")
	public ModelAndView paginaAlteracaoProduto(ModelAndView modelView) {
		modelView.setViewName("produto/alterar");
		return modelView;
	}
	
	@RequestMapping("/produto/detalhe")
	public ModelAndView paginaDetalheProduto(ModelAndView modelView) {
		modelView.setViewName("produto/detalhe");
		return modelView;
	}	
		
}
