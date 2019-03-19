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
import com.github.lucasjalves.projetoles.enums.TipoUsuario;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.rns.Resultado;
@SuppressWarnings("unchecked")
@Controller
public class ClienteController extends ControllerBase {

	@Autowired
	private Facade facade;
	
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
		return facade.salvar(cliente);
	}
	
	@RequestMapping("/cliente/consulta")
	public ModelAndView paginaConsultaCliente(ModelAndView modelView) throws JsonProcessingException {
		modelView.setViewName("cliente/consulta");
		
		List<Cliente> listaTodosClientes = (List<Cliente>) facade.consultar(new Cliente()).getEntidades();
		modelView.addObject("listaClientes", mapper.writeValueAsString(listaTodosClientes));
		return modelView;
	}
	
	@RequestMapping("/cliente/deletar")
	@ResponseBody
	public Resultado deletarCliente(@ModelAttribute Cliente cliente) {
		Cliente c = (Cliente) facade.consultar(cliente).getEntidades().get(0);
		c.setAtivo(false);
		return facade.alterar(c);
	}
	
	@RequestMapping("/cliente/alteracao")
	public ModelAndView paginaAlterarCliente(@RequestParam Long id) throws Exception {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("cliente/alterar");
		Cliente cliente = (Cliente) facade.consultar(new Cliente().withId(id)).getEntidades().get(0);
		modelView.addObject("cliente", cliente);
		
		return modelView;
	}
	
	@RequestMapping("/cliente/alterar")
	@ResponseBody
	public Resultado alterarCliente(@ModelAttribute Cliente cliente) {
		return facade.alterar(cliente);
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
				(List<Cliente>) facade.consultar(cliente).getEntidades();
		
		if(clientes.isEmpty()) {
			throw new Exception("Cliente não encontrado");
		}
		Cliente cli = clientes.get(0);
		httpSession.setAttribute("cliente", cli);
		return cli.getTipoUsuario().equals(TipoUsuario.ADMIN);
	}
	
	@RequestMapping("/painel")
	public ModelAndView painel(ModelAndView modelView) {
		modelView.setViewName("painel/painel");
		return modelView;
	}
	
}
