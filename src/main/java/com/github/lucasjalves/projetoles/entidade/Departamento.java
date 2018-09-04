package com.github.lucasjalves.projetoles.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Departamento extends Entidade{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nome;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Setor> setores = new ArrayList<>();
	
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
	
	@Override
	public boolean equals(Object o) {
		if(o == null) 
			return false;
		if(!(o instanceof Departamento))
			return false;
		
		Departamento departamento = (Departamento)o;
		if(departamento.getNome().equals(this.nome)) {
			return true;
		}else {
			return false;
		}
			
	}
	
	@Override
	public int hashCode() {
		int result = nome.hashCode();
		return result;
	}
	
}
