package com.github.lucasjalves.projetoles.facade;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Component
public interface Facade {
	Resultado salvar(Entidade entidade);
	Resultado buscar(Entidade entidade);
	Resultado alterar(Entidade entidade);
	Resultado excluir(Entidade entidade);
	Resultado buscarPorId(Entidade entidade);
	Resultado buscarPorFiltro(Entidade entidade, List<Predicate<? extends Entidade>> predicados);
}
