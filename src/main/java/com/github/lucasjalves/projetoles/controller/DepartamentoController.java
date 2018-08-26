package com.github.lucasjalves.projetoles.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.lucasjalves.projetoles.entidade.Departamento;
import com.github.lucasjalves.projetoles.rns.Mensagem;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Controller
public class DepartamentoController extends ControllerBase{

	@PostMapping("/departamento/cadastrar")
	@ResponseBody
	public Resultado cadastrar(@RequestBody Departamento departamento){
		Resultado resultado = facade.buscar(new Departamento());
		List<Departamento> list = (List<Departamento>) resultado.getEntidades();
		int listSize = departamento.getSetores().size();
		if(listSize > 1) {
			if(departamento.getSetores().get(listSize -1).getNome().trim().length() == 0) {
				departamento.getSetores().remove(listSize - 1); 
			}
		}
		if(list.stream().filter(dp -> dp.getNome().trim()
				.equalsIgnoreCase(departamento.getNome().trim()))
				.collect(Collectors.toList()).size() > 0) {
			resultado.setEntidades(new ArrayList<Departamento>());
			resultado.getMensagem().add(new Mensagem("Departamento já cadastrado"));
			return resultado;
		}
		
		resultado = facade.salvar(departamento);
		if(resultado.getMensagem().size() == 0) {
			list.add(departamento);
			resultado.setEntidades(list);
		}
		
		list = (List<Departamento>) resultado.getEntidades();
		list = list.stream().sorted(Comparator.comparing(Departamento::getNome)).collect(Collectors.toList());
		resultado.setEntidades(list);
			
		return resultado;
	}
	

	@GetMapping("/departamento/consultar")
	@ResponseBody
	public List<Departamento> buscar(){
		return (List<Departamento>) facade.buscar(new Departamento()).getEntidades();
	}
	
	
}
