package com.github.lucasjalves.projetoles.entidade;

import javax.persistence.Entity;

@Entity
public class Caracteristicas extends Entidade{
	private String chave;
	private String valor;
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
}
