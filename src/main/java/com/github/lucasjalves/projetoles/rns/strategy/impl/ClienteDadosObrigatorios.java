package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.annotation.RegraNegocio;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.rns.Mensagem;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;

@RegraNegocio(classe=Cliente.class, operacao={"SALVAR", "ALTERAR"})
public class ClienteDadosObrigatorios implements Strategy<Cliente> {

	private List<Mensagem> mensagem = new ArrayList<>();
	@Override
	public List<Mensagem> processar(Cliente entidade) {
		if(entidade.getCpfCnpj()== null || entidade.getCpfCnpj().isEmpty()) {
			mensagem.add(new Mensagem("CPF/CNPJ vazio!"));
		}
		
		if(entidade.getDtNascimento() == null || entidade.getDtNascimento().isEmpty()) {
			mensagem.add(new Mensagem("Data de nascimento vazia!"));
		}
		if(entidade.getNome() == null || entidade.getNome().isEmpty()) {
			mensagem.add(new Mensagem("Nome vazio!"));
		}
		
		if(entidade.getUsername() == null || entidade.getUsername().isEmpty()) {
			mensagem.add(new Mensagem("Username vazio!"));
		}
		
		if(entidade.getSenha() == null || entidade.getSenha().isEmpty()) {
			mensagem.add(new Mensagem("Senha vazia!"));
		}
		return mensagem;
	}

}
