package com.github.lucasjalves.projetoles.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.lucasjalves.projetoles.entidade.Sla;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Service
public interface SlaService {

	Resultado salvar(Sla sla);
	Resultado alterar(Sla sla);
	List<Sla> buscar(Sla sla);
	Resultado excluir(Sla sla);
}
