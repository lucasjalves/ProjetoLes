package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.entidade.Pedido;
import com.github.lucasjalves.projetoles.enums.StatusPedido;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;
import com.github.lucasjalves.projetoles.util.FormatadorDataUtil;

public class DadosObrigatoriosPedido implements Strategy {

	@Override
	public List<String> processar(Entidade entidade) {
		Pedido p = (Pedido)entidade;
		List<String> mensagens = new ArrayList<>();
		if(p.getStatus().equals(StatusPedido.SOLICITADO)) {
			if(p.getEndereco() == null) {
				mensagens.add("O pedido não pode ser efetivado sem um endereço");
			}
			if(p.getCupom() != null) {
				LocalDate dtVencimento = 
						FormatadorDataUtil.toLocalDate(p.getCupom().getDataVencimento());
				
				if(!p.getCupom().getStatus()) {
					mensagens.add("O cupom não está mais ativo");
				}
				
				if(!dtVencimento.isAfter(LocalDate.now())) {
					mensagens.add("O cupom está vencido");
				}
			}
			if(p.getItensPedido().isEmpty()) {
				mensagens.add("O pedido não pode ser efetivado sem produtos!");
			}
		}
		return mensagens;
	}

}
