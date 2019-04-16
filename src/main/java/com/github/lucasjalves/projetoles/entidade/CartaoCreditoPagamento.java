package com.github.lucasjalves.projetoles.entidade;

import javax.persistence.Entity;

@Entity
public class CartaoCreditoPagamento extends AbstractCartao{

	private String valor;

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}	
	
	
}
