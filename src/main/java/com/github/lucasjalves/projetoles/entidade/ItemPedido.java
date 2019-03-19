package com.github.lucasjalves.projetoles.entidade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class ItemPedido extends Entidade{
	private Integer quantidade;
	
	@OneToOne(cascade=CascadeType.ALL)
	private ProdutoPedido produto = new ProdutoPedido();
	private String valorTotal;
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public ProdutoPedido getProduto() {
		return produto;
	}
	public void setProduto(ProdutoPedido produto) {
		this.produto = produto;
	}
	public String getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public ItemPedido withProduto(Produto produto) {
		this.produto = (ProdutoPedido)produto;
		return this;
	}
	
	public ItemPedido withQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
		return this;
	}
	
	public ItemPedido withValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
		return this;
	}
}
