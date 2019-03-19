package com.github.lucasjalves.projetoles.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.dao.DAO;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.entidade.Pedido;
import com.github.lucasjalves.projetoles.repository.PedidoRepository;

@Component
public class PedidoDAO implements DAO{

	@Autowired
	private PedidoRepository repository;
	@Override
	public Entidade salvar(Entidade entidade) {
		Pedido pedido = (Pedido)entidade;
		return repository.save(pedido);
	}

	@Override
	public List<Entidade> buscar(Entidade entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entidade alterar(Entidade entidade) {
		Pedido pedido = (Pedido)entidade;
		return repository.save(pedido);
	}

	@Override
	public Entidade excluir(Entidade entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
