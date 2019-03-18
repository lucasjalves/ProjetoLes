package com.github.lucasjalves.projetoles.entidade;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Cupom extends Entidade{

	private String codigo;
	private Boolean status;
	private String dataVencimento;
	private Double valorDesconto = 0.00;
	@Transient
	private boolean validar;
	
	
	public Cupom withId(Long id) {
		this.setId(id);
		return this;
	}
	
	public Cupom withValidar(boolean validar) {
		this.validar = validar;
		return this;
	}
	
	public boolean isValidar() {
		return validar;
	}
	public void setValidar(boolean validar) {
		this.validar = validar;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Double getValorDesconto() {
		return valorDesconto;
	}
	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
		
	
}
