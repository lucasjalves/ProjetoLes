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
import javax.persistence.OneToOne;

import com.github.lucasjalves.projetoles.enums.PrioridadeChamado;
import com.github.lucasjalves.projetoles.enums.StatusChamado;

@Entity
public class Chamado extends Entidade implements Serializable {

	private static final long serialVersionUID = 5009011064214472741L;
	
	@OneToOne
	private Usuario responsavel;
	
	@OneToOne
	private Usuario solicitante;
	
	@OneToMany
	private List<Usuario> acompanhantes = new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String assunto;
	private String dtInicio;
	
	@OneToMany
	private List<Produto> produtos = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private StatusChamado status;
	
	@OneToMany
	private List<Servico> servicos = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private PrioridadeChamado prioridadeChamado;
	
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

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public Usuario getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Usuario solicitante) {
		this.solicitante = solicitante;
	}

	public List<Usuario> getAcompanhantes() {
		return acompanhantes;
	}

	public void setAcompanhantes(List<Usuario> acompanhantes) {
		this.acompanhantes = acompanhantes;
	}

	public PrioridadeChamado getPrioridadeChamado() {
		return prioridadeChamado;
	}

	public void setPrioridadeChamado(PrioridadeChamado prioridadeChamado) {
		this.prioridadeChamado = prioridadeChamado;
	}
	
	

}
