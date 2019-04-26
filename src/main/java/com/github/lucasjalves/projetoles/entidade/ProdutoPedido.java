package com.github.lucasjalves.projetoles.entidade;

import javax.persistence.Entity;

@Entity
public class ProdutoPedido extends AbstractProduto{
	private Long idProduto;

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	
}
