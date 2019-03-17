package com.github.lucasjalves.projetoles.helper;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.lucasjalves.projetoles.entidade.Carrinho;
import com.github.lucasjalves.projetoles.entidade.ItemCarrinho;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.util.CalculoUtil;

public  class CarrinhoHelper {
	private CarrinhoHelper() {
		
	}
	
	public static Carrinho adicionarItemCarrinho(Produto produto, Integer quantidade, Carrinho carrinho) {
		
		if(carrinho == null) {
			carrinho = new Carrinho();
		}
		ItemCarrinho itemCarrinho = new ItemCarrinho().withProduto(produto);
		Set<ItemCarrinho> itensCarrinho = carrinho.getItensCarrinho();
		
		if(itensCarrinho.contains(itemCarrinho)) {
			Iterator<ItemCarrinho> it = itensCarrinho.iterator();
			while(it.hasNext()) {
				ItemCarrinho item = it.next();
				if(item.equals(itemCarrinho)) {
					valorizarObjetoItemCarrinho(produto, item, quantidade);
				}
			}
		}else {
			itemCarrinho = valorizarObjetoItemCarrinho(produto, itemCarrinho, quantidade);
			carrinho.addItem(itemCarrinho);
		}

		return carrinho;
	}
	
	public static Integer getQuantidadeNoCarrinho(Produto produto, Carrinho carrinho) {
		
		if(carrinho == null) {
			return 0;
		}else {
			ItemCarrinho item = new ItemCarrinho().withProduto(produto);
			if(!carrinho.getItensCarrinho().contains(item)){
				return 0;
			}
			return carrinho.getItensCarrinho().stream()
				.filter(i -> item.equals(item))
				.collect(Collectors.toList())
				.get(0)
				.getQuantidade();
		}
	}
		
	private static ItemCarrinho valorizarObjetoItemCarrinho(Produto produto, ItemCarrinho itemCarrinho, Integer quantidade) {
		itemCarrinho.setQuantidade(quantidade);
		itemCarrinho.setValorTotal(CalculoUtil.calcularValorTotal(produto.getPrecoVenda(), quantidade));
		itemCarrinho.setProduto(produto);		
		return itemCarrinho;
	}
	public static Carrinho alterarQuantidadeItemCarrinho(Produto produto, Carrinho carrinho) {
		
		ItemCarrinho itemCarrinho = new ItemCarrinho().withProduto(produto);
		Set<ItemCarrinho> itensCarrinho = carrinho.getItensCarrinho();

		Iterator<ItemCarrinho> it = itensCarrinho.iterator();
		while (it.hasNext()) {
			ItemCarrinho item = it.next();
			if (item.equals(itemCarrinho)) {
				valorizarObjetoItemCarrinho(produto, item, produto.getQuantidadeSelecionada());
				break;
			}
		}
		
		return carrinho;
	}
}
