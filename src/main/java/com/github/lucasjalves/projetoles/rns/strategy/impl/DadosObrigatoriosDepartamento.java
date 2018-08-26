package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.lucasjalves.projetoles.annotation.RegraNegocio;
import com.github.lucasjalves.projetoles.entidade.Departamento;
import com.github.lucasjalves.projetoles.entidade.Setor;
import com.github.lucasjalves.projetoles.rns.Mensagem;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;

@RegraNegocio(classe = Departamento.class, operacao = "SALVAR")
public class DadosObrigatoriosDepartamento implements Strategy{

	private List<Mensagem> mensagens = new ArrayList<Mensagem>();
	
	@Override
	public List<Mensagem> processar(Object object) {
		Departamento departamento = (Departamento)object;
		if(departamento.getNome().trim().length() == 0) {
			mensagens.add(new Mensagem("Nome do departamento é obrigatório"));
		}
		if(departamento.getSetores().size() > 0) {
			List<Setor> setores = departamento.getSetores().stream()
							.filter(s -> s.getNome().trim().length() == 0)
							.collect(Collectors.toList());
			if(setores.size() > 0) {
				mensagens.add(new Mensagem("Todos os setores devem conter um nome"));
			}		
		}
		if(departamento.getSetores().size() == 0) {
			mensagens.add(new Mensagem("Todo departamento deve conter no mínimo um setor"));
		}
		
		return mensagens;
	}

}
