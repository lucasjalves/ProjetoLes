package com.github.lucasjalves.projetoles.dao;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.entidade.Entidade;

@Component
public interface DAO {

	Entidade salvar(Entidade entidade);
	List<Entidade> buscar(Entidade entidade);
	Entidade alterar(Entidade entidade);
	Entidade excluir(Entidade entidade);
	Entidade buscarPorId(Entidade entidade);
	List<Entidade> buscarPorFiltro(Entidade entidade, List<Predicate<? extends Entidade>> listaPredicados);
}
