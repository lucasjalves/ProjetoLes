package com.github.lucasjalves.projetoles.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.facade.Facade;

@Controller
class ControllerBase {
	
	
	@Autowired
	protected HttpSession httpSession;
	
	@Autowired
	protected ObjectMapper mapper;
	
	@Autowired
	protected Facade facade;
	
	protected Cliente getCliente() {
		Cliente c = (Cliente) httpSession.getAttribute("cliente");
		
		if(c == null) {
			return null;
		}
		
		return (Cliente) facade.consultar(c).getEntidades().stream().findFirst().get();
	}
	
	protected Cliente alterarClienteSessao(Cliente cliente) {
		facade.alterar(cliente);
		return getCliente();
	}
}
