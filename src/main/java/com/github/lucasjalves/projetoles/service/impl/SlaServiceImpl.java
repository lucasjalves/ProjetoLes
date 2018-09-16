package com.github.lucasjalves.projetoles.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.lucasjalves.projetoles.entidade.Sla;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.service.SlaService;

@Service
public class SlaServiceImpl implements SlaService {

	@Autowired
	private Facade facade;
	
	@Override
	public Resultado salvar(Sla sla) {
		return facade.salvar(sla);
	}

	@Override
	public Resultado alterar(Sla sla) {
		return facade.alterar(sla);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sla> buscar(Sla sla) {
		return (List<Sla>) facade.buscar(new Sla()).getEntidades();
	}

	@Override
	public Resultado excluir(Sla sla) {
		// TODO Auto-generated method stub
		return null;
	}

}
