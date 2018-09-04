package com.github.lucasjalves.projetoles.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.github.lucasjalves.projetoles.enums.StatusCadastro;
import com.github.lucasjalves.projetoles.enums.TipoUsuario;

@Entity
public class Usuario extends Entidade implements Serializable{


	private static final long serialVersionUID = -578268814259016013L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String cpf;
	private String senha;
	private String email;
	private String username;
	private String nome;
	private String ramal;
	private String pontos;
	
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;
	
	@Enumerated(EnumType.STRING)
	private StatusCadastro statusCadastro;
	
	@OneToOne
	private Setor setor = new Setor();
	
	@OneToOne
	private Cargo cargo;
	
	@OneToOne
	private Departamento departamento = new Departamento();

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getPontos() {
		return pontos;
	}

	public void setPontos(String pontos) {
		this.pontos = pontos;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public StatusCadastro getStatusCadastro() {
		return statusCadastro;
	}

	public void setStatusCadastro(StatusCadastro statusCadastro) {
		this.statusCadastro = statusCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) 
			return false;
		if(!(o instanceof Usuario)) {
			return false;
		}else {
			Usuario u = (Usuario)o;
			if(u.getId() == this.id && u.getSenha().equals(this.senha) && u.getEmail().equals(this.email)
					&& u.getNome().equals(this.nome) && u.getUsername().equals(this.username) && u.getRamal().equals(this.ramal))
				return true;
			else
				return false;
		}
	}
	
	@Override
	public int hashCode() {
		int result = Long.hashCode(id);
		result = 31 * result + this.cpf.hashCode();
		result = 31 * result + this.senha.hashCode();
		result = 31 * result + this.email.hashCode();
		result = 31 * result + this.nome.hashCode();
		result = 31 * result + this.username.hashCode();
		result = 31 * result + this.ramal.hashCode();
		return result;
	}
	
	@Override
	public String toString() {
		return "{CPF: " + this.cpf + 
				"\nUsername: " + this.username + 
				"\nNome: " + this.nome + 
				"\nEmail: " + this.email +
				"\nRamal: " + this.ramal + 
				"\nID: " + this.id + "}";
	}
}
