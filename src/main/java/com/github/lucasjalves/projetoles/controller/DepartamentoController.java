package com.github.lucasjalves.projetoles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.lucasjalves.projetoles.entidade.Departamento;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.service.DepartamentoService;

@Controller
final class DepartamentoController{

	@Autowired
	private DepartamentoService service;
	
	@PostMapping("/departamento/cadastrar")
	@ResponseBody
	public Resultado cadastrar(@RequestBody Departamento departamento){
		return service.salvar(departamento);
	}
	
	@PostMapping("/departamento/alterar")
	@ResponseBody
	public Resultado alterar(@RequestBody Departamento departamento) {
		return service.alterar(departamento);
	}

	@GetMapping("/departamento/consultar")
	@ResponseBody
	public List<Departamento> buscar(){
		return service.buscarTodos();
	}
	

	
}
