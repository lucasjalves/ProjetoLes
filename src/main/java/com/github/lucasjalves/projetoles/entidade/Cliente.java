package com.github.lucasjalves.projetoles.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.github.lucasjalves.projetoles.enums.TipoUsuario;

@Entity
public class Cliente extends Entidade {

	private String cpfCnpj;
	private String nome;
	private String dtNascimento;
	private String username;
	private String senha;
	private String email;
	private String genero;
	private Boolean ativo;
	private TipoUsuario tipoUsuario;
	
	@OneToMany(cascade=CascadeType.ALL)
	List<Pedido> pedidos = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL)
	List<Ticket> tickets = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	List<Endereco> enderecos = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL)
	List<CartaoCredito> cartoes = new ArrayList<>();
	public Cliente withId(Long id) {
		this.setId(id);
		return this;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	public List<CartaoCredito> getCartoes() {
		return cartoes;
	}
	public void setCartoes(List<CartaoCredito> cartoes) {
		this.cartoes = cartoes;
	}
	public Cliente withEndereco(Endereco endereco) {
		this.enderecos.add(endereco);
		return this;
	}
	
}
