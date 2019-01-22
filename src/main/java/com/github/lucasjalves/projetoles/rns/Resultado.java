package com.github.lucasjalves.projetoles.rns;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.entidade.Entidade;

final public class Resultado {
	private List<Mensagem> mensagem =  new ArrayList<>();
	private List<? extends Entidade> entidades = new ArrayList<>();
	
	public List<Mensagem> getMensagem() {
		return mensagem;
	}
	public void setMensagem(List<Mensagem> mensagem) {
		this.mensagem = mensagem;
	}
	public List<? extends Entidade> getEntidades() {
		return entidades;
	}
	public void setEntidades(List<? extends Entidade> entidades) {
		this.entidades = entidades;
	}
	
}
