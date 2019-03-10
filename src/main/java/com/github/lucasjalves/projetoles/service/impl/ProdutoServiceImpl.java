package com.github.lucasjalves.projetoles.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService{

	@Autowired
	private Facade facade;

	@Override
	public Resultado cadastrar(Produto produto) {
		return facade.salvar(produto);
	}

	@Override
	public Resultado consultar(Entidade entidade) {
		return facade.buscar(entidade);
	}

	@Override
	public Resultado desativar(Long id) throws Exception   {
		Produto produto = new Produto();
		produto.setId(id);
		
		Resultado resultado = facade.buscarPorId(produto);
		if(resultado.getEntidades().isEmpty()){
			throw new Exception("Produto não encontrado");
		}
		if(resultado.getMensagem().isEmpty()) {
			produto = (Produto) resultado.getEntidades().get(0);
			produto.setAtivo(false);
			return facade.alterar(produto);
		}
		
		return resultado;
	}

	@Override
	public Resultado consultarPorId(Long id) throws Exception {
		Produto produto = new Produto();
		produto.setId(id);
		Resultado resultado = 
				facade.buscarPorId(produto);
		
		List<Produto> produtos = 
				(List<Produto>) resultado.getEntidades();
		
		if(produtos.isEmpty()) {
			throw new Exception("Produto não encontrado");
		}
		
		return resultado;
	}

	@Override
	public Resultado alterar(Produto produto) {
		return facade.alterar(produto);
	}

}
