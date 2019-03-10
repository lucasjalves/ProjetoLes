package com.github.lucasjalves.projetoles.util;

import java.math.BigDecimal;

public final class CalculoUtil {
	private CalculoUtil() {
		
	}
	
	public static String calcularValorTotal(String valorItemFormatado,  Integer quantidade) {
		String valorItemDouble = valorItemFormatado.replaceAll("[.]","").replaceAll(",", ".");
		BigDecimal valorItemDecimal = new BigDecimal(valorItemDouble);
		BigDecimal quantidadeDecimal = new BigDecimal(quantidade);
		
		double somaValorTotal = valorItemDecimal.multiply(quantidadeDecimal).doubleValue();
		return String.format("%,.2f", somaValorTotal);
	}
}	
