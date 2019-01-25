package com.github.lucasjalves.projetoles.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.entidade.Entidade;

@Component
public interface DAO {

	Entidade salvar(Entidade entidade);
	List<Entidade> buscar(Entidade entidade);
	Entidade alterar(Entidade entidade);
	Entidade excluir(Entidade entidade);
	Entidade buscarPorId(Entidade entidade);
}
