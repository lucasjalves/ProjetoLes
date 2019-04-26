package com.github.lucasjalves.projetoles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.entidade.Endereco;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Controller
@RequestMapping("/endereco")
public class EnderecoController extends ControllerBase {
	
	@ResponseBody
	@RequestMapping("/cadastrar")
	public Resultado cadastrar(@ModelAttribute Endereco endereco) {
		Cliente cliente = getCliente();
		
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
	
	@ResponseBody
	@RequestMapping("/adicionar")
	public Resultado adicionar(@ModelAttribute Endereco endereco) {
		return facade.salvar(endereco);

	}
	@RequestMapping("/consulta")
	public ModelAndView consulta(ModelAndView modelView) {
		modelView.setViewName("painel/iframes/enderecos");
		return modelView;
	}
}
