package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.lucasjalves.projetoles.annotation.RegraNegocio;
import com.github.lucasjalves.projetoles.entidade.Departamento;
import com.github.lucasjalves.projetoles.rns.Mensagem;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;

@RegraNegocio(classe = Departamento.class, operacao = {"SALVAR", "ALTERAR"})
public class SetoresUnicosDepartamento implements Strategy<Departamento>{
	private List<Mensagem> mensagens = new ArrayList<>();
	
	@Override
	public List<Mensagem> processar(Departamento departamento) {
		Set<String> nomes = new HashSet<>();
		departamento.getSetores().forEach(dp -> {
			if(!nomes.contains(dp.getNome())) {
				nomes.add(dp.getNome());
			}
		});
		
		if(nomes.size() != departamento.getSetores().size()) {
			mensagens.add(new Mensagem("Setores devem conter nomes diferentes"));
		}
		return mensagens;
	}

}
