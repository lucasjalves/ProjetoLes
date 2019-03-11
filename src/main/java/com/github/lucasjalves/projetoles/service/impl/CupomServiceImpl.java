package com.github.lucasjalves.projetoles.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.lucasjalves.projetoles.entidade.Cupom;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.service.CupomService;

@Service
public class CupomServiceImpl implements CupomService {

	@Autowired
	private Facade facade;
	@Override
	public Resultado cadastrar(Cupom cupom) {
		return facade.salvar(cupom);
	}

	@Override
	public Resultado consultar(Cupom cupom) {
		return facade.buscar(cupom);
	}

	@Override
	public Resultado desativar(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resultado consultarPorId(Long id) throws Exception {
		Cupom cupom = new Cupom();
		cupom.setId(id);
		Resultado resultado = 
				facade.buscarPorId(cupom);
		
		List<Cupom> produtos = 
				(List<Cupom>) resultado.getEntidades();
		
		if(produtos.isEmpty()) {
			throw new Exception("Cupom não encontrado");
		}
		
		return resultado;
	}

	@Override
	public Resultado alterar(Cupom cupom) {
		return facade.alterar(cupom);
	}

}
