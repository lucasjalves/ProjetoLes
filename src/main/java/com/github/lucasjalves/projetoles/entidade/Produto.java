package com.github.lucasjalves.projetoles.entidade;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Produto extends AbstractProduto{

	@Transient
	private Integer quantidadeSelecionada;
	
	
	public Produto withAtivo(Boolean ativo) {
		this.setAtivo(ativo);
		return this;
	}

	public Produto withId(Long id) {
		this.setId(id);
		return this;
	}

	public Integer getQuantidadeSelecionada() {
		return quantidadeSelecionada;
	}

	public void setQuantidadeSelecionada(Integer quantidadeSelecionada) {
		this.quantidadeSelecionada = quantidadeSelecionada;
	}
	
	
	
}
