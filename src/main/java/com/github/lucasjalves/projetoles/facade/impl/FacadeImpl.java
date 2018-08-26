package com.github.lucasjalves.projetoles.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.helper.ProcessarRegraNegocioHelper;
import com.github.lucasjalves.projetoles.helper.RepositoryHelper;
import com.github.lucasjalves.projetoles.rns.Mensagem;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Component
public class FacadeImpl implements Facade {

	@Autowired
	private ProcessarRegraNegocioHelper regraNegocioHelper;
	
	@Autowired
	private RepositoryHelper repositoryHelper;
	
	@Override
	public Resultado salvar(Entidade entidade) {
		Resultado resultado = process(entidade, "SALVAR");
		if(resultado.getMensagem().size() == 0) {
			JpaRepository<?, Long> repository = repositoryHelper.getRepository(entidade);
			List<Entidade> lista = new ArrayList<>();
			lista.add(repository.save(noCast(entidade)));
			resultado.setEntidades(lista);
		}
		
		return resultado;
	}

	@Override
	public Resultado buscar(Entidade entidade) {
		Resultado resultado = process(entidade, "CONSULTAR");
		if(resultado.getMensagem().size() == 0) {
			JpaRepository<?, Long> repository = repositoryHelper.getRepository(entidade);
			resultado.setEntidades((List<Entidade>) repository.findAll());
		}
		return resultado;
	}

	@Override
	public Resultado alterar(Entidade entidade) {
		Resultado resultado = process(entidade, "ALTERAR");
		if(resultado.getMensagem().size() == 0) {
			JpaRepository<?, Long> repository = repositoryHelper.getRepository(entidade);
			List<Entidade> lista = new ArrayList<>();
			lista.add(repository.save(noCast(entidade)));
			resultado.setEntidades(lista);
			
		}
		
		return resultado;
	}

	@Override
	public Resultado excluir(Entidade entidade) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Resultado process(Entidade entidade, String operacao){
		Resultado resultado = new Resultado();
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		mensagens = regraNegocioHelper.processarRegraNegocio(entidade, operacao);
		resultado = new Resultado();
		resultado.setMensagem(mensagens);
		return resultado;
	}
	
	private <T> T noCast(Object object)
	{
		return (T) object;
	}
	

}
