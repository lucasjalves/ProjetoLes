package com.github.lucasjalves.projetoles.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Service
public interface ClienteService {

	public Resultado cadastrar(Cliente cliente);
	public List<Cliente> consultar(Entidade entidade);
	public Resultado deletar(Cliente cliente);
}
