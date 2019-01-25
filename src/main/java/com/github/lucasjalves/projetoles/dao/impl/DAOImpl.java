package com.github.lucasjalves.projetoles.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.dao.DAO;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.helper.RepositoryHelper;

@Component
final public class DAOImpl implements DAO {

	@Autowired
	private RepositoryHelper repositoryHelper;
	
	@Override
	public Entidade salvar(Entidade entidade) {
		return repositoryHelper.getRepository(entidade).save(noCast(entidade));
	}

	@Override
	public List<Entidade> buscar(Entidade entidade) {
		return (List<Entidade>) repositoryHelper.getRepository(entidade).findAll();
	}

	@Override
	public Entidade alterar(Entidade entidade) {
		return repositoryHelper.getRepository(entidade).save(noCast(entidade));
	}

	@Override
	public Entidade excluir(Entidade entidade) {
		repositoryHelper.getRepository(entidade).deleteById(entidade.getId());
		return entidade;
	}

	
	private <T> T noCast(Object object)
	{
		return (T) object;
	}

	@Override
	public Entidade buscarPorId(Entidade entidade) {
		Optional<? extends Entidade> lista = (Optional<? extends Entidade>) repositoryHelper.getRepository(entidade).findById(entidade.getId());
		return lista.get();
	}


	
}
