package com.github.lucasjalves.projetoles.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.lucasjalves.projetoles.entidade.Departamento;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.rns.Mensagem;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.service.DepartamentoService;

@Service
final class DepartamentoServiceImpl implements DepartamentoService{

	@Autowired
	private Facade facade;
	
	@Override
	public Resultado salvar(Departamento departamento) {
		Resultado resultado = processarResultado(departamento, "SALVAR");
		List<Departamento> list = (List<Departamento>) resultado.getEntidades();
		if(resultado.getMensagem().size() == 0) {
			resultado = facade.salvar(departamento);		
		}
		if(resultado.getMensagem().size() == 0) {
			list.add(departamento);
			resultado.setEntidades(list);
		}
		
		list = (List<Departamento>) resultado.getEntidades();
		list = list.stream()
				.sorted(Comparator.comparing(Departamento::getNome))
				.collect(Collectors.toList());
		resultado.setEntidades(list);
		return resultado;
	}



	@Override
	public List<Departamento> buscarTodos() {
		List<Departamento> lista = (List<Departamento>) facade.buscar(new Departamento()).getEntidades();
		return lista.stream().sorted(Comparator.comparing(Departamento::getNome)).collect(Collectors.toList());
	}



	@Override
	public Resultado alterar(Departamento departamento) {
		Resultado resultado = processarResultado(departamento, "ALTERAR");
		List<Departamento> list = (List<Departamento>) resultado.getEntidades();
		if(resultado.getMensagem().size() == 0) {
			resultado = facade.alterar(departamento);
		}
		if(resultado.getMensagem().size() == 0) {
			list = (List<Departamento>) facade.buscar(new Departamento()).getEntidades();
		}
		resultado.setEntidades(list);
		return resultado;
	}



	@Override
	public Resultado excluir(Departamento departamento) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Resultado processarResultado(Departamento departamento, String operacao) {
		Resultado resultado = facade.buscar(new Departamento());
		List<Departamento> list = (List<Departamento>) resultado.getEntidades();
		int listSize = departamento.getSetores().size();
		if (listSize > 1) {
			if (departamento.getSetores().get(listSize - 1).getNome().trim().length() == 0) {
				departamento.getSetores().remove(listSize - 1);
			}
		}
		if (operacao.equals("SALVAR")) {
			if (list.stream().filter(dp -> dp.getNome().trim().equalsIgnoreCase(departamento.getNome().trim()))
					.collect(Collectors.toList()).size() > 0) {
				resultado.setEntidades(new ArrayList<Departamento>());
				resultado.getMensagem().add(new Mensagem("Departamento já cadastrado"));
				return resultado;
			}
		}
		if (operacao.equals("ALTERAR")) {
			if (list.stream().filter(dp -> dp.getNome().trim().equalsIgnoreCase(departamento.getNome().trim()))
					.collect(Collectors.toList()).size() > 1) {
				resultado.setEntidades(new ArrayList<Departamento>());
				resultado.getMensagem().add(new Mensagem("Departamento já cadastrado"));
				return resultado;
			}

		}
		return resultado;
	}




}
