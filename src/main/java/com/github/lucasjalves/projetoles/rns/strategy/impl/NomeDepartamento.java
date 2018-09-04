package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.annotation.RegraNegocio;
import com.github.lucasjalves.projetoles.entidade.Departamento;
import com.github.lucasjalves.projetoles.rns.Mensagem;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;

@RegraNegocio(classe = Departamento.class, operacao={"SALVAR", "ALTERAR"})
final public class NomeDepartamento implements Strategy<Departamento> {

	List<Mensagem> mensagens = new ArrayList<>();
	@Override
	public List<Mensagem> processar(Departamento departamento) {
		if(departamento.getNome().trim().equalsIgnoreCase("Departamento LES")) {
			mensagens.add(new Mensagem("O nome do departamento deve ser diferente de Departamento LES"));
		}
		return mensagens;
	}

}
