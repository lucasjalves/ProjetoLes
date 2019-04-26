package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.entidade.Endereco;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;
import com.github.lucasjalves.projetoles.util.StringUtils;

public class DadosObrigatoriosEndereco implements Strategy {

	@Override
	public List<String> processar(Entidade entidade) {
		List<String> mensagens = new ArrayList<>();
		Endereco endereco = 
				(Endereco)entidade;
		
		if(StringUtils.isNullOrEmpty(endereco.getBairro())) {
			mensagens.add("Bairro vazio!");
		}
		if(StringUtils.isNullOrEmpty(endereco.getCep())) {
			mensagens.add("Cep vazio!");
		}
		if(StringUtils.isNullOrEmpty(endereco.getCidade())) {
			mensagens.add("Cidade vazio!");
		}
		if(StringUtils.isNullOrEmpty(endereco.getNome())) {
			mensagens.add("Nome vazio!");
		}
		if(StringUtils.isNullOrEmpty(endereco.getNumero())) {
			mensagens.add("Número vazio!");
		}		
		if(StringUtils.isNullOrEmpty(endereco.getPais())) {
			mensagens.add("País vazio!");
		}
		if(StringUtils.isNullOrEmpty(endereco.getRua())) {
			mensagens.add("Rua vazia!");
		}
		if(StringUtils.isNullOrEmpty(endereco.getUf())) {
			mensagens.add("UF vazio!");
		}		
		return mensagens;
	}

}
