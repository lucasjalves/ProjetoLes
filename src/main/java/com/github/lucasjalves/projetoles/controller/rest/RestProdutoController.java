package com.github.lucasjalves.projetoles.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.lucasjalves.projetoles.controller.ControllerBase;
import com.github.lucasjalves.projetoles.entidade.Pedido;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.enums.Categorias;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.rns.Resultado;

@CrossOrigin
@RestController
@RequestMapping("/rest/produto")
public class RestProdutoController extends ControllerBase{

	@Autowired
	private Facade facade;
	
	@GetMapping("/consulta/todos")
	public Resultado consultarTodos(@RequestParam boolean admin) {
		if (admin) {
			return facade.consultar(new Produto());			
		}
		return facade.consultar(new Produto().withAtivo(true));
	}
	
	@GetMapping("/consulta/{id}")
	public Resultado consultarId(@PathVariable Long id) {
		return facade.consultar(new Produto().withId(id));
	}
	
	@GetMapping("/categorias")
	public List<String> getCategorias() {
		List<String> categorias = new ArrayList<>();
		for(Categorias c: Categorias.values()) {
			categorias.add(c.toString());
		}
		return categorias;
	}
	
	@PostMapping("/salvar")
	public Resultado salvar(@RequestBody Produto produto) throws JsonProcessingException {
		return facade.salvar(produto);
	}
}
