package com.github.lucasjalves.projetoles.rns;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.entidade.Entidade;

final public class Resultado {
	public Resultado(String... mensagens) {
		for(String m: mensagens) {
			this.mensagem.add(m);
		}
	}
	private List<String> mensagem =  new ArrayList<>();
	private List<? extends Entidade> entidades = new ArrayList<>();
	
	public List<String> getMensagem() {
		return mensagem;
	}
	public void setMensagem(List<String> mensagem) {
		this.mensagem = mensagem;
	}
	public List<? extends Entidade> getEntidades() {
		return entidades;
	}
	public void setEntidades(List<? extends Entidade> entidades) {
		this.entidades = entidades;
	}
	
}
