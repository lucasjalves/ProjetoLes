package com.github.lucasjalves.projetoles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService service;
	
	@Autowired
	ObjectMapper mapper;
	
	@RequestMapping("/cadastro")
	public ModelAndView paginaCadastroCliente(ModelAndView modelView) {
		modelView.setViewName("cliente/cadastro");
		return modelView;
	}
	
	@RequestMapping("/cadastrar")
	@ResponseBody
	public Resultado cadastrarCliente(@ModelAttribute Cliente cliente) {
		return service.cadastrar(cliente);
	}
	
	@RequestMapping("/consulta")
	public ModelAndView paginaConsultaCliente(ModelAndView modelView) throws JsonProcessingException {
		modelView.setViewName("cliente/consulta");
		List<Cliente> listaTodosClientes = (List<Cliente>) service.consultar(new Cliente()).getEntidades();
		modelView.addObject("listaClientes", mapper.writeValueAsString(listaTodosClientes));
		return modelView;
	}
	
	@RequestMapping("/deletar")
	@ResponseBody
	public Resultado deletarCliente(@ModelAttribute Cliente cliente) {
		service.deletar(cliente);
		return service.consultar(new Cliente());
	}
	
	@RequestMapping("/alteracao")
	public ModelAndView paginaAlterarCliente(@RequestParam String id) {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("cliente/alterar");
		Cliente cliente = new Cliente();
		cliente.setId(Long.parseLong(id));
		Cliente clienteSelecionado = (Cliente) service.consultarPorId(cliente).getEntidades().get(0);
		modelView.addObject("cliente", clienteSelecionado);
		
		return modelView;
	}
	
	@RequestMapping("/alterar")
	@ResponseBody
	public Resultado alterarCliente(@ModelAttribute Cliente cliente) {
		return service.alterar(cliente);
	}	
	
}
