package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.entidade.Cupom;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;
import com.github.lucasjalves.projetoles.util.DataUtil;

public class ValoresValidosCupom implements Strategy {

	@Override
	public List<String> processar(Entidade entidade) {
		Cupom cupom = (Cupom)entidade;
		List<String> mensagens = new ArrayList<>();
		if(cupom.getDataVencimento() != null) {
			try {
				LocalDate data = DataUtil.formatarData(cupom.getDataVencimento());
				if(data.isBefore(LocalDate.now())) {
					mensagens.add("A data de vencimento deve ser no futuro");
				}
			} catch (Exception e) {
				mensagens.add("Data inválida");
			}
		}
		if(cupom.getValorDesconto() != null) {
			if(cupom.getValorDesconto() > 100.0) {
				mensagens.add("O valor do desconto deve ser menor que 100%");
			}
			if(cupom.getValorDesconto() <= 0.0) {
				mensagens.add("O valor do desconto deve ser maior que 0.00%");
			}
		}
		return mensagens;
	}

}
