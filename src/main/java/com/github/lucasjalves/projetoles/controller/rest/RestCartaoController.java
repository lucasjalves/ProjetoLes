package com.github.lucasjalves.projetoles.controller.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("/get")
	public Resultado getCartoes() {
		Resultado resultado = new Resultado();
		resultado.setEntidades(getCliente().getCartoes());
		return resultado;
	}
	
	@PostMapping("/cadastrar")
	public Resultado cadastrar(@RequestBody CartaoCredito cartao) {
		Cliente cliente = getCliente();
		Resultado resultado = facade.salvar(cartao);
		if(resultado.getMensagem().isEmpty()) {
			cliente.getCartoes().add(cartao);
			return facade.alterar(cliente);
		}
		return resultado;
	}
}
