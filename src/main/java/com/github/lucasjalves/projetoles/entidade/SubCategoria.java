package com.github.lucasjalves.projetoles.entidade;

import javax.persistence.Entity;

@Entity
public class SubCategoria extends Entidade{

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
