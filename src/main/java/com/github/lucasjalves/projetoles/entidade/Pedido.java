package com.github.lucasjalves.projetoles.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.github.lucasjalves.projetoles.enums.StatusPedido;

@Entity
public class Pedido extends Entidade{
	@OneToMany(cascade=CascadeType.ALL)
	List<ItemPedido> itemsPedido = new ArrayList<>();

	private StatusPedido status;
	private String dtPedido;
	private String valorTotalPedido;
	private String frete;
	@OneToMany(cascade=CascadeType.ALL)
	private List<CartaoCreditoPagamento>  cartoes = new ArrayList<>();
	
	@OneToOne(cascade=CascadeType.ALL)
	private Endereco endereco = new Endereco();
	private String creditoUtilizado = "0";
	
	public List<ItemPedido> getItemsPedido() {
		return itemsPedido;
	}

	public void setItemsPedido(List<ItemPedido> itemsPedido) {
		this.itemsPedido = itemsPedido;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public String getDtPedido() {
		return dtPedido;
	}

	public void setDtPedido(String dtPedido) {
		this.dtPedido = dtPedido;
	}

	public String getValorTotalPedido() {
		return valorTotalPedido;
	}

	public void setValorTotalPedido(String valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}

	public String getFrete() {
		return frete;
	}

	public void setFrete(String frete) {
		this.frete = frete;
	}

	public List<CartaoCreditoPagamento> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<CartaoCreditoPagamento> cartoes) {
		this.cartoes = cartoes;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getCreditoUtilizado() {
		return creditoUtilizado;
	}

	public void setCreditoUtilizado(String creditoUtilizado) {
		this.creditoUtilizado = creditoUtilizado;
	}
	
	
	
	
	
}
