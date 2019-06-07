package com.github.lucasjalves.projetoles.controller.rest;

import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.lucasjalves.projetoles.controller.ControllerBase;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.Endereco;
import com.github.lucasjalves.projetoles.rns.Resultado;

@RestController
@CrossOrigin
@RequestMapping("rest/endereco")
public class RestEnderecoController extends ControllerBase {

	@GetMapping("getAll/{cpf}")
	public Resultado getEnderecos(@PathVariable String cpf) {
		Resultado resultado = new Resultado();
		Cliente cliente = 
				(Cliente) facade.consultar(new Cliente().withCpf(cpf)).getEntidades().get(0);
		
		resultado.setEntidades(cliente.getEnderecos().stream().filter(Endereco::getAtivo).collect(Collectors.toList()));
		return resultado;
	}
	
	@PostMapping("salvar/{cpf}")
	public Resultado salvar(@PathVariable String cpf, @RequestBody Endereco endereco) {
		Cliente cliente = 
				(Cliente) facade.consultar(new Cliente().withCpf(cpf)).getEntidades().get(0);
		
		Resultado res = facade.salvar(endereco);
		if(res.getMensagem().isEmpty()) {
			endereco.setAtivo(true);
			cliente.getEnderecos().add(endereco);
			return facade.alterar(cliente);
		}
		return res;
	}
	
	@GetMapping("get/{id}")
	public Resultado getById(@PathVariable Long id) {
		return facade.consultar(new Endereco().withId(id));
	}
	
	@DeleteMapping("deletar/{id}")
	public Resultado deletar(@PathVariable Long id) {
		Endereco endereco = 
				(Endereco) facade.consultar(new Endereco().withId(id)).getEntidades().get(0);
		
		endereco.setAtivo(false);
		
		return facade.alterar(endereco);
	}
	
	@PutMapping("alterar")
	public Resultado alterar(@RequestBody Endereco endereco) {
		endereco.setAtivo(true);
		return facade.alterar(endereco);
	}
}
