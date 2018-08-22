package com.github.lucasjalves.projetoles.command.impl;

import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Component
public class AlterarCommand extends AbstractCommand {

	@Override
	public Resultado execute(Entidade entidade) {
		return this.facade.alterar(entidade);
	}

}
