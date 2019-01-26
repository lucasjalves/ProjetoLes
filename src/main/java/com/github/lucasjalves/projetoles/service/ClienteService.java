package com.github.lucasjalves.projetoles.service;

import org.springframework.stereotype.Service;

import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Service
public interface ClienteService {

	public Resultado cadastrar(Cliente cliente);
	public Resultado consultar(Entidade entidade);
	public Resultado deletar(Cliente cliente);
	public Resultado consultarPorId(Cliente cliente);
	public Resultado alterar(Cliente cliente);
}
