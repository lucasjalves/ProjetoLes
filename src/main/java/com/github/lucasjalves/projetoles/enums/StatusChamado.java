package com.github.lucasjalves.projetoles.enums;

public enum StatusChamado {

	CANCELADO("cancelado"),
	PROGRESSO("progresso"),
	APROVADO("aprovado"),
	CONCLUIDO("concluido"),
	REPROVADO("reprovado"),
	ANALISE("analise");
	private String status;
	
	StatusChamado(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}

}
