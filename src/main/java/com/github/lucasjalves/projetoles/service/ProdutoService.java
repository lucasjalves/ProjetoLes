package com.github.lucasjalves.projetoles.service;

import org.springframework.stereotype.Service;

import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Service
  public interface ProdutoService {
	 Resultado cadastrar(Produto produto);
	 Resultado consultar(Entidade entidade);
	 Resultado deletar(Produto produto);
	 Resultado consultarPorId(Produto produto);
	 Resultado alterar(Produto produto);
}
