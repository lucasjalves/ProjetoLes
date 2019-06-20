package com.github.lucasjalves.projetoles.controller.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.lucasjalves.projetoles.controller.ControllerBase;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.rns.Resultado;

@RestController
@RequestMapping("/rest/carrinho")
@CrossOrigin
public class RestCarrinhoController extends ControllerBase{

	@GetMapping("/adicionar/{id}")
	public Resultado validarQtde(@PathVariable Long id, @RequestParam Integer qtde) {
		Produto produto = (Produto) facade.consultar(new Produto().withId(id)).getEntidades().get(0);
		produto.setQuantidadeSelecionada(qtde);
		return facade.consultar(produto);
	}
}
