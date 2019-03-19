package com.github.lucasjalves.projetoles.util;

import java.time.LocalDateTime;

public final class FormatadorLocalDateTime {
	
	private FormatadorLocalDateTime() {
		
	}
	
	public static String dataFormatada(LocalDateTime localDateTime) {
		String dtHora = localDateTime.toString();
		
		String[] split = dtHora.split("T");
		String[] splitDt = split[0].split("-");
		
		String data = splitDt[2] + "/" + splitDt[1] + "/" + splitDt[0];
		
		String[] splitHr = split[1].split("[.]");
		
		return data + "T" + splitHr[0];
	}
}
