package com.github.lucasjalves.projetoles.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.lucasjalves.projetoles.entidade.Departamento;
import com.github.lucasjalves.projetoles.rns.Mensagem;

@Controller("/departamento")
public class DepartamentoController extends ControllerBase{

	@RequestMapping("/cadastrar")
	@ResponseBody
	public List<Mensagem> cadastrar(@RequestBody Departamento departamento){
		return facade.salvar(departamento).getMensagem();
	}
	
	@RequestMapping("/consultar")
	@ResponseBody
	public List<Object> buscar(){
		return facade.buscar(new Departamento()).getEntidades();
	}
	
	
}
