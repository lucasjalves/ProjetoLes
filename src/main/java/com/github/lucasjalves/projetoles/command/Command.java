package com.github.lucasjalves.projetoles.command;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.rns.Resultado;

public interface Command {
	Resultado execute(Entidade entidade, JpaRepository<?, Long> repository);
}
