package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.entidade.Ticket;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;
import com.github.lucasjalves.projetoles.util.StringUtils;

public class DadosObrigatoriosTicket implements Strategy{

	@Override
	public List<String> processar(Entidade entidade) {
		Ticket t = (Ticket)entidade;
		List<String> mensagens = new ArrayList<>();
		if(t.getItens().isEmpty()) {
			mensagens.add("Um ticket não pode ser gerado sem itens para trocar");
		}
		if(t.getIdPedido() == null) {
			mensagens.add("Um ticket não pode ser gerado sem estar atrelado há um pedido");
		}
		if(t.getTipo() == null) {
			mensagens.add("Um ticket não pode ser gerador sem ter um tipo");
		}
		if(t.getStatus() == null) {
			mensagens.add("Um ticket não pode ser gerador sem ter um status");
		}
		if(StringUtils.isNullOrEmpty(t.getDtPedido())) {
			mensagens.add("Um ticket não pode ser gerado sem data");
		}
		if(StringUtils.isNullOrEmpty(t.getHora())) {
			mensagens.add("Um ticket não pode ser gerado sem hora");
		}		
		return mensagens;
	}

}
