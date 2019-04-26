package com.github.lucasjalves.projetoles.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.dao.DAO;
import com.github.lucasjalves.projetoles.entidade.Entidade;

@Component
public class EnderecoDAO implements DAO{

	
	@Override
	public Entidade salvar(Entidade entidade) {
		return entidade;
	}

	@Override
	public List<Entidade> buscar(Entidade entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entidade alterar(Entidade entidade) {
		return entidade;
	}

	@Override
	public Entidade excluir(Entidade entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
