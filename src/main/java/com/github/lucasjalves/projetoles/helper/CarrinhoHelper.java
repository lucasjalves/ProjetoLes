package com.github.lucasjalves.projetoles.helper;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.lucasjalves.projetoles.entidade.Carrinho;
import com.github.lucasjalves.projetoles.entidade.ItemCarrinho;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.util.CalculoUtil;
import com.github.lucasjalves.projetoles.util.FreteUtil;

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
					valorizarObjetoItemCarrinho(produto, item, quantidade, carrinho);
				}
			}
		}else {
			carrinho.addItem(itemCarrinho);
			itemCarrinho = valorizarObjetoItemCarrinho(produto, itemCarrinho, quantidade, carrinho);			
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
		
	public static ItemCarrinho valorizarObjetoItemCarrinho(Produto produto, ItemCarrinho itemCarrinho, Integer quantidade, Carrinho carrinho) {
		itemCarrinho.setQuantidade(quantidade);
		itemCarrinho.setValorTotal(CalculoUtil.calcularValorTotal(produto.getPrecoVenda(), quantidade));
		itemCarrinho.setProduto(produto);	
		Double total = 0.00;
		Double frete = 0.00;
		Double desconto = 0.00;
		Iterator<ItemCarrinho> it= carrinho.getItensCarrinho().iterator();
		while(it.hasNext()) {
			ItemCarrinho item = it.next();
			total = total + (CalculoUtil.StringToDouble(item.getProduto().getPrecoVenda())) * item.getQuantidade();
			frete = frete + FreteUtil.calcularFrete(item.getProduto(),  item.getQuantidade());
		}
		if(carrinho.getCupom() != null) {
			if(carrinho.isStatusCupom()) {
				desconto = carrinho.getCupom().getValorDesconto() / 100;
				desconto = desconto * total;				
			}
		}
		carrinho.setTotal(String.format("%,.2f", total));
		carrinho.setFrete(String.format("%,.2f", frete));
		carrinho.setDesconto(String.format("%,.2f", desconto));
		carrinho.setTotalCompra(String.format("%,.2f", (total - desconto) + frete));
		return itemCarrinho;
	}
	
	public static Carrinho atualizarValores(Carrinho carrinho) {
		Double total = 0.00;
		Double frete = 0.00;
		Double desconto = 0.00;
		Iterator<ItemCarrinho> it= carrinho.getItensCarrinho().iterator();
		while(it.hasNext()) {
			ItemCarrinho item = it.next();
			total = total + (CalculoUtil.StringToDouble(item.getProduto().getPrecoVenda())) * item.getQuantidade();
			frete = frete + FreteUtil.calcularFrete(item.getProduto(), item.getQuantidade());
		}
		if(carrinho.getCupom() != null) {
			if(carrinho.isStatusCupom()) {
				desconto = carrinho.getCupom().getValorDesconto() / 100;
				desconto = desconto * total;				
			}
		}
		carrinho.setTotal(String.format("%,.2f", total));
		carrinho.setFrete(String.format("%,.2f", frete));
		carrinho.setDesconto(String.format("%,.2f", desconto));
		carrinho.setTotalCompra(String.format("%,.2f", (total - desconto) + frete));
		
		return carrinho;
	}
	public static Carrinho alterarQuantidadeItemCarrinho(Produto produto, Carrinho carrinho) {
		
		ItemCarrinho itemCarrinho = new ItemCarrinho().withProduto(produto);
		Set<ItemCarrinho> itensCarrinho = carrinho.getItensCarrinho();

		Iterator<ItemCarrinho> it = itensCarrinho.iterator();
		while (it.hasNext()) {
			ItemCarrinho item = it.next();
			if (item.equals(itemCarrinho)) {
				valorizarObjetoItemCarrinho(produto, item, produto.getQuantidadeSelecionada(), carrinho);
				break;
			}
		}
		
		return carrinho;
	}
}
