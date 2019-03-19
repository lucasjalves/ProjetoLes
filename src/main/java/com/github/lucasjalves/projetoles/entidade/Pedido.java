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
	private String total;
	private String frete;
	private String desconto;
	private String totalCompra;
	private String hora;
	private Cupom cupom;
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

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
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

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	
	
	
	
}
