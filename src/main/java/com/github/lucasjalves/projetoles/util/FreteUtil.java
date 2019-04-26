package com.github.lucasjalves.projetoles.util;

import com.github.lucasjalves.projetoles.entidade.Produto;

public final class FreteUtil {
	private FreteUtil() {
		
	}
	private static final Double VALOR_DIVISAO = 50.0;
	private static final Double TAXA_FIXA_FRETE = 5.0;
	public static Double calcularFrete(Produto produto, Integer quantidade) {
		Double peso = Double.parseDouble(produto.getPeso()) / VALOR_DIVISAO;
		Double altura = Double.parseDouble(produto.getAltura()) / VALOR_DIVISAO;
		Double comprimento = Double.parseDouble(produto.getComprimento()) / VALOR_DIVISAO;
		Double largura = Double.parseDouble(produto.getLargura()) / VALOR_DIVISAO;
		if(quantidade == 0) {
			return 0.0;
		}
		return ((peso + altura + comprimento + largura) * quantidade) + TAXA_FIXA_FRETE ;
	}
	
	public static String calcularFreteFormatted(Produto produto, Integer quantidade) {
		Double peso = Double.parseDouble(produto.getPeso()) / VALOR_DIVISAO;
		Double altura = Double.parseDouble(produto.getAltura()) / VALOR_DIVISAO;
		Double comprimento = Double.parseDouble(produto.getComprimento()) / VALOR_DIVISAO;
		Double largura = Double.parseDouble(produto.getLargura()) / VALOR_DIVISAO;
		
		
		Double total = ((peso + altura + comprimento + largura) * quantidade) + TAXA_FIXA_FRETE;
		if(quantidade == 0.0) {
			total = 0.0;
		}
		return String.format("%,.2f", total);
	}	
}
