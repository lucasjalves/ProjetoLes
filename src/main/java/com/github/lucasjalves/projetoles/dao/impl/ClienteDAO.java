package com.github.lucasjalves.projetoles.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.dao.DAO;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.repository.ClienteRepository;
import com.github.lucasjalves.projetoles.util.StringUtils;

@Component
public class ClienteDAO implements DAO{

	
	@Autowired
	private ClienteRepository repository;
	@Override
	public Entidade salvar(Entidade entidade) {
		Cliente cliente = (Cliente)entidade;
		return repository.save(cliente);
	}

	@Override
	public List<Entidade> buscar(Entidade entidade) {
		Cliente cliente = (Cliente)entidade;
		List<Predicate<Cliente>> filtro = new ArrayList<>();
		
		if(!StringUtils.isNullOrEmpty(cliente.getCpfCnpj())) {	
			filtro.add(c -> c.getCpfCnpj().equalsIgnoreCase(cliente.getCpfCnpj()));
		}
		
		if(!StringUtils.isNullOrEmpty(cliente.getEmail())) {
			filtro.add(c -> c.getEmail().equalsIgnoreCase(cliente.getEmail()));
		}
		
		if(!StringUtils.isNullOrEmpty(cliente.getGenero())) {
			filtro.add(c -> c.getEmail().equalsIgnoreCase(cliente.getEmail()));
		}
		
		if(!StringUtils.isNullOrEmpty(cliente.getNome())) {
			filtro.add(c -> c.getNome().equalsIgnoreCase(cliente.getNome()));
		}
		
		if(!StringUtils.isNullOrEmpty(cliente.getSenha())) {
			filtro.add(c -> c.getSenha().equalsIgnoreCase(cliente.getSenha()));
		}
		
		if(!StringUtils.isNullOrEmpty(cliente.getUsername())) {
			filtro.add(c -> c.getUsername().equalsIgnoreCase(cliente.getUsername()));
		}
		
		if(!StringUtils.isNullOrEmpty(cliente.getDtNascimento())) {
			filtro.add( c-> c.getDtNascimento().equalsIgnoreCase(cliente.getDtNascimento()));
		}
		
		if(cliente.getTipoUsuario() != null) {
			filtro.add(c -> c.getTipoUsuario().equals(cliente.getTipoUsuario()));
		}
		
		if(cliente.getAtivo() != null) {
			filtro.add(c -> c.getAtivo().equals(cliente.getAtivo()));
		}
		if(cliente.getId() != null) {
			filtro.add(c -> c.getId().equals(cliente.getId()));
		}
		
		Predicate<Cliente> compositePredicate = filtro.stream().reduce(c -> true, Predicate::and);
		
		return repository.findAll().stream().filter(compositePredicate).collect(Collectors.toList());
		
	}

	@Override
	public Entidade alterar(Entidade entidade) {
		Cliente cliente = (Cliente)entidade;
		return repository.save(cliente);
	}

	@Override
	public Entidade excluir(Entidade entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
