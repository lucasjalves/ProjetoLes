package com.github.lucasjalves.projetoles.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Sla extends Entidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nome;
	
	@OneToOne
	private HorasNegocio horasNegocio;
	
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

	public HorasNegocio getHorasNegocio() {
		return horasNegocio;
	}

	public void setHorasNegocio(HorasNegocio horasNegocio) {
		this.horasNegocio = horasNegocio;
	}


	
	
}
