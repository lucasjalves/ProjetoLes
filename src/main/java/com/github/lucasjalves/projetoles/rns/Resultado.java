package com.github.lucasjalves.projetoles.rns;

import java.util.ArrayList;
import java.util.List;

public class Resultado {
	private List<Mensagem> mensagem =  new ArrayList<Mensagem>();
	private List<Object> entidades = new ArrayList<Object>();
	
	public List<Mensagem> getMensagem() {
		return mensagem;
	}
	public void setMensagem(List<Mensagem> mensagem) {
		this.mensagem = mensagem;
	}
	public List<Object> getEntidades() {
		return entidades;
	}
	public void setEntidades(List<Object> entidades) {
		this.entidades = entidades;
	}
	
}
