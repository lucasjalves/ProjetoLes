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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}
	
}
