package com.github.lucasjalves.projetoles.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Departamento extends Entidade{

	@Id
	private long id;
	private String nome;
	
	@OneToMany
	private List<Setor> setores = new ArrayList<Setor>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Setor> getSetores() {
		return setores;
	}
	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}
	
	
}
