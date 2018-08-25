package com.github.lucasjalves.projetoles.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Usuario extends Entidade implements Serializable{


	private static final long serialVersionUID = -578268814259016013L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String cpf;
	private String senha;
	private String email;
	
	
	private String ramal;
	
	@OneToOne
	private Cargo cargo;
	
	@OneToOne
	private Setor setor;
	
	private String pontos;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public String getPontos() {
		return pontos;
	}

	public void setPontos(String pontos) {
		this.pontos = pontos;
	}
	
	
	

}
