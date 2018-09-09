package com.github.lucasjalves.projetoles.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.service.ProdutoService;

@Service
final class ProdutoServiceImpl implements ProdutoService{

	@Autowired
	private Facade facade;

	@Override
	public Resultado salvar(Produto produto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resultado alterar(Produto produto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resultado excluir(Produto produto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produto> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	


}
