package com.github.lucasjalves.projetoles.entidade;

import javax.persistence.Entity;

@Entity
public class CartaoCredito extends AbstractCartao{

	public CartaoCredito withId(Long id) {
		this.setId(id);
		return this;
	}
}
