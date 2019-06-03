package com.github.lucasjalves.projetoles.controller.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.lucasjalves.projetoles.controller.ControllerBase;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.enums.TipoUsuario;
import com.github.lucasjalves.projetoles.rns.Resultado;

@RestController
@RequestMapping("/rest/cliente")
@CrossOrigin
public class RestClienteController extends ControllerBase{

	@PostMapping("/cadastrar")
	public Resultado cadastrar(@RequestBody Cliente cliente) {
		cliente.setTipoUsuario(TipoUsuario.COMUM);
		return new Resultado("Nome vazio!");
	}
	
	@PostMapping("/login")
	public Resultado login(@RequestBody Cliente cliente) {
		Resultado resultado = facade.consultar(cliente);
		if(resultado.getEntidades().isEmpty()) {
			return new Resultado("Usuário não encontrado");
		} else {
			httpSession.setAttribute("cliente", (Cliente) resultado.getEntidades().get(0));
			return new Resultado();
		}
	}
}
