package com.github.lucasjalves.projetoles.rns.strategy;

import java.util.List;

import com.github.lucasjalves.projetoles.entidade.Entidade;

public interface Strategy{

	List<String> processar(Entidade entidade);
}
