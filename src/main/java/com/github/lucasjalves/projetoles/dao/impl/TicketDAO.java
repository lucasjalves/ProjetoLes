package com.github.lucasjalves.projetoles.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.dao.DAO;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.entidade.Ticket;
import com.github.lucasjalves.projetoles.repository.TicketRepository;

@Component
public class TicketDAO implements DAO{

	@Autowired
	private TicketRepository repository;
	@Override
	public Entidade salvar(Entidade entidade) {
		return (Ticket)entidade;
	}

	@Override
	public List<Entidade> buscar(Entidade entidade) {
		Ticket t = (Ticket)entidade;
		List<Predicate<Ticket>> filtro = new ArrayList<>();
		
		if(t.getId() != null) {
			filtro.add(ticket -> ticket.getId().equals(t.getId()));
		}
		if(t.getStatus() != null) {
			filtro.add(ticket -> ticket.getStatus().equals(t.getStatus()));
		}
		Predicate<Ticket> compositePredicate = filtro.stream().reduce(c -> true, Predicate::and);
		
		return repository.findAll().stream().filter(compositePredicate).collect(Collectors.toList());
	}

	@Override
	public Entidade alterar(Entidade entidade) {
		Ticket t = (Ticket) entidade;
		return repository.save(t);
	}

	@Override
	public Entidade excluir(Entidade entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
