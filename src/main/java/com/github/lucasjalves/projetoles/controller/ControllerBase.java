package com.github.lucasjalves.projetoles.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.github.lucasjalves.projetoles.entidade.ItemCarrinho;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.util.CalculoUtil;

@Controller
class ControllerBase {
	
	@Autowired
	protected HttpSession httpSession;
	
	public Map<Long, ItemCarrinho> adicionarItemCarrinho(Produto produto, Integer quantidade) {
		Map<Long, ItemCarrinho> carrinho = 
				(Map<Long, ItemCarrinho>) httpSession.getAttribute("carrinho");
		
		if(carrinho == null) {
			carrinho = new HashMap<Long, ItemCarrinho>();
		}
				
		Set<Long> chaves = carrinho.keySet();
		if(chaves.contains(produto.getId())) {
			ItemCarrinho itemSelecionado = carrinho.get(produto.getId());
			itemSelecionado.setQuantidade(quantidade);
			itemSelecionado.setValorTotal(CalculoUtil.calcularValorTotal(produto.getPrecoVenda(), quantidade));
		}else {
			ItemCarrinho item = new ItemCarrinho();
			item.setProduto(produto);
			item.setQuantidade(quantidade);
			item.setValorTotal(CalculoUtil.calcularValorTotal(produto.getPrecoVenda(), quantidade));
			carrinho.put(produto.getId(), item);
		}
		httpSession.setAttribute("carrinho", carrinho);
		return carrinho;
	}
	
	public Integer getQuantidadeNoCarrinho(Produto produto) {
		Map<Long, ItemCarrinho> carrinho = 
				(Map<Long, ItemCarrinho>) httpSession.getAttribute("carrinho");
		
		if(carrinho == null) {
			return 0;
		}else {
			Set<Long> chaves = carrinho.keySet();
			if(chaves.contains(produto.getId())) {
				ItemCarrinho itemSelecionado = carrinho.get(produto.getId());
				return itemSelecionado.getQuantidade();
			}else {
				return 0;
			}
		}
		
	}
}
