package com.github.lucasjalves.projetoles.entidade;

import javax.persistence.Entity;

@Entity
public class Cupom extends AbstractCupom{
	public Cupom withId(Long id) {
		this.setId(id);
		return this;
	}
	
	public Cupom withValidar(boolean validar) {
		this.setValidar(validar);
		return this;
	}
}
