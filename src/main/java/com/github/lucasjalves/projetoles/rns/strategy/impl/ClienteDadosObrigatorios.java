package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.annotation.RegraNegocio;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.rns.Mensagem;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;
import com.github.lucasjalves.projetoles.util.StringUtils;

@RegraNegocio(classe=Cliente.class, operacao={"SALVAR", "ALTERAR"})
public class ClienteDadosObrigatorios implements Strategy<Cliente> {

	private List<Mensagem> mensagem = new ArrayList<>();
	@Override
	public List<Mensagem> processar(Cliente entidade) {
		if(StringUtils.isNullOrEmpty(entidade.getCpfCnpj())) {
			mensagem.add(new Mensagem("CPF/CNPJ vazio!"));
		}
		
		if(StringUtils.isNullOrEmpty(entidade.getDtNascimento())) {
			mensagem.add(new Mensagem("Data de nascimento vazia!"));
		}
		if(StringUtils.isNullOrEmpty(entidade.getNome())) {
			mensagem.add(new Mensagem("Nome vazio!"));
		}
		
		if(StringUtils.isNullOrEmpty(entidade.getUsername())) {
			mensagem.add(new Mensagem("Username vazio!"));
		}
		if(StringUtils.isNullOrEmpty(entidade.getSenha())) {
			mensagem.add(new Mensagem("Senha vazia!"));
		}
		if(StringUtils.isNullOrEmpty(entidade.getEmail())) {
			mensagem.add(new Mensagem("Email vazio!"));
		}
		if(StringUtils.isNullOrEmpty(entidade.getGenero())) {
			mensagem.add(new Mensagem("Genero vazio!"));
		}		
		return mensagem;
	}

}
