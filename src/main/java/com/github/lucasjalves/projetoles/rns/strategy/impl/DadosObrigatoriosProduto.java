package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.annotation.RegraNegocio;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.rns.Mensagem;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;
import com.github.lucasjalves.projetoles.util.StringUtils;

@RegraNegocio(classe=Produto.class, operacao={"SALVAR", "ALTERAR"})
public class DadosObrigatoriosProduto implements Strategy<Produto>{

	private List<Mensagem> mensagens = new ArrayList<>();
	@Override
	public List<Mensagem> processar(Produto entidade) {
		if(StringUtils.isNullOrEmpty(entidade.getNome())) {
			mensagens.add(new Mensagem("Nome vazia!"));
		}
		if(StringUtils.isNullOrEmpty(entidade.getAltura())) {
			mensagens.add(new Mensagem("Altura vazia!"));
		}
		if(StringUtils.isNullOrEmpty(entidade.getCodigoBarras())) {
			mensagens.add(new Mensagem("Codigo de barras vazio!"));
		}
		if(StringUtils.isNullOrEmpty(entidade.getComprimento())) {
			mensagens.add(new Mensagem("Comprimento vazio!"));
		}		
		if(StringUtils.isNullOrEmpty(entidade.getConteudoEmbalagem())) {
			mensagens.add(new Mensagem("Conteudo embalagem vazio!"));
		}		
		if(StringUtils.isNullOrEmpty(entidade.getDescricao())) {
			mensagens.add(new Mensagem("Descrição vazia!"));
		}
		if(StringUtils.isNullOrEmpty(entidade.getEspecificacoes())) {
			mensagens.add(new Mensagem("Especificações vazio!"));
		}	
		if(StringUtils.isNullOrEmpty(entidade.getLargura())) {
			mensagens.add(new Mensagem("Largura vazia!"));
		}	
		if(StringUtils.isNullOrEmpty(entidade.getMarca())) {
			mensagens.add(new Mensagem("Marca vazio!"));
		}			
		if(StringUtils.isNullOrEmpty(entidade.getModelo())) {
			mensagens.add(new Mensagem("Modelo vazio!"));
		}			
		if(StringUtils.isNullOrEmpty(entidade.getPeso())) {
			mensagens.add(new Mensagem("Peso vazia!"));
		}			
		if(StringUtils.isNullOrEmpty(entidade.getPrecoCompra())) {
			mensagens.add(new Mensagem("Preco compra vazio!"));
		}	
		if(StringUtils.isNullOrEmpty(entidade.getPrecoVenda())) {
			mensagens.add(new Mensagem("Preco venda vazia!"));
		}
		if(entidade.getCategoria() == null) {
			mensagens.add(new Mensagem("Categoria vazia!"));
		}	
		if(entidade.getEstoque() == null) {
			mensagens.add(new Mensagem("Estoque vazio!"));
		}
		return mensagens;
	}

}
