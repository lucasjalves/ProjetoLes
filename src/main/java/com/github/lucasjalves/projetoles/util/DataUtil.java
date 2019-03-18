package com.github.lucasjalves.projetoles.util;

import java.time.LocalDate;

public final class DataUtil {
	private DataUtil() {
		
	}
	
	public static LocalDate formatarData(String data) throws Exception{
		StringBuilder sb = new StringBuilder(data);
		String[] reversed = sb.toString().split("/");
		int dia = Integer.parseInt(reversed[0]);
		int mes = Integer.parseInt(reversed[1]);
		int ano = Integer.parseInt(reversed[2]);
		
		return LocalDate.of(ano, mes, dia);
		
	}
}
