package com.github.lucasjalves.projetoles.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.dao.DAO;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.repository.ProdutoRepository;
import com.github.lucasjalves.projetoles.util.StringUtils;

@Component
public class ProdutoDAO implements DAO{

	@Autowired
	private ProdutoRepository repository;
	@Override
	public Entidade salvar(Entidade entidade) {
		return repository.save((Produto)entidade);
	}

	@Override
	public List<Entidade> buscar(Entidade entidade) {
		Produto produto = (Produto)entidade;
		List<Predicate<Produto>> filtro = new ArrayList<>();
		
		if(!StringUtils.isNullOrEmpty(produto.getAltura())) {
			filtro.add(p -> p.getAltura().equals(produto.getAltura()));
		}
		
		if(!StringUtils.isNullOrEmpty(produto.getNome())) {
			filtro.add(p -> p.getNome().equals(produto.getNome()));
		}	
		
		if(!StringUtils.isNullOrEmpty(produto.getPrecoCompra())) {
			filtro.add(p -> p.getPrecoCompra().equals(produto.getPrecoCompra()));
		}	
		
		if(!StringUtils.isNullOrEmpty(produto.getPrecoVenda())) {
			filtro.add(p -> p.getPrecoVenda().equals(produto.getPrecoVenda()));
		}	
		
		if(!StringUtils.isNullOrEmpty(produto.getMarca())) {
			filtro.add(p -> p.getMarca().equals(produto.getMarca()));
		}	
		
		if(!StringUtils.isNullOrEmpty(produto.getModelo())) {
			filtro.add(p -> p.getModelo().equals(produto.getModelo()));
		}		
		
		if(!StringUtils.isNullOrEmpty(produto.getCodigoBarras())) {
			filtro.add(p -> p.getCodigoBarras().equals(produto.getCodigoBarras()));
		}			
		if(produto.getAtivo() != null) {
			filtro.add(p -> p.getAtivo().equals(produto.getAtivo()));
		}
		if(produto.getCategoria() != null) {
			filtro.add(p -> p.getCategoria().equals(produto.getCategoria()));
		}
		
		if(produto.getId() != null) {
			filtro.add(p -> p.getId().equals(produto.getId()));
		}
		List<Produto> consulta = repository.findAll();
		
		for(Predicate<Produto> p : filtro) {
			consulta = consulta.stream()
						.filter(p)
						.collect(Collectors.toList());
		}		
		
		return consulta.stream().collect(Collectors.toList());
	}

	@Override
	public Entidade alterar(Entidade entidade) {
		return repository.save((Produto)entidade);
	}

	@Override
	public Entidade excluir(Entidade entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
