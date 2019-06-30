package com.github.lucasjalves.projetoles.controller.rest;

import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		cliente.setTipoUsuario(TipoUsuario.ADMIN);
		return facade.salvar(cliente);
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
	
	@GetMapping("/get/{cpf}")
	public Resultado get(@PathVariable String cpf) {
		return facade.consultar(new Cliente().withCpf(cpf));
	}
	
	@GetMapping("/getById/{id}")
	public Resultado getById(@PathVariable Long id) {
		return facade.consultar(new Cliente().withId(id));
	}
	
	@PutMapping("/alterar")
	public Resultado alterar(@RequestBody Cliente cliente) throws Exception {
		Optional<Cliente> optional = 
				(Optional<Cliente>) facade.consultar(new Cliente().withId(cliente.getId())).getEntidades().stream().findFirst();
		
		if(!optional.isPresent()) {
			throw new Exception("Cliente não encontrado para alteração " + cliente.getId());
		}
		
		Cliente c = optional.get();
		cliente.setCreditoDisponivel(c.getCreditoDisponivel());
		cliente.setCartoes(c.getCartoes());
		cliente.setEnderecos(c.getEnderecos());
		cliente.setPedidos(c.getPedidos());
		cliente.setTickets(c.getTickets());
		return facade.alterar(cliente);
	}
	
	
}
