package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.util.StringUtils;

import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;

public class ValorMedidasProduto implements Strategy{

	@Override
	public List<String> processar(Entidade entidade) {
		List<String> mensagens = new ArrayList<>();
		Produto produto = (Produto)entidade;
		if(!StringUtils.isNullOrEmpty(produto.getAltura())) {
			Double valor = Double.parseDouble(produto.getAltura());
			if(valor < 0.0) {
				mensagens.add("Altura deve ser maior que zero!");
			}
		}
		if(!StringUtils.isNullOrEmpty(produto.getComprimento())) {
			Double valor = Double.parseDouble(produto.getComprimento());
			if(valor < 0.0) {
				mensagens.add("Comprimento deve ser maior que zero!");
			}
		}			
		if(!StringUtils.isNullOrEmpty(produto.getLargura())) {
			Double valor = Double.parseDouble(produto.getLargura());
			if(valor < 0.0) {
				mensagens.add("Largura deve ser maior que zero!");
			}
		}
		if(!StringUtils.isNullOrEmpty(produto.getPeso())) {
			Double valor = Double.parseDouble(produto.getPeso());
			if(valor < 0.0) {
				mensagens.add("Peso deve ser maior que zero!");
			}			
		}
		return mensagens;
	}

}
