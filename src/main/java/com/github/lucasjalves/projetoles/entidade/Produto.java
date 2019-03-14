package com.github.lucasjalves.projetoles.entidade;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.github.lucasjalves.projetoles.enums.Categorias;

@Entity
public class Produto extends Entidade{
	private String nome;
	private String descricao;
	private String precoCompra;
	private String precoVenda;
	private String marca;
	private String modelo;
	private String altura;
	private String largura;
	private String comprimento;
	private String peso;
	private String especificacoes;
	private String codigoBarras;
	private Categorias categoria;	
	@Transient
	private Integer quantidadeSelecionada;
	private Integer estoque;
	private String conteudoEmbalagem;
	private Boolean ativo;
	
	public Produto withAtivo(Boolean ativo) {
		this.ativo = ativo;
		return this;
	}

	public Produto withId(Long id) {
		this.setId(id);
		return this;
	}
	
	public Integer getQuantidadeSelecionada() {
		return quantidadeSelecionada;
	}

	public void setQuantidadeSelecionada(Integer quantidadeSelecionada) {
		this.quantidadeSelecionada = quantidadeSelecionada;
	}

	public Categorias getCategoria() {
		return categoria;
	}

	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getLargura() {
		return largura;
	}

	public void setLargura(String largura) {
		this.largura = largura;
	}

	public String getComprimento() {
		return comprimento;
	}

	public void setComprimento(String comprimento) {
		this.comprimento = comprimento;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getEspecificacoes() {
		return especificacoes;
	}

	public void setEspecificacoes(String especificacoes) {
		this.especificacoes = especificacoes;
	}

	public String getConteudoEmbalagem() {
		return conteudoEmbalagem;
	}

	public void setConteudoEmbalagem(String conteudoEmbalagem) {
		this.conteudoEmbalagem = conteudoEmbalagem;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public String getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(String precoCompra) {
		this.precoCompra = precoCompra;
	}

	public String getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(String precoVenda) {
		this.precoVenda = precoVenda;
	}

	
}
