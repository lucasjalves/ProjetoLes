package com.github.lucasjalves.projetoles.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import com.github.lucasjalves.projetoles.enums.TipoUsuario;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.service.ClienteService;

@Controller
public class ClienteController {

	@Autowired
	ClienteService service;
	
	@Autowired
	ObjectMapper mapper;
	
	@RequestMapping("/cliente/cadastro")
	public ModelAndView paginaCadastroCliente(ModelAndView modelView) {
		modelView.setViewName("cliente/cadastro");
		return modelView;
	}
	
	@RequestMapping("/cliente/cadastrar")
	@ResponseBody
	public Resultado cadastrarCliente(@ModelAttribute Cliente cliente) {
		cliente.setTipoUsuario(TipoUsuario.ADMIN);
		return service.cadastrar(cliente);
	}
	
	@RequestMapping("/cliente/consulta")
	public ModelAndView paginaConsultaCliente(ModelAndView modelView) throws JsonProcessingException {
		modelView.setViewName("cliente/consulta");
		List<Cliente> listaTodosClientes = (List<Cliente>) service.consultar(new Cliente()).getEntidades();
		modelView.addObject("listaClientes", mapper.writeValueAsString(listaTodosClientes));
		return modelView;
	}
	
	@RequestMapping("/cliente/deletar")
	@ResponseBody
	public Resultado deletarCliente(@ModelAttribute Cliente cliente) {
		service.deletar(cliente);
		return service.consultar(new Cliente());
	}
	
	@RequestMapping("/cliente/alteracao")
	public ModelAndView paginaAlterarCliente(@RequestParam String id) {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("cliente/alterar");
		Cliente cliente = new Cliente();
		cliente.setId(Long.parseLong(id));
		Cliente clienteSelecionado = (Cliente) service.consultarPorId(cliente).getEntidades().get(0);
		modelView.addObject("cliente", clienteSelecionado);
		
		return modelView;
	}
	
	@RequestMapping("/cliente/alterar")
	@ResponseBody
	public Resultado alterarCliente(@ModelAttribute Cliente cliente) {
		return service.alterar(cliente);
	}	
	
	@RequestMapping("/cliente/dados")
	public ModelAndView dados(ModelAndView modelView) {
		modelView.setViewName("painel/iframes/dados");
		return modelView;
	}
	
	@RequestMapping("/cliente/detalhe")
	public ModelAndView detalhe(ModelAndView modelView) {
		modelView.setViewName("painel/admin/detalheUsuario");
		return modelView;
	}

	@RequestMapping("/cliente/login")
	public ModelAndView login(ModelAndView modelView) {
		modelView.setViewName("cliente/login");
		return modelView;
	}
	
	@ResponseBody
	@RequestMapping("/cliente/efetuarLogin")
	public Boolean login(@ModelAttribute Cliente cliente) throws Exception {
		List<Cliente> clientes = 
				(List<Cliente>) service.consultar(cliente).getEntidades();
		
		if(clientes.contains(cliente)) {
			throw new Exception("Usuário não encontrado");
		}
		Cliente cli = clientes.get(0);
		return cli.getTipoUsuario().equals(TipoUsuario.ADMIN);
	}
	
	@RequestMapping("/painel")
	public ModelAndView painel(ModelAndView modelView) {
		modelView.setViewName("painel/painel");
		return modelView;
	}
	
}
