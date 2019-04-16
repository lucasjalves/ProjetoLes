package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.entidade.CartaoCredito;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;
import com.github.lucasjalves.projetoles.util.DataUtil;
import com.github.lucasjalves.projetoles.util.StringUtils;

public class DadosObrigatoriosCartao implements Strategy{

	@Override
	public List<String> processar(Entidade entidade) {
		CartaoCredito cartao = 
				(CartaoCredito) entidade;
		
		List<String> mensagens = 
				new ArrayList<>();
		
		if(StringUtils.isNullOrEmpty(cartao.getBandeira())) {
			mensagens.add("Bandeira vazia!");
		}
		
		if(StringUtils.isNullOrEmpty(cartao.getCvv())) {
			mensagens.add("Código de verificação vazio!");
		}
		if(!StringUtils.isNullOrEmpty(cartao.getCvv())) {
			if(cartao.getCvv().length() < 3) {
				mensagens.add("CVV inválido");
			}
		}
		if(StringUtils.isNullOrEmpty(cartao.getDtVencimento())) {
			mensagens.add("Data vazia!");
		}
		if(!StringUtils.isNullOrEmpty(cartao.getDtVencimento())) {
			try {
				DataUtil.formatarData(cartao.getDtVencimento());
			} catch (Exception e) {
				mensagens.add("Data inválida!");
			}
		}
		if(StringUtils.isNullOrEmpty(cartao.getNumero())) {
			mensagens.add("Número do cartão vazio!");
		}
		if(!StringUtils.isNullOrEmpty(cartao.getNumero())) {
			if(cartao.getNumero().length() < 16) {
				mensagens.add("O cartão deve conter 16 digítos");
			}
		}
		return mensagens;
	}

}
