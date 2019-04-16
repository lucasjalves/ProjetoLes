package com.github.lucasjalves.projetoles.helper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.lucasjalves.projetoles.entidade.Pedido;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.util.CalculoUtil;

public final class EfetivacaoPedidoHelper {

	public static Resultado validarPedido(Pedido pedido) {
		if(pedido.getCartoes().isEmpty()) {
			return new Resultado("Você deve selecionar um cartão para pagamento");
		}
		if(!pedido.getCartoes().isEmpty()) {
			Set<Long> ids = pedido.getCartoes()
					.stream().map(c -> c.getId())
					.collect(Collectors.toCollection(HashSet::new));
			
			if(ids.size() != pedido.getCartoes().size()) {
				return new Resultado("Você deve selecionar mais de um cartão diferente.");
			}
		}
		
		List<String> valores = pedido.getCartoes()
				.stream()
				.map(c -> c.getValor())
				.collect(Collectors.toList());
		Double valor = 0.0;
		for(String v: valores) {
			try {
				valor = + valor + CalculoUtil.StringToDouble(v);
			}catch(Exception e) {
				return new Resultado("Você deve preencher um valor para cada cartão");
			}
			
		}
		
		Double valorTotal = CalculoUtil.StringToDouble(pedido.getTotalCompra());
		
		if(valor != valorTotal) {
			return new Resultado("O valor a pagar por cartão não está igual ao total. Verifique os valores a pagar");
		}
		
		return new Resultado();
	}
}
