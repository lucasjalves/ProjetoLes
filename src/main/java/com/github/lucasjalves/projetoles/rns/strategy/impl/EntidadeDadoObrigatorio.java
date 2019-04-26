package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.annotation.RegraNegocio;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;

@RegraNegocio(classe=Entidade.class, operacao= {"ALTERAR"})
public class EntidadeDadoObrigatorio implements Strategy{


	@Override
	public List<String> processar(Entidade entidade) {
		List<String> mensagens = new ArrayList<>();
		if(entidade.getId() == 0) {
			mensagens.add("Id nulo!");
		}
		return mensagens;
	}

}
