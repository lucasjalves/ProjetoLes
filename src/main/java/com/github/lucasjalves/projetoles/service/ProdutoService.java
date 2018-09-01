package com.github.lucasjalves.projetoles.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Service
public interface ProdutoService {

	Resultado salvar(Produto produto);
	List<Produto> buscarTodos();
	Resultado alterar(Produto produto);
	Resultado excluir(Produto produto);
}
