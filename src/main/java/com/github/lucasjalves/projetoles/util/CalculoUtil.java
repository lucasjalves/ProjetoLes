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
	
	public static boolean isValorZerado(String valor) {
		if(valor == null) {
			return true;
		}
		valor = valor.replaceAll("[^\\d]", "");
		if(valor.length() == 0) {
			return true;
		}
		if(Integer.parseInt(valor) == 0) {
			return true;
		}
		return false;
	}
	
	public static String formatMoney(Double valor) {
		try {
			return String.format("%,.2f", valor);
		}catch(Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static Double StringToValor(String valor) throws Exception{
		String textoFormatado = valor.replaceAll("[.]", "").replaceAll("[,]", ".");
		return Double.parseDouble(textoFormatado);
	}
	
	public static Double StringToDouble(String valor){
		String textoFormatado = valor.replaceAll("[.]", "").replaceAll("[,]", ".");
		return Double.parseDouble(textoFormatado);
	}
}	
