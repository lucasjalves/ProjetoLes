package com.github.lucasjalves.projetoles.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.enums.TipoUsuario;
import com.github.lucasjalves.projetoles.rns.Resultado;
@SuppressWarnings("unchecked")
@Controller
public class ClienteController extends ControllerBase {

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
	public Resultado alterarCliente(@ModelAttribute Cliente cliente) throws Exception {
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
	
	@RequestMapping("/cliente/dados")
	public ModelAndView dados(ModelAndView modelView) {
		Cliente cliente = getCliente();
		modelView.setViewName("painel/iframes/dados");
		modelView.addObject("cliente", cliente);
		return modelView;
	}

	@RequestMapping("/cliente/logout")
	public String logout() {
		httpSession.removeAttribute("cliente");
		return "forward:/";
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
	public Resultado login(@ModelAttribute Cliente cliente) throws Exception {
		List<Cliente> clientes = 
				(List<Cliente>) facade.consultar(cliente).getEntidades();
		
		if(clientes.isEmpty()) {
			return new Resultado("Usuário não encontrado");
		}
		Cliente cli = clientes.get(0);
		httpSession.setAttribute("cliente", cli);
		
		return new Resultado();
	}
	
	@RequestMapping("/painel")
	public ModelAndView painel(ModelAndView modelView) {
		modelView.setViewName("painel/painel");
		return modelView;
	}
	
	@ResponseBody
	@RequestMapping("/cliente/logado")
	public Boolean logado() {
		Cliente cliente =
				(Cliente) httpSession.getAttribute("cliente");
		
		if(cliente == null){
			return null;
		}
		
		return true;
	}
	
	@RequestMapping("/cliente/todos")
	public ModelAndView todosUsuarios(ModelAndView modelView) throws JsonProcessingException {
		List<Cliente> clientes = (List<Cliente>) facade.consultar(new Cliente()).getEntidades();
		modelView.setViewName("painel/admin/usuarios");
		modelView.addObject("usuarios", mapper.writeValueAsString(clientes));
		return modelView;
	}
}
