package com.github.lucasjalves.projetoles.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.helper.ProcessarRegraNegocioHelper;
import com.github.lucasjalves.projetoles.rns.Mensagem;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Component
public class FacadeImpl implements Facade {

	@Autowired
	private ProcessarRegraNegocioHelper regraNegocioHelper;
	private Resultado resultado;
	
	private <T> T noCast(Object object)
	{
		return (T) object;
	}
	

	@Override
	public Resultado salvar(Entidade entidade, JpaRepository<?, Long> repository) {
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		mensagens = regraNegocioHelper.processarRegraNegocio(entidade, "SALVAR");
		resultado = new Resultado();
		resultado.setMensagem(mensagens);
		if(mensagens.size() == 0) {
			resultado.setEntidades(repository.save(noCast(entidade)));
		}
		
		return resultado;
	}

	@Override
	public Resultado buscar(Entidade entidade, JpaRepository<?, Long> repository) {
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		mensagens = regraNegocioHelper.processarRegraNegocio(entidade, "CONSULTAR");
		resultado = new Resultado();
		resultado.setMensagem(mensagens);
		if(mensagens.size() == 0) {
			resultado.setEntidades((List<Object>) repository.findAll());
		}
		
		return resultado;
	}

	@Override
	public Resultado alterar(Entidade entidade, JpaRepository<?, Long> repository) {
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		mensagens = regraNegocioHelper.processarRegraNegocio(entidade, "CONSULTAR");
		resultado = new Resultado();
		resultado.setMensagem(mensagens);
		if(mensagens.size() == 0) {
			resultado.setEntidades(repository.save(noCast(entidade)));
		}
		
		return resultado;
	}

	@Override
	public Resultado excluir(Entidade entidade, JpaRepository<?, Long> repository) {
		// TODO Auto-generated method stub
		return null;
	}

}
