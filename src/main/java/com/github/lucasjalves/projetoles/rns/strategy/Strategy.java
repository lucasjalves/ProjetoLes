package com.github.lucasjalves.projetoles.rns.strategy;

import java.util.List;

import com.github.lucasjalves.projetoles.rns.Mensagem;

public interface Strategy {

	List<Mensagem> processar(Object object);
}
