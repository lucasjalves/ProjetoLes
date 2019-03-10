package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.annotation.RegraNegocio;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.rns.Mensagem;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;
import com.github.lucasjalves.projetoles.util.StringUtils;

@RegraNegocio(classe=Produto.class, operacao={"SALVAR", "ALTERAR"})
public class ValorProduto implements Strategy<Produto> {
	
	private List<Mensagem> mensagens = new ArrayList<>();
	
	@Override
	public List<Mensagem> processar(Produto entidade) {
		if(!StringUtils.isNullOrEmpty(entidade.getPrecoCompra()) && !StringUtils.isNullOrEmpty(entidade.getPrecoVenda())) {
			String precoVenda = entidade.getPrecoVenda().replaceAll("[.]", "").replaceAll(",", ".");
			String precoCompra =  entidade.getPrecoCompra().replaceAll("[.]", "").replaceAll(",", ".");
			
			try {
				if(Double.parseDouble(precoVenda) <= Double.parseDouble(precoCompra)) {
					mensagens.add(new Mensagem("Preço de venda não pode ser inferior ou igual ao preço de compra"));
				}				
			}catch(Exception e) {
				mensagens.add(new Mensagem("Preço de venda ou de compra inválidos"));
			}

		}
		return mensagens;
	}

}
