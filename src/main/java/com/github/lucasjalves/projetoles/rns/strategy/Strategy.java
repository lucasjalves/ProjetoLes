package com.github.lucasjalves.projetoles.rns.strategy;

import java.util.List;

import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.rns.Mensagem;

public interface Strategy<entidade extends Entidade> {

	List<Mensagem> processar(entidade entidade);
}
