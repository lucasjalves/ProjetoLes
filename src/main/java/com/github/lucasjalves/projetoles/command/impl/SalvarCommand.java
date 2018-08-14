package com.github.lucasjalves.projetoles.command.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.rns.Resultado;

public class SalvarCommand extends AbstractCommand {

	@Override
	public Resultado execute(Entidade entidade, JpaRepository<?, Long> repository) {
		return this.facade.salvar(entidade, repository);	
	}

}
