package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.annotation.RegraNegocio;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;
import com.github.lucasjalves.projetoles.util.StringUtils;

@RegraNegocio(classe=Cliente.class, operacao={"SALVAR", "ALTERAR"})
public class ClienteDadosObrigatorios implements Strategy {

	
	
	@Override
	public List<String> processar(Entidade cliente) {
		List<String> mensagem = new ArrayList<>();
		Cliente entidade = (Cliente)cliente;
		
		if(StringUtils.isNullOrEmpty(entidade.getCpfCnpj())) {
			mensagem.add("CPF/CNPJ vazio!");
		}
		
		if(entidade.getDtNascimento() == null) {
			mensagem.add("Data de nascimento vazia!");
		}
		if(StringUtils.isNullOrEmpty(entidade.getNome())) {
			mensagem.add("Nome vazio!");
		}
		
		if(StringUtils.isNullOrEmpty(entidade.getUsername())) {
			mensagem.add("Username vazio!");
		}
		if(StringUtils.isNullOrEmpty(entidade.getSenha())) {
			mensagem.add("Senha vazia!");
		}
		if(StringUtils.isNullOrEmpty(entidade.getEmail())) {
			mensagem.add("Email vazio!");
		}
		if(StringUtils.isNullOrEmpty(entidade.getGenero())) {
			mensagem.add("Genero vazio!");
		}		
		return mensagem;
	}

}
