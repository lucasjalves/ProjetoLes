package com.github.lucasjalves.projetoles.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.Entidade;
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

	@Override
	public Resultado consultar(Entidade entidade) {
		return facade.buscar(entidade);
	}

	@Override
	public Resultado deletar(Long id) throws Exception {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		Resultado resultado = facade.buscarPorId(cliente);
		List<Cliente> listaClientes = 
				(List<Cliente>) resultado.getEntidades();
		
		if(listaClientes.isEmpty()) {
			throw new Exception("Cliente não encontrado!");
		}
		
		Cliente c = listaClientes.get(0);
		c.setAtivo(false);
		return facade.alterar(c);
	}

	@Override
	public Resultado consultarPorId(String id) throws Exception{
		Cliente cliente = new Cliente();
		cliente.setId(Long.parseLong(id));
		Resultado resultado = facade.buscarPorId(cliente);
		if(resultado.getEntidades().isEmpty()) {
			throw new Exception("Cliente não encontrado! ");
		}
		return resultado;
	}

	@Override
	public Resultado alterar(Cliente cliente) {
		return facade.alterar(cliente);
	}
	
	
}
