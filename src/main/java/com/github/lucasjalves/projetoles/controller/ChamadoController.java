package com.github.lucasjalves.projetoles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.lucasjalves.projetoles.command.Command;
import com.github.lucasjalves.projetoles.command.impl.SalvarCommand;
import com.github.lucasjalves.projetoles.entidade.Chamado;
import com.github.lucasjalves.projetoles.repository.ChamadoRepository;

@Controller
@RequestMapping("/chamado")
public class ChamadoController {

	private Command command;
	
	@Autowired
	private ChamadoRepository repository;
	
	@RequestMapping("/pagina")
	public String paginaPrincipal() {
		return "NewFile";
	}
	
	public String cadastrarChamado(@ModelAttribute("SpringWeb")Chamado chamado) {
		command = new SalvarCommand();
		command.execute(chamado, repository);
		return "NewFile";
	}
}
