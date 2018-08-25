package com.github.lucasjalves.projetoles.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.github.lucasjalves.projetoles.enums.StatusChamado;

@Entity
public class Chamado extends Entidade implements Serializable {

	private static final long serialVersionUID = 5009011064214472741L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String assunto;
	private String dtInicio;
	
	@OneToMany
	private List<Produto> produtos = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private StatusChamado status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(String dtInicio) {
		this.dtInicio = dtInicio;
	}

	public StatusChamado getStatus() {
		return status;
	}

	public void setStatus(StatusChamado status) {
		this.status = status;
	}
	
	

}
