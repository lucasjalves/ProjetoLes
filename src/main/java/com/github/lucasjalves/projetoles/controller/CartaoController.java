package com.github.lucasjalves.projetoles.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.lucasjalves.projetoles.entidade.CartaoCredito;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Controller
@RequestMapping("/cartao")
public class CartaoController extends ControllerBase{

	@RequestMapping("/consulta")
	public ModelAndView consulta(ModelAndView modelView) throws JsonProcessingException {
		Cliente cliente = getCliente();
		modelView.addObject("cartoes", mapper.writeValueAsString(cliente.getCartoes()));
		modelView.setViewName("painel/iframes/cartoes");
		return modelView;
	}
	
	
	@ResponseBody
	@RequestMapping("/cadastrar")
	public Resultado cadastrar(@ModelAttribute CartaoCredito cartao) {
		Cliente cliente = getCliente();
		
		if(cliente == null) {
			return new Resultado("Cliente não logado!");
		}
		
		Resultado res = facade.salvar(cartao);
		if(res.getMensagem().isEmpty()) {
			cliente.getCartoes().add(cartao);
			res = facade.alterar(cliente);
		}
		return res;		
	}
	
	@RequestMapping("/paginaCadastro")
	public ModelAndView paginaCadastro(ModelAndView modelView) {
		modelView.setViewName("painel/iframes/cartao/cadastrar");
		return modelView;
	}
	
	@ResponseBody
	@RequestMapping("/alterar")
	public Resultado alterar(@ModelAttribute CartaoCredito cartao) {
		Resultado r = facade.salvar(cartao);
		if(!r.getMensagem().isEmpty()) {
			return r;
		}
		return facade.alterar(cartao);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/detalhe")
	public ModelAndView detalhe(@RequestParam Long id, ModelAndView modelView) throws Exception {
		Optional<CartaoCredito> c = 
				(Optional<CartaoCredito>) facade.consultar(new CartaoCredito().withId(id))
				.getEntidades()
					.stream()
					.findFirst();
		
		if(!c.isPresent()) {
			throw new Exception("Cartão não encontrado "+ id);
		}
		modelView.addObject("cartao", c.get());
		modelView.setViewName("painel/iframes/cartao/detalhe");
		return modelView;
	}
}
