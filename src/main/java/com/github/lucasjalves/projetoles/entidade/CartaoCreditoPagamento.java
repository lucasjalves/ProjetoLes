package com.github.lucasjalves.projetoles.entidade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class CartaoCreditoPagamento extends Entidade{

	private String valor;
	@OneToOne(cascade=CascadeType.PERSIST)
	private CartaoCredito cartao;
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public CartaoCredito getCartao() {
		return cartao;
	}
	public void setCartao(CartaoCredito cartao) {
		this.cartao = cartao;
	}
	
	
}
