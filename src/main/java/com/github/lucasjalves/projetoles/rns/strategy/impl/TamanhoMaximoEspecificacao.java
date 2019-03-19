package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;

public class TamanhoMaximoEspecificacao implements Strategy{

	@Override
	public List<String> processar(Entidade entidade) {
		List<String> mensagens = new ArrayList<>();
		Produto produto = (Produto)entidade;
		if(produto.getEspecificacoes().length() > 200) {
			mensagens.add("O campo especificações não pode passar de 200 caracteres");
		}
		return mensagens;
	}

}
