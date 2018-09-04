package com.github.lucasjalves.projetoles.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.lucasjalves.projetoles.entidade.Servico;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.service.ServicoService;

@Service
final class ServicoServiceImpl implements ServicoService{

	@Autowired
	private Facade facade;
	
	@Override
	public Resultado salvar(Servico servico) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Servico> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resultado alterar(Servico servico) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resultado excluir(Servico servico) {
		// TODO Auto-generated method stub
		return null;
	}

}
