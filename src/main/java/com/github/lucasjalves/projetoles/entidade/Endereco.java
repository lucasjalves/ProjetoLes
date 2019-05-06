package com.github.lucasjalves.projetoles.entidade;

import javax.persistence.Entity;

@Entity
public class Endereco extends AbstractEndereco{

	public Endereco withId(Long id) {
		this.setId(id);
		return this;
	}
}
