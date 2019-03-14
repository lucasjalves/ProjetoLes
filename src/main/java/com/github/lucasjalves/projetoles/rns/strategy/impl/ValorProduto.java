package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.annotation.RegraNegocio;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;
import com.github.lucasjalves.projetoles.util.StringUtils;

@RegraNegocio(classe=Produto.class, operacao={"SALVAR", "ALTERAR"})
public class ValorProduto implements Strategy {
	
	
	@Override
	public List<String> processar(Entidade produto) {
		List<String> mensagens = new ArrayList<>();
		Produto entidade = (Produto)produto;
		if(!StringUtils.isNullOrEmpty(entidade.getPrecoCompra()) && !StringUtils.isNullOrEmpty(entidade.getPrecoVenda())) {
			String precoVenda = entidade.getPrecoVenda().replaceAll("[.]", "").replaceAll(",", ".");
			String precoCompra =  entidade.getPrecoCompra().replaceAll("[.]", "").replaceAll(",", ".");
			
			try {
				if(Double.parseDouble(precoVenda) <= Double.parseDouble(precoCompra)) {
					mensagens.add("Preço de venda não pode ser inferior ou igual ao preço de compra");
				}				
			}catch(Exception e) {
				mensagens.add("Preço de venda ou de compra inválidos");
			}

		}
		return mensagens;
	}

}
