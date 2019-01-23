package com.github.lucasjalves.projetoles.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired Facade facade;

	@Override
	public Resultado cadastrar(Cliente cliente) {
		return facade.salvar(cliente);
	}
	
	
}
