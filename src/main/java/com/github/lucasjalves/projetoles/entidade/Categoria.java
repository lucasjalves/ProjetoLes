package com.github.lucasjalves.projetoles.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Categoria extends Entidade {

	private String nome;
	@OneToMany(cascade=CascadeType.ALL)
	private List<SubCategoria> subcategorias = new ArrayList<>();
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<SubCategoria> getSubcategorias() {
		return subcategorias;
	}
	public void setSubcategorias(List<SubCategoria> subcategorias) {
		this.subcategorias = subcategorias;
	}
	
	
}
