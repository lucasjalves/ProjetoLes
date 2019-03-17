package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;

public class QuantidadeEstoqueProdutoCarrinho implements Strategy{
 

	@Override
	public List<String> processar(Entidade produto) {
		List<String> mensagem = new ArrayList<>();
		Produto entidade = (Produto)produto;
		if(entidade.getEstoque() != null && entidade.getQuantidadeSelecionada() != null) {
			if(entidade.getQuantidadeSelecionada() > entidade.getEstoque()) {
				mensagem.add("Quantidade não disponível em estoque");
			}
		}
		return mensagem;
	}
	

}
