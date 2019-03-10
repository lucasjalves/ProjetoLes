package com.github.lucasjalves.projetoles.entidade;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

	List<ItemCarrinho> listaItemCarrinho = new ArrayList<>();

	public List<ItemCarrinho> getListaItemCarrinho() {
		return listaItemCarrinho;
	}

	public void setListaItemCarrinho(List<ItemCarrinho> listaItemCarrinho) {
		this.listaItemCarrinho = listaItemCarrinho;
	}
}
