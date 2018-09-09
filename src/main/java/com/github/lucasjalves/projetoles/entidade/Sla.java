package com.github.lucasjalves.projetoles.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.github.lucasjalves.projetoles.enums.PrioridadeChamado;

@Entity
public class Sla extends Entidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private PrioridadeChamado prioridade;
	
	@OneToMany
	private List<Dias> resolucao = new ArrayList<>();
	
	@OneToMany
	private List<Dias> atendimento = new ArrayList<>();

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

	public PrioridadeChamado getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(PrioridadeChamado prioridade) {
		this.prioridade = prioridade;
	}

	public List<Dias> getResolucao() {
		return resolucao;
	}

	public void setResolucao(List<Dias> resolucao) {
		this.resolucao = resolucao;
	}

	public List<Dias> getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(List<Dias> atendimento) {
		this.atendimento = atendimento;
	}
	
	
}
