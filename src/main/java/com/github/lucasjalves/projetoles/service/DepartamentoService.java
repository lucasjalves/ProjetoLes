package com.github.lucasjalves.projetoles.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.lucasjalves.projetoles.entidade.Departamento;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Service
public interface DepartamentoService {

	Resultado salvar(Departamento departamento);
	List<Departamento> buscarTodos();
	Resultado alterar(Departamento departamento);
	Resultado excluir(Departamento departamento);
}
