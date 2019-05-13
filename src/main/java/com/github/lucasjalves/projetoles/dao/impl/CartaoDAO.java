package com.github.lucasjalves.projetoles.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.dao.DAO;
import com.github.lucasjalves.projetoles.entidade.CartaoCredito;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.repository.CartaoCreditoRepository;

@Component
public class CartaoDAO implements DAO{

	@Autowired
	private CartaoCreditoRepository repository;
	
	@Override
	public Entidade salvar(Entidade entidade) {
		return (CartaoCredito) entidade;
	}

	@Override
	public List<Entidade> buscar(Entidade entidade) {
		CartaoCredito e = (CartaoCredito)entidade;
		List<Predicate<CartaoCredito>> filtro = new ArrayList<>();
		
		if(e.getId() != null) {
			filtro.add(cartao -> cartao.getId().equals(e.getId()));
		}

		Predicate<CartaoCredito> compositePredicate = filtro.stream().reduce(c -> true, Predicate::and);
		
		return repository.findAll().stream().filter(compositePredicate).collect(Collectors.toList());
	}

	@Override
	public Entidade alterar(Entidade entidade) {
		CartaoCredito c = (CartaoCredito)entidade;
		return repository.save(c);
	}

	@Override
	public Entidade excluir(Entidade entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
