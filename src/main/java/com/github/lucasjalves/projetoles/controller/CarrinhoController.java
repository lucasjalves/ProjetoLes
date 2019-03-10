package com.github.lucasjalves.projetoles.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.lucasjalves.projetoles.entidade.ItemCarrinho;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.service.ProdutoService;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController extends ControllerBase{

	@Autowired
	private ProdutoService service;
	
	@RequestMapping("")
	public ModelAndView carrinho() {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("carrinho/carrinho");
		modelView.addObject("carrinho", (Map<Long, ItemCarrinho>) httpSession.getAttribute("carrinho"));
		return modelView;
	}
	
	@ResponseBody
	@RequestMapping("/adicionar")
	public Resultado adicionar(@ModelAttribute Produto produto) {
		Integer quantidade = produto.getQuantidadeSelecionada() + getQuantidadeNoCarrinho(produto);
		
		Produto produtoCadastrado = (Produto) service.consultarPorId(produto)
				.getEntidades()
				.get(0);
		
		produtoCadastrado.setQuantidadeSelecionada(quantidade);
		Resultado resultado = service.consultarPorId(produtoCadastrado);
		
		if(resultado.getMensagem().isEmpty()) {
			adicionarItemCarrinho(produtoCadastrado, quantidade);
		}
		
		return resultado;
		
	}
}
