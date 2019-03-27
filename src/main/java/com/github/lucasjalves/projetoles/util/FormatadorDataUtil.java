package com.github.lucasjalves.projetoles.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class FormatadorDataUtil {
	
	private FormatadorDataUtil() {
		
	}
	
	public static String dataFormatada(LocalDateTime localDateTime) {
		String dtHora = localDateTime.toString();
		
		String[] split = dtHora.split("T");
		String[] splitDt = split[0].split("-");
		
		String data = splitDt[2] + "/" + splitDt[1] + "/" + splitDt[0];
		
		String[] splitHr = split[1].split("[.]");
		
		return data + "T" + splitHr[0];
	}
	
	public static LocalDate toLocalDate(String data) {
		String[] dataSplit = data.split("/");
		
		return LocalDate.now().withDayOfMonth(Integer.parseInt(dataSplit[0]))
			.withMonth(Integer.parseInt(dataSplit[1]))
			.withYear(Integer.parseInt(dataSplit[2]));
		
	}
}
