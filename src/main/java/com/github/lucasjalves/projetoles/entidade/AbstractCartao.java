package com.github.lucasjalves.projetoles.entidade;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractCartao extends Entidade {
	private String bandeira;
	private String cvv;
	private String numero;
	private String dtVencimento;
	public String getBandeira() {
		return bandeira;
	}
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDtVencimento() {
		return dtVencimento;
	}
	public void setDtVencimento(String dtVencimento) {
		this.dtVencimento = dtVencimento;
	}
	
}
