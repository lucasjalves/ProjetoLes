package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.entidade.Cupom;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;
import com.github.lucasjalves.projetoles.util.DataUtil;

public class CupomValido implements Strategy{

	@Override
	public List<String> processar(Entidade entidade) {
		List<String> mensagens = new ArrayList<>();
		Cupom cupom = (Cupom)entidade;
		if(cupom.isValidar()) {
			if(cupom.getId() == null) {
				mensagens.add("Cupom inexistente!");
				return mensagens;
			}
			
			if(!cupom.getStatus()) {
				mensagens.add("Cupom desativado!");
			}
			try {
				LocalDate dataCupom =
						DataUtil.formatarData(cupom.getDataVencimento());
				if(!dataCupom.isAfter(LocalDate.now())) {
					mensagens.add("Cupom vencido!");
				}
			} catch (Exception e) {
				mensagens.add("Data inválida!");
			}
			
		}
		
		return mensagens;
	}

}
