package com.github.lucasjalves.projetoles.controller.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.lucasjalves.projetoles.controller.ControllerBase;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.rns.Resultado;

@RestController
@RequestMapping("rest/ticket")
@CrossOrigin
public class RestTicketController extends ControllerBase{

	@GetMapping("/cliente/{cpf}")
	public Resultado getTicketsByCliente(@PathVariable String cpf) {
		Resultado resultadoConsultaCliente = 
				facade.consultar(new Cliente().withCpf(cpf));
		
		Cliente cliente = (Cliente) resultadoConsultaCliente.getEntidades().get(0);
		Resultado resultadoConsultaTickets = new Resultado();
		resultadoConsultaTickets.setEntidades(cliente.getTickets());
		
		return resultadoConsultaTickets;
	}
}
