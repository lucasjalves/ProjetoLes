package com.github.lucasjalves.projetoles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.lucasjalves.projetoles.entidade.Cliente;

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
	
}
