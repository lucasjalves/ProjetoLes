package com.github.lucasjalves.projetoles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.lucasjalves.projetoles.entidade.Carrinho;
import com.github.lucasjalves.projetoles.entidade.ItemCarrinho;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.helper.CarrinhoHelper;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController extends ControllerBase{


	@Autowired
	private Facade facade;
	
	@RequestMapping("")
	public ModelAndView carrinho() {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("carrinho/carrinho");
		modelView.addObject("carrinho", (Carrinho) httpSession.getAttribute("carrinho"));
		return modelView;
	}
	
	@ResponseBody
	@RequestMapping("/adicionar")
	public Resultado adicionar(@ModelAttribute Produto produto) throws Exception {
		
		Carrinho carrinhoSessao = 
				(Carrinho) httpSession.getAttribute("carrinho");		
		
		Integer quantidade = produto.getQuantidadeSelecionada() + 
				CarrinhoHelper.getQuantidadeNoCarrinho(produto, carrinhoSessao);
		
		Produto produtoCadastrado = 
				(Produto) facade.consultar(produto).getEntidades().get(0);

		
		produtoCadastrado.setQuantidadeSelecionada(quantidade);
		Resultado resultado = facade.consultar(produtoCadastrado);
		
		if(resultado.getMensagem().isEmpty()) {
			Carrinho c = CarrinhoHelper.adicionarItemCarrinho(produtoCadastrado, quantidade, carrinhoSessao);
			httpSession.setAttribute("carrinho", c);
		}
		
		return resultado;
		
	}
	
	@ResponseBody
	@RequestMapping("/alterar")
	public Resultado alterar(@ModelAttribute Produto produto) throws Exception {

		
		Produto produtoCadastrado = 
				(Produto) facade.consultar(produto).getEntidades().get(0);
		
		produtoCadastrado.setQuantidadeSelecionada(produto.getQuantidadeSelecionada());
		Resultado resultado = facade.consultar(produtoCadastrado);
		
		if(resultado.getMensagem().isEmpty()) {
			Carrinho carrinhoSessao = 
					(Carrinho) httpSession.getAttribute("carrinho");
			Carrinho carrinho = CarrinhoHelper.alterarQuantidadeItemCarrinho(produtoCadastrado,carrinhoSessao);
			httpSession.setAttribute("carrinho", carrinho);
		}
		
		return resultado;
		
	}
	
	@ResponseBody
	@RequestMapping("/remover")
	public Resultado remover(@ModelAttribute Produto produto) throws Exception {
		Carrinho carrinho = 
				(Carrinho) httpSession.getAttribute("carrinho");
		
		carrinho.getItensCarrinho().remove(new ItemCarrinho().withProduto(produto));
		
		httpSession.setAttribute("carrinho", carrinho);

		return new Resultado();
	}
}
