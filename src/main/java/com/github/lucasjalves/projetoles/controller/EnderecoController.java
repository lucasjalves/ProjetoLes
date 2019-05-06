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
	public ModelAndView consulta(ModelAndView modelView) throws JsonProcessingException {
		Cliente cliente = getCliente();
		List<Endereco> enderecos = cliente.getEnderecos();
		modelView.setViewName("painel/iframes/enderecos");
		modelView.addObject("enderecos", mapper.writeValueAsString(enderecos));
		return modelView;
	}
	
	@RequestMapping("/detalhe")
	public ModelAndView detalhe(ModelAndView modelView, @RequestParam Long id) throws Exception {
		modelView.setViewName("painel/iframes/endereco/detalhe");
		Optional<Endereco> e = (Optional<Endereco>) facade.consultar(new Endereco().withId(id)).getEntidades().stream().findFirst();
		if(!e.isPresent()) {
			throw new Exception("Endereco não encontrado " + id);
		}
		Endereco endereco = e.get();
		modelView.addObject("endereco", endereco);
		return modelView;
	}
	
	@ResponseBody
	@RequestMapping("/alterar")
	public Resultado alterar(@ModelAttribute Endereco endereco) {
		return facade.alterar(endereco);
	}
}
