package com.github.lucasjalves.projetoles.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
		Pedido p = (Pedido)entidade;
		return repository.save(p);
	}

	@Override
	public List<Entidade> buscar(Entidade entidade) {
		Pedido p = (Pedido)entidade;
		List<Predicate<Pedido>> filtro = new ArrayList<>();
		
		if(p.getId() != null) {
			filtro.add(pedido -> pedido.equals(p));
		}
		if(p.getStatus() != null) {
			filtro.add(pedido -> pedido.getStatus().equals(p.getStatus()));
		}
		Predicate<Pedido> compositePredicate = filtro.stream().reduce(c -> true, Predicate::and);
		
		return repository.findAll().stream().filter(compositePredicate).collect(Collectors.toList());
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
