package com.github.lucasjalves.projetoles.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.dao.DAO;
import com.github.lucasjalves.projetoles.entidade.Endereco;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.repository.EnderecoRepository;

@Component
public class EnderecoDAO implements DAO{

	@Autowired
	private EnderecoRepository repository;
	
	@Override
	public Entidade salvar(Entidade entidade) {
		return entidade;
	}

	@Override
	public List<Entidade> buscar(Entidade entidade) {
		Endereco e = (Endereco)entidade;
		List<Predicate<Endereco>> filtro = new ArrayList<>();
		
		if(e.getId() != null) {
			filtro.add(endereco -> endereco.getId().equals(e.getId()));
		}

		Predicate<Endereco> compositePredicate = filtro.stream().reduce(c -> true, Predicate::and);
		
		return repository.findAll().stream().filter(compositePredicate).collect(Collectors.toList());
	}

	@Override
	public Entidade alterar(Entidade entidade) {
		Endereco e = (Endereco)entidade;
		return repository.save(e);
	}

	@Override
	public Entidade excluir(Entidade entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
