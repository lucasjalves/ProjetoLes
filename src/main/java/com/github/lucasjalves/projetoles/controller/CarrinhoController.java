package com.github.lucasjalves.projetoles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.lucasjalves.projetoles.entidade.Carrinho;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.Cupom;
import com.github.lucasjalves.projetoles.entidade.ItemCarrinho;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.helper.AtualizarCarrinhoHelper;
import com.github.lucasjalves.projetoles.helper.CarrinhoHelper;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController extends ControllerBase{


	@Autowired
	private Facade facade;
	
	@RequestMapping("")
	public ModelAndView carrinho() throws CloneNotSupportedException {
		Cliente cliente = (Cliente) httpSession.getAttribute("cliente");
		Carrinho carrinho = (Carrinho) httpSession.getAttribute("carrinho");
		if(carrinho != null) {
			List<Produto> produtos = (List<Produto>) facade.consultar(new Produto()).getEntidades();
			List<Cupom> lista = (List<Cupom>) facade.consultar(new Cupom()).getEntidades();
			carrinho = AtualizarCarrinhoHelper.atualizarSessaoInformacoes(carrinho, produtos, lista);
			Carrinho c =  AtualizarCarrinhoHelper.atualizarSessao((Carrinho)carrinho.clone());
			httpSession.setAttribute("carrinho", CarrinhoHelper.atualizarValores(c));
		}
		if(cliente != null) {
			cliente = (Cliente) facade.consultar(cliente).getEntidades().get(0);
		}
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("carrinho/carrinho");
		modelView.addObject("carrinho", carrinho);
		modelView.addObject("cliente", cliente);
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
		carrinho = CarrinhoHelper.atualizarValores(carrinho);
		
		httpSession.setAttribute("carrinho", carrinho);

		return new Resultado();
	}
	
	@ResponseBody
	@RequestMapping("/adicionarCupom")
	public Resultado adicionarCupom(@ModelAttribute Cupom cupom) throws Exception {
		
		Carrinho carrinho = 
				(Carrinho) httpSession.getAttribute("carrinho");
		
		if(carrinho == null) {
			carrinho = new Carrinho();
			httpSession.setAttribute("carrinho", new Carrinho());
		}
		Resultado resultado = 
				facade.consultar(cupom);
		
		cupom.setValidar(true);
		if(resultado.getEntidades().isEmpty()) {
			carrinho.setCupom(null);
			httpSession.setAttribute("carrinho", carrinho);
			return facade.consultar(cupom);
		}
		else {
			Cupom c = (Cupom) resultado.getEntidades().get(0);
			c.setValidar(true);
			resultado = facade.consultar(c);
			if(resultado.getMensagem().isEmpty()) {
				carrinho.setCupom(c.withValidar(false));
				carrinho = CarrinhoHelper.atualizarValores(carrinho);
				httpSession.setAttribute("carrinho", carrinho);
			}
			return resultado;
		}
	}
	
	@ResponseBody
	@RequestMapping("/removerCupom")
	public Resultado removerCupom() throws Exception {
		
		Carrinho carrinho = 
				(Carrinho) httpSession.getAttribute("carrinho");
		
		carrinho.setCupom(null);
		carrinho = CarrinhoHelper.atualizarValores(carrinho);
		httpSession.setAttribute("carrinho", carrinho);
		
		return new Resultado();
	}
}
