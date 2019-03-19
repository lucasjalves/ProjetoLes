package com.github.lucasjalves.projetoles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.Endereco;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Controller
@RequestMapping("/endereco")
public class EnderecoController extends ControllerBase {

	@Autowired
	private Facade facade;
	
	@ResponseBody
	@RequestMapping("/cadastrar")
	public Resultado cadastrar(@ModelAttribute Endereco endereco) {
		Cliente cliente = 
				(Cliente) httpSession.getAttribute("cliente");
		
		if(cliente == null) {
			return new Resultado("Usuário não logado!");
		}
		
		Resultado resultado =
				facade.salvar(endereco);
		
		if(resultado.getMensagem().isEmpty()) {
			return facade.alterar(cliente.withEndereco(endereco));
		}
		return resultado;
	}
	@RequestMapping("/consulta")
	public ModelAndView consulta(ModelAndView modelView) {
		modelView.setViewName("painel/iframes/enderecos");
		return modelView;
	}
}
