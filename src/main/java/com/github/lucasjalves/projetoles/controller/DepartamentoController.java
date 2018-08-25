package com.github.lucasjalves.projetoles.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.lucasjalves.projetoles.entidade.Departamento;
import com.github.lucasjalves.projetoles.rns.Mensagem;

@Controller
public class DepartamentoController extends ControllerBase{

	@PostMapping("/departamento/cadastrar")
	@ResponseBody
	public List<Mensagem> cadastrar(@RequestBody Departamento departamento){
		int listSize = departamento.getSetores().size();
		if(listSize > 1) {
			if(departamento.getSetores().get(listSize -1).getNome().trim().length() == 0) {
				departamento.getSetores().remove(listSize - 1); 
			}
		}
		return facade.salvar(departamento).getMensagem();
	}
	

	@GetMapping("/departamento/consultar")
	@ResponseBody
	public List<Departamento> buscar(){
		return (List<Departamento>) facade.buscar(new Departamento()).getEntidades();
	}
	
	
}
