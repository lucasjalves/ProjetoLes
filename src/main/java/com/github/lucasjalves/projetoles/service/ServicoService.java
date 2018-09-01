package com.github.lucasjalves.projetoles.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.lucasjalves.projetoles.entidade.Servico;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Service
public interface ServicoService {

	Resultado salvar(Servico servico);
	List<Servico> buscarTodos();
	Resultado alterar(Servico servico);
	Resultado excluir(Servico servico);
}
