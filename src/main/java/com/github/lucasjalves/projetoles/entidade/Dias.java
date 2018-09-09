package com.github.lucasjalves.projetoles.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dias extends Entidade {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String nome;
	
	private String horasInicio;
	
	private String horasFim;

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

	public String getHorasInicio() {
		return horasInicio;
	}

	public void setHorasInicio(String horasInicio) {
		this.horasInicio = horasInicio;
	}

	public String getHorasFim() {
		return horasFim;
	}

	public void setHorasFim(String horasFim) {
		this.horasFim = horasFim;
	}
	
	
}

