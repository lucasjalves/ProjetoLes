package com.github.lucasjalves.projetoles.facade;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.rns.Resultado;

public interface Facade {
	Resultado salvar(Entidade entidade, JpaRepository<?, Long> repository);
	Resultado buscar(Entidade entidade, JpaRepository<?, Long> repository);
	Resultado alterar(Entidade entidade, JpaRepository<?, Long> repository);
	Resultado excluir(Entidade entidade, JpaRepository<?, Long> repository);
}
