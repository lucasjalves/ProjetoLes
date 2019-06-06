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
import com.github.lucasjalves.projetoles.entidade.CartaoCredito;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.rns.Resultado;

@RestController
@RequestMapping("/rest/cartao")
@CrossOrigin
public class RestCartaoController extends ControllerBase{

	@GetMapping("/get/{cpf}")
	public Resultado getCartoes(@PathVariable String cpf) {
		Resultado resultado = new Resultado();
		Cliente cliente = (Cliente) facade.consultar(new Cliente().withCpf(cpf)).getEntidades().get(0);
		resultado.setEntidades(cliente.getCartoes().stream().filter(c -> c.getAtivo()).collect(Collectors.toList()));
		return resultado;
	}
	
	@PostMapping("/cadastrar/{cpf}")
	public Resultado cadastrar(@PathVariable String cpf, @RequestBody CartaoCredito cartao) {
		Cliente cliente = (Cliente) facade.consultar(new Cliente().withCpf(cpf)).getEntidades().get(0);
		cartao.setAtivo(true);
		Resultado resultado = facade.salvar(cartao);
		if(resultado.getMensagem().isEmpty()) {
			cliente.getCartoes().add(cartao);
			return facade.alterar(cliente);
		}
		return resultado;
	}
	
	@GetMapping("/get/{cpf}/{id}")
	public Resultado getCartoesId(@PathVariable String cpf, @PathVariable Long id) {
		return facade.consultar(new CartaoCredito().withId(id));
	}
	
	@PutMapping("/alterar")
	public Resultado alterar(@RequestBody CartaoCredito cartao) {
		return facade.alterar(cartao);
	}
	
	@DeleteMapping("/deletar/{id}")
	public Resultado deletar(@PathVariable Long id) {
		CartaoCredito cartao = 
				(CartaoCredito) facade.consultar(new CartaoCredito().withId(id)).getEntidades().get(0);

		cartao.setAtivo(false);
		return facade.alterar(cartao);
	}
}
