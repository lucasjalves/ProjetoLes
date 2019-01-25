package com.github.lucasjalves.projetoles.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.dao.DAO;
import com.github.lucasjalves.projetoles.dao.impl.DAOImpl;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.helper.ProcessarRegraNegocioHelper;
import com.github.lucasjalves.projetoles.helper.RepositoryHelper;
import com.github.lucasjalves.projetoles.rns.Mensagem;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Component
final public class FacadeImpl implements Facade {

	@Autowired
	private ProcessarRegraNegocioHelper regraNegocioHelper;
	
	@Autowired
	private DAO dao;
	
	@Override
	public Resultado salvar(Entidade entidade) {
		Resultado resultado = process(entidade, "SALVAR");
		if(resultado.getMensagem().size() == 0) {
			List<Entidade> lista = new ArrayList<>();
			lista.add(dao.salvar(entidade));
			resultado.setEntidades(lista);
		}
		return resultado;
	}

	@Override
	public Resultado buscar(Entidade entidade) {
		Resultado resultado = process(entidade, "CONSULTAR");
		if(resultado.getMensagem().size() == 0) {
			resultado.setEntidades(dao.buscar(entidade));
		}
		return resultado;
	}

	@Override
	public Resultado alterar(Entidade entidade) {
		Resultado resultado = process(entidade, "ALTERAR");
		if(resultado.getMensagem().size() == 0) {
			List<Entidade> lista = new ArrayList<>();
			lista.add(dao.alterar(entidade));
			resultado.setEntidades(lista);	
		}
		return resultado;
	}

	@Override
	public Resultado excluir(Entidade entidade) {
		Resultado resultado = process(entidade, "EXCLUIR");
		if(resultado.getMensagem().size() == 0) {
			List<Entidade> lista = new ArrayList<>();
			lista.add(dao.excluir(entidade));
			resultado.setEntidades(lista);
		}		
		return resultado;
	}

	@Override
	public Resultado buscarPorId(Entidade entidade) {
		Resultado resultado = process(entidade, "CONSULTAR");
		if(resultado.getMensagem().size() == 0) {
			Entidade e = dao.buscarPorId(entidade);
			ArrayList<Entidade> lista = new ArrayList<>();
			lista.add(e);
			resultado.setEntidades(lista);
		}
		return resultado;
	}
	
	private Resultado process(Entidade entidade, String operacao){
		Resultado resultado = new Resultado();
		resultado.setMensagem(regraNegocioHelper.processarRegraNegocio(entidade, operacao));
		return resultado;
	}


	

}
