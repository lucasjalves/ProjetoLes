package com.github.lucasjalves.projetoles.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService{

	@Autowired
	private Facade facade;

	@Override
	public Resultado cadastrar(Produto produto) {
		return facade.salvar(produto);
	}

	@Override
	public Resultado consultar(Entidade entidade) {
		return facade.buscar(entidade);
	}

	@Override
	public Resultado deletar(Produto produto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resultado consultarPorId(Produto produto) {
		return facade.buscarPorId(produto);
	}

	@Override
	public Resultado alterar(Produto produto) {
		return facade.alterar(produto);
	}

}
