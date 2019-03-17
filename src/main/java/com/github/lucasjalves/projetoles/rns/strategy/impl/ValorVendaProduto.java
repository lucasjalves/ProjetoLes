package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;
import com.github.lucasjalves.projetoles.util.StringUtils;

public class ValorVendaProduto implements Strategy {

	@Override
	public List<String> processar(Entidade entidade) {
		List<String> mensagens = new ArrayList<>();
		Produto produto = (Produto)entidade;
		if(!StringUtils.isNullOrEmpty(produto.getPrecoVenda()) && !StringUtils.isNullOrEmpty(produto.getPrecoCompra())) {
			try {
				Double valorVenda = StringUtils.StringToValor(produto.getPrecoVenda());
				Double valorCompra = StringUtils.StringToValor(produto.getPrecoCompra());
				if(valorCompra >= valorVenda) {
					mensagens.add("O valor de venda deve ser maior que o valor de compra!");
				}
				if(valorVenda <= 0.00) {
					mensagens.add("O valor de venda deve ser maior que zero!");
				}
				if(valorCompra < 0.00) {
					mensagens.add("O valor de compra deve ser um número maior que zero!");
				}
			} catch (Exception e) {
				mensagens.add("Valor de venda ou de compra invalidos!");
			}
		}
		return mensagens;
	}

}
