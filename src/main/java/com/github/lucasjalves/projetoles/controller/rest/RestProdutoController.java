package com.github.lucasjalves.projetoles.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.lucasjalves.projetoles.controller.ControllerBase;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.rns.Resultado;

@CrossOrigin
@RestController
@RequestMapping("/rest/produto")
public class RestProdutoController extends ControllerBase{

	@Autowired
	private Facade facade;
	
	@GetMapping("/consulta/todos")
	public Resultado consultarTodos() {
		return facade.consultar(new Produto());
	}
	
	@GetMapping("/consulta/{id}")
	public Resultado consultarId(@PathVariable Long id) {
		return facade.consultar(new Produto().withId(id));
	}
}
