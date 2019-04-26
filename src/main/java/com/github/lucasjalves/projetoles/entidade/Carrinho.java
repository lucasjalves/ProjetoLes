package com.github.lucasjalves.projetoles.entidade;

import java.util.HashSet;
import java.util.Set;

public class Carrinho implements Cloneable {

	Set<ItemCarrinho> itensCarrinho = new HashSet<>();
	private String total;
	private String frete;
	private String desconto;
	private String totalCompra;
	private Cupom cupom;
	private boolean statusCupom = true;
	
	public boolean isStatusCupom() {
		return statusCupom;
	}

	public void setStatusCupom(boolean statusCupom) {
		this.statusCupom = statusCupom;
	}

	public Set<ItemCarrinho> getItensCarrinho() {
		return itensCarrinho;
	}

	public void setItensCarrinho(Set<ItemCarrinho> itensCarrinho) {
		this.itensCarrinho = itensCarrinho;
	}
	
	public void addItem(ItemCarrinho itemCarrinho) {
		this.itensCarrinho.add(itemCarrinho);
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getFrete() {
		return frete;
	}

	public void setFrete(String frete) {
		this.frete = frete;
	}

	public String getDesconto() {
		return desconto;
	}

	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}

	public String getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(String totalCompra) {
		this.totalCompra = totalCompra;
	}

	public Cupom getCupom() {
		return cupom;
	}

	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}
	
	@Override
    public Object clone()throws CloneNotSupportedException{  
        return (Carrinho)super.clone();  
    }
}
