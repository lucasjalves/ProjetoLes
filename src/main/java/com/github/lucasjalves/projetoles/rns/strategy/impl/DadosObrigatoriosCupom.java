package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.annotation.RegraNegocio;
import com.github.lucasjalves.projetoles.entidade.Cupom;
import com.github.lucasjalves.projetoles.rns.Mensagem;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;
import com.github.lucasjalves.projetoles.util.StringUtils;

@RegraNegocio(classe=Cupom.class, operacao={"SALVAR", "ALTERAR"})
public class DadosObrigatoriosCupom implements Strategy<Cupom> {

	private List<Mensagem> mensagens = new ArrayList<>();
	@Override
	public List<Mensagem> processar(Cupom cupom) {
		if(StringUtils.isNullOrEmpty(cupom.getCodigo())) {
			mensagens.add(new Mensagem("Código vazio"));
		}
		if(cupom.getValorDesconto() == null || cupom.getValorDesconto() <= 0.00) {
			mensagens.add(new Mensagem("Valor do desconto deve ser maior que 0"));
		}
		if(cupom.getDataVencimento() == null) {
			mensagens.add(new Mensagem("Data de vencimento vazia!"));
		}
		if(cupom.getValorDesconto() > 100.00) {
			mensagens.add(new Mensagem("O valor do desconto deve ser igual ou menor que 100%"));
		}
		
		return mensagens;
	}

}
