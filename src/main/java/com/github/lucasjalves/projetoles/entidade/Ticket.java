package com.github.lucasjalves.projetoles.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.github.lucasjalves.projetoles.enums.StatusTicket;
import com.github.lucasjalves.projetoles.enums.TipoTicket;

@Entity
public class Ticket extends Entidade {

	private String dtPedido;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();
	private StatusTicket status;
	private TipoTicket tipo;
	public String getDtPedido() {
		return dtPedido;
	}
	public void setDtPedido(String dtPedido) {
		this.dtPedido = dtPedido;
	}
	public List<ItemPedido> getItens() {
		return itens;
	}
	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
	public StatusTicket getStatus() {
		return status;
	}
	public void setStatus(StatusTicket status) {
		this.status = status;
	}
	public TipoTicket getTipo() {
		return tipo;
	}
	public void setTipo(TipoTicket tipo) {
		this.tipo = tipo;
	}
	
	
}
