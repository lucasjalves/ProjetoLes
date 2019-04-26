package com.github.lucasjalves.projetoles.entidade;

import javax.persistence.Entity;

@Entity
public class CartaoCreditoPagamento extends AbstractCartao{

	private String valor;
	private Long idCartao;
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Long getIdCartao() {
		return idCartao;
	}

	public void setIdCartao(Long idCartao) {
		this.idCartao = idCartao;
	}	
	
	
}
