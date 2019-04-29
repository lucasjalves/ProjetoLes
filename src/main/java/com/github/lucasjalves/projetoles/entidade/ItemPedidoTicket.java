package com.github.lucasjalves.projetoles.entidade;

import javax.persistence.Entity;

@Entity
public class ItemPedidoTicket extends Entidade {

	private Integer quantidade;
	private Long idItem;

	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Long getIdItem() {
		return idItem;
	}
	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	

	
	
}
