package com.github.lucasjalves.projetoles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.lucasjalves.projetoles.entidade.Chamado;

@Controller
@RequestMapping("/chamado")
final class ChamadoController {

	
	@RequestMapping("/pagina")
	public String paginaPrincipal() {
		return "NewFile";
	}
	
	public String cadastrarChamado(@ModelAttribute("SpringWeb")Chamado chamado) {
		return "NewFile";
	}
}
