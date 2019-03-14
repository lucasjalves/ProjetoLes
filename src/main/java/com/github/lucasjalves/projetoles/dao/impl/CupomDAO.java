package com.github.lucasjalves.projetoles.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.dao.DAO;
import com.github.lucasjalves.projetoles.entidade.Cupom;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.repository.CupomRepository;
import com.github.lucasjalves.projetoles.util.StringUtils;

@Component
public class CupomDAO implements DAO{

	@Autowired
	private CupomRepository repository;
	@Override
	public Entidade salvar(Entidade entidade) {
		return repository.save((Cupom)entidade);
	}

	@Override
	public List<Entidade> buscar(Entidade entidade) {
		Cupom cupom = (Cupom)entidade;
		List<Predicate<Cupom>> filtro = new ArrayList<>();
				
		if(!StringUtils.isNullOrEmpty(cupom.getCodigo())) {
			filtro.add(c -> c.getCodigo().equals(cupom.getCodigo()));
		}
		
		if(!StringUtils.isNullOrEmpty(cupom.getDataVencimento())) {
			filtro.add(c -> c.getDataVencimento().equals(cupom.getDataVencimento()));
		}
		
		if(cupom.getId() != null) {
			filtro.add(c -> c.getId().equals(cupom.getId()));
		}		
		
		if(cupom.getStatus() != null) {
			filtro.add(c -> c.getStatus().equals(cupom.getStatus()));
		}
		
		List<Cupom> consulta = repository.findAll();
		
		for(Predicate<Cupom> p : filtro) {
			consulta = consulta.stream()
						.filter(p)
						.collect(Collectors.toList());
		}		
		
		return consulta.stream().collect(Collectors.toList());		
	}

	@Override
	public Entidade alterar(Entidade entidade) {
		return repository.save((Cupom)entidade);
	}

	@Override
	public Entidade excluir(Entidade entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
