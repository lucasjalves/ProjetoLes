package com.github.lucasjalves.projetoles.entidade;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class AbstractCupom extends Entidade{

	private String codigo;
	private Boolean status;
	private String dataVencimento;
	private Double valorDesconto = 0.00;
	@Transient
	private boolean validar;
	
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
		
	@Override
    public Object clone()throws CloneNotSupportedException{  
        return (Cupom)super.clone();  
    }
}
