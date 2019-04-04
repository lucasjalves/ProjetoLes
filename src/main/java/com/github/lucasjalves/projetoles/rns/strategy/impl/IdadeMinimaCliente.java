package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;
import com.github.lucasjalves.projetoles.util.FormatadorDataUtil;
import com.github.lucasjalves.projetoles.util.StringUtils;

public class IdadeMinimaCliente implements Strategy {

	@Override
	public List<String> processar(Entidade entidade) {
		Cliente cliente = (Cliente)entidade;
		List<String> mensagens = new ArrayList<>();
		if(!StringUtils.isNullOrEmpty(cliente.getDtNascimento())) {
			if(cliente.getDtNascimento().length() < 10) {
				mensagens.add("Data de nascimento inválida!");
			}else {
				LocalDate dtNascimento = FormatadorDataUtil.toLocalDate(cliente.getDtNascimento());
				long diferenca = ChronoUnit.YEARS.between(dtNascimento, LocalDate.now());
				if(diferenca < 18) {
					mensagens.add("O cliente deve ter no mínimo 18 anos de idade");
				}
			}
		}
		
		return mensagens;
	}

}
