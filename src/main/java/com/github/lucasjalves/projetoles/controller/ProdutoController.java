package com.github.lucasjalves.projetoles.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.enums.Categorias;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.service.ProdutoService;
@SuppressWarnings("unchecked")
@Controller
public class ProdutoController extends ControllerBase{
	
	@Autowired
	private ProdutoService service;
	
	@Autowired
	private ObjectMapper mapper;
	
	
	@RequestMapping("")
	public ModelAndView home(ModelAndView modelView) throws JsonProcessingException {
		modelView.setViewName("home");
		
		List<Produto> listaProdutos = 
				(List<Produto>) service.consultar(new Produto()).getEntidades();
		
		listaProdutos = listaProdutos
				.stream()
				.filter(produto -> produto.isAtivo())
				.collect(Collectors.toList());
				
		modelView.addObject("listaProdutos", mapper.writeValueAsString(listaProdutos));
		return modelView;
	}

	@RequestMapping("/produto/cadastro")
	public ModelAndView paginaCadastroProduto(ModelAndView modelView) {
		modelView.setViewName("produto/cadastro");
		modelView.addObject("categorias", Categorias.values());
		return modelView;
	}
	
	@RequestMapping("/produto/consulta")
	public ModelAndView paginaConsultaProduto(ModelAndView modelView) throws JsonProcessingException {
		List<Produto> produtos = (List<Produto>) service.consultar(new Produto()).getEntidades();
		modelView.setViewName("produto/consulta");
		modelView.addObject("produtos", mapper.writeValueAsString(produtos));
		modelView.addObject("categorias", Categorias.values());
		return modelView;
	}
	
	@RequestMapping("/produto/alteracao")
	public ModelAndView paginaAlteracaoProduto(ModelAndView modelView,@RequestParam Long id) throws Exception {
		Produto produto = buscarProduto(id);
		modelView.setViewName("produto/alterar");
		modelView.addObject("categorias", Categorias.values());
		modelView.addObject("produto", produto);
		return modelView;
	}
	

	@RequestMapping("/produto/detalhe")
	public ModelAndView paginaDetalheProduto(@RequestParam Long id) throws Exception {
		ModelAndView modelView = new ModelAndView();
		Produto produto = buscarProduto(id);
		httpSession.setAttribute("produto", produto);
		modelView.addObject("produto", produto);
		modelView.setViewName("produto/detalhe");
		return modelView;
	}	
	
	
	@ResponseBody
	@RequestMapping("/produto/cadastrar")
	public Resultado cadastrar(@ModelAttribute Produto produto) {
		return service.cadastrar(produto);
	}

	@ResponseBody
	@RequestMapping("/produto/alterar")
	public Resultado alterar(@ModelAttribute Produto produto) {
		return service.alterar(produto);
	}
	
	@ResponseBody
	@RequestMapping("/produto/desativar")
	public Resultado desativar(@RequestParam Long id) throws Exception {
		Produto produto = buscarProduto(id);
		produto.setAtivo(false);
		Resultado resultado = service.alterar(produto);
		if(resultado.getMensagem().isEmpty()) {
			return service.consultar(new Produto());
		}
		return resultado;
	}
	
	private Produto buscarProduto(Long id) throws Exception {
		Produto produto = new Produto();
		produto.setId(id);

		List<Produto> produtos = (List<Produto>) service.consultarPorId(produto).getEntidades();
		if(produtos.size() == 0) {
			throw new Exception("Produto não encontrado");
		}
		
		return produtos.get(0);
	}
}
