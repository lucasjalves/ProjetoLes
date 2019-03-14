package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;
import com.github.lucasjalves.projetoles.util.StringUtils;

public class DadosObrigatoriosProduto implements Strategy{

	
	@Override
	public List<String> processar(Entidade produto) {
		List<String> mensagens = new ArrayList<>();
		Produto entidade = (Produto)produto;
		
		if(StringUtils.isNullOrEmpty(entidade.getNome())) {
			mensagens.add("Nome vazio!");
		}
		if(StringUtils.isNullOrEmpty(entidade.getAltura())) {
			mensagens.add("Altura vazia!");
		}
		if(StringUtils.isNullOrEmpty(entidade.getCodigoBarras())) {
			mensagens.add("Codigo de barras vazio!");
		}
		if(StringUtils.isNullOrEmpty(entidade.getComprimento())) {
			mensagens.add("Comprimento vazio!");
		}		
		if(StringUtils.isNullOrEmpty(entidade.getConteudoEmbalagem())) {
			mensagens.add("Conteudo embalagem vazio!");
		}		
		if(StringUtils.isNullOrEmpty(entidade.getDescricao())) {
			mensagens.add("Descrição vazia!");
		}
		if(StringUtils.isNullOrEmpty(entidade.getEspecificacoes())) {
			mensagens.add("Especificações vazio!");
		}	
		if(StringUtils.isNullOrEmpty(entidade.getLargura())) {
			mensagens.add("Largura vazia!");
		}	
		if(StringUtils.isNullOrEmpty(entidade.getMarca())) {
			mensagens.add("Marca vazio!");
		}			
		if(StringUtils.isNullOrEmpty(entidade.getModelo())) {
			mensagens.add("Modelo vazio!");
		}			
		if(StringUtils.isNullOrEmpty(entidade.getPeso())) {
			mensagens.add("Peso vazia!");
		}			
		if(StringUtils.isNullOrEmpty(entidade.getPrecoCompra())) {
			mensagens.add("Preco compra vazio!");
		}	
		if(StringUtils.isNullOrEmpty(entidade.getPrecoVenda())) {
			mensagens.add("Preco venda vazia!");
		}
		if(entidade.getCategoria() == null) {
			mensagens.add("Categoria vazia!");
		}	
		if(entidade.getEstoque() == null) {
			mensagens.add("Estoque vazio!");
		}
		return mensagens;
	}

}
