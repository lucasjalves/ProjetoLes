package com.github.lucasjalves.projetoles.entidade;

import java.util.List;

import com.github.lucasjalves.projetoles.enums.TipoTicket;

public class ConfirmacaoTicket {

	
	private TipoTicket tipoTicket;
	private List<ItemPedido> itensPedido;
	private Pedido pedido;
	public TipoTicket getTipoTicket() {
		return tipoTicket;
	}
	public void setTipoTicket(TipoTicket tipoTicket) {
		this.tipoTicket = tipoTicket;
	}
	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}
	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public ConfirmacaoTicket withTipoTicket(TipoTicket tipoTicket) {
		this.tipoTicket = tipoTicket;
		return this;
	}

	public ConfirmacaoTicket withItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
		return this;
	}
	
	public ConfirmacaoTicket withPedido(Pedido pedido) {
		this.pedido = pedido;
		return this;
	}
}
