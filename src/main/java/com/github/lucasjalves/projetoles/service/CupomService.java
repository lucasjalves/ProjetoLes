package com.github.lucasjalves.projetoles.service;

import org.springframework.stereotype.Service;

import com.github.lucasjalves.projetoles.entidade.Cupom;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Service
public interface CupomService {

	 Resultado cadastrar(Cupom cupom);
	 Resultado consultar(Cupom cupom);
	 Resultado desativar(Long id) throws Exception ;
	 Resultado consultarPorId(Long id) throws Exception;
	 Resultado alterar(Cupom cupom);
	 
}
