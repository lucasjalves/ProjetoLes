package com.github.lucasjalves.projetoles.util;

public final class StringUtils {

	private StringUtils() {
	
	}
	
	public static boolean isNullOrEmpty(String texto) {
		if(texto == null) {
			return true;
		}
		if(texto.isEmpty()) {
			return texto.isEmpty() || texto.replaceAll("\\s+","").length() == 0;
		}
		return false;
	}
	
	public static Double StringToValor(String valor) throws Exception{
		String textoFormatado = valor.replaceAll("[.]", "").replaceAll("[,]", ".");
		return Double.parseDouble(textoFormatado);
	}
}
