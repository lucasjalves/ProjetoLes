package com.github.lucasjalves.projetoles.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.github.lucasjalves.projetoles.enums.StatusPedido;

@Entity
public class Pedido extends Entidade implements Cloneable{
	@OneToMany(cascade=CascadeType.ALL)
	List<ItemPedido> itensPedido = new ArrayList<>();

	private StatusPedido status;
	private String dtPedido;
	private String total;
	private String frete;
	private String desconto;
	private String totalCompra;
	private String hora;
	@OneToOne(cascade=CascadeType.ALL)
	private CupomPedido cupomPedido;
	@OneToMany(cascade=CascadeType.ALL)
	private List<CartaoCreditoPagamento>  cartoes = new ArrayList<>();
	
	@OneToOne(cascade=CascadeType.ALL)
	private EnderecoPedido endereco;
	private String creditoUtilizado = "0";
	
	public Pedido withId(Long id) {
		this.setId(id);
		return this;
	}
	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
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

	public EnderecoPedido getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoPedido endereco) {
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

	public CupomPedido getCupomPedido() {
		return cupomPedido;
	}

	public void setCupomPedido(CupomPedido cupomPedido) {
		this.cupomPedido = cupomPedido;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	@Override
    public Object clone()throws CloneNotSupportedException{  
        return (Pedido)super.clone();  
    }
	
	
	
}
