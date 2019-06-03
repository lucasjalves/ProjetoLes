package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;

public class QuantidadeEstoqueProduto implements Strategy{

	@Override
	public List<String> processar(Entidade entidade) {
		Produto produto = (Produto)entidade;
		List<String> mensagens = new ArrayList<>();
		if(produto.getEstoque() != null) {
			if(produto.getEstoque() < 0) {
				mensagens.add("A quantidade em estoque deve ser maior que 0");
			}
		}
		return mensagens;
	}

}
