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
	private String hora;
	@OneToMany(cascade=CascadeType.ALL)
	private List<ItemPedidoTicket> itens = new ArrayList<>();
	private StatusTicket status;
	private TipoTicket tipo;
	private Long idCliente;
	private Long idPedido;
	private String obs;
	
	public Ticket withId(Long id) {
		this.setId(id);
		return this;
	}
	public String getDtPedido() {
		return dtPedido;
	}
	public void setDtPedido(String dtPedido) {
		this.dtPedido = dtPedido;
	}
	public List<ItemPedidoTicket> getItens() {
		return itens;
	}
	public void setItens(List<ItemPedidoTicket> itens) {
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

	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	
}
