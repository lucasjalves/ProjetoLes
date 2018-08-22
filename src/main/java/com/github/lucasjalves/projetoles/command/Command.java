package com.github.lucasjalves.projetoles.command;

import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Component
public interface Command {
	Resultado execute(Entidade entidade);
}
