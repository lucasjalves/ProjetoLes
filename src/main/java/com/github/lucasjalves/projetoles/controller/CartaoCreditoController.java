package com.github.lucasjalves.projetoles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.lucasjalves.projetoles.entidade.CartaoCredito;
import com.github.lucasjalves.projetoles.entidade.Cliente;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Controller
@RequestMapping("/cartao")
public class CartaoCreditoController extends ControllerBase{

	@Autowired
	private Facade facade;
	
	@ResponseBody
	@RequestMapping("/cadastrar")
	public Resultado cadastrar(CartaoCredito cartao) {
		Cliente cliente = 
				(Cliente) httpSession.getAttribute("cliente");
		
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
}
