package com.github.lucasjalves.projetoles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	private static final String PAGINA_CADASTRO_PRODUTO = "/produto/cadastrarProduto";
	private static final String PAGINA_CONSULTA_PRODUTO = "/produto/todosProdutosTabela";
	
	@RequestMapping("/paginaCadastro")
	public ModelAndView paginaCadastroProduto(ModelAndView modelView) {
		modelView.setViewName(PAGINA_CADASTRO_PRODUTO);
		return modelView;
	}
	
	@RequestMapping("/paginaConsulta")
	public ModelAndView paginaConsultaProdutos(ModelAndView modelView) {
		modelView.setViewName(PAGINA_CONSULTA_PRODUTO);
		return modelView;
	}
}
