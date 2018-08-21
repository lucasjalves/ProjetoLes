package com.github.lucasjalves.projetoles.entidade;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Telefone extends Entidade {

	@Id
	private long id;
	private String ddd;
	private String numero;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
	
}
