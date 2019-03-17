package com.github.lucasjalves.projetoles.entidade;

import java.util.HashSet;
import java.util.Set;

public class Carrinho {

	Set<ItemCarrinho> itensCarrinho = new HashSet<>();

	public Set<ItemCarrinho> getItensCarrinho() {
		return itensCarrinho;
	}

	public void setItensCarrinho(Set<ItemCarrinho> itensCarrinho) {
		this.itensCarrinho = itensCarrinho;
	}
	
	public void addItem(ItemCarrinho itemCarrinho) {
		this.itensCarrinho.add(itemCarrinho);
	}
}
