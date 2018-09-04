package com.github.lucasjalves.projetoles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.service.ProdutoService;

@Controller
@RequestMapping("/produto")
final class ProdutoController {

	private static final String PAGINA_CADASTRO_PRODUTO = "/produto/cadastrarProduto";
	private static final String PAGINA_CONSULTA_PRODUTO = "/produto/todosProdutosTabela";
	
	@Autowired
	private ProdutoService service;
	
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
	
	@RequestMapping("/cadastrar/efetivar")
	public String efetivarCadastro(@RequestBody Produto produto) {
		return "forward:/produto/paginaConsulta";
	}
}
