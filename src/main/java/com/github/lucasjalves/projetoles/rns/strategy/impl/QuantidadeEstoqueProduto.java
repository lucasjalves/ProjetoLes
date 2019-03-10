package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.annotation.RegraNegocio;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.rns.Mensagem;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;

@RegraNegocio(classe=Produto.class, operacao={"CONSULTAR"})
public class QuantidadeEstoqueProduto implements Strategy<Produto>{

	private List<Mensagem> mensagem = new ArrayList<>();

	@Override
	public List<Mensagem> processar(Produto entidade) {
		if(entidade.getEstoque() != null && entidade.getQuantidadeSelecionada() != null) {
			if(entidade.getQuantidadeSelecionada() > entidade.getEstoque()) {
				mensagem.add(new Mensagem("Quantidade não disponível em estoque"));
			}
		}
		return mensagem;
	}
	

}
