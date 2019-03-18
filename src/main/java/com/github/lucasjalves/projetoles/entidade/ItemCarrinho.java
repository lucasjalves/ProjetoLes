package com.github.lucasjalves.projetoles.entidade;

public class ItemCarrinho {
	private Integer quantidade;
	private Produto produto = new Produto();
	private String valorTotal;
	private boolean status = true;
	private boolean quantidadeAtualizada;
	
	public ItemCarrinho withProduto(Produto produto) {
		this.produto = produto;
		return this;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public String getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
	
	public boolean isQuantidadeAtualizada() {
		return quantidadeAtualizada;
	}
	public void setQuantidadeAtualizada(boolean quantidadeAtualizada) {
		this.quantidadeAtualizada = quantidadeAtualizada;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemCarrinho other = (ItemCarrinho) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}
	
	
}
