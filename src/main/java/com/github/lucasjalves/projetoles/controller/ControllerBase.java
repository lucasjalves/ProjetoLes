package com.github.lucasjalves.projetoles.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lucasjalves.projetoles.command.impl.AlterarCommand;
import com.github.lucasjalves.projetoles.command.impl.ConsultarCommand;
import com.github.lucasjalves.projetoles.command.impl.ExcluirCommand;
import com.github.lucasjalves.projetoles.command.impl.SalvarCommand;

@Controller
public class ControllerBase {

	@Autowired
	protected ObjectMapper mapper;
	
	@Autowired
	protected HttpSession httpSession;
	
	@Autowired
	protected SalvarCommand salvar;
	
	@Autowired
	protected ConsultarCommand consultar;
	
	@Autowired
	protected ExcluirCommand excluir;
	
	@Autowired
	protected AlterarCommand alterar;
}
