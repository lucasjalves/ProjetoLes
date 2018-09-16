package com.github.lucasjalves.projetoles.entidade;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.github.lucasjalves.projetoles.enums.StatusCadastro;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nomePecaTi;
	private String nomeEletrico;
	private String nomeTelefonia;
	private String nomeFerramenta;
	private String qtde;
	private String dtCadastro;
	private String dtCompra;
	private String dtValidade;
	private String descricao;
	private String preco;
	private String fornecedor;
	private String modeloEletronico;
	private String modeloTelefonia;
	private String marcaEletronico;
	private String marcaPecaTelefonia;
	private String peso;
	   
	
	
	@Enumerated(EnumType.STRING)
	private StatusCadastro statusCadastro;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setQtde(String qtde) {
		this.qtde = qtde;
	}
	public String getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(String dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public StatusCadastro getStatusCadastro() {
		return statusCadastro;
	}
	public void setStatusCadastro(StatusCadastro statusCadastro) {
		this.statusCadastro = statusCadastro;
	}
	
	
	
}
