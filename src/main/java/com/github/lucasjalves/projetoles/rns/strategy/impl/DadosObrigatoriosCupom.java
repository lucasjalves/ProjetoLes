package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.annotation.RegraNegocio;
import com.github.lucasjalves.projetoles.entidade.Cupom;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;
import com.github.lucasjalves.projetoles.util.StringUtils;

@RegraNegocio(classe=Cupom.class, operacao={"SALVAR", "ALTERAR"})
public class DadosObrigatoriosCupom implements Strategy {

	
	@Override
	public List<String> processar(Entidade entidade) {
		List<String> mensagens = new ArrayList<>();
		Cupom cupom = (Cupom)entidade;
		if(StringUtils.isNullOrEmpty(cupom.getCodigo())) {
			mensagens.add("Código vazio");
		}
		if(cupom.getValorDesconto() == null || cupom.getValorDesconto() <= 0.00) {
			mensagens.add("Valor do desconto deve ser maior que 0");
		}
		if(cupom.getDataVencimento() == null) {
			mensagens.add("Data de vencimento vazia!");
		}
		if(cupom.getValorDesconto() > 100.00) {
			mensagens.add("O valor do desconto deve ser igual ou menor que 100%");
		}
		
		return mensagens;
	}

}
