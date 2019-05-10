package com.github.lucasjalves.projetoles.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.lucasjalves.projetoles.entidade.Pedido;

@Controller
@RequestMapping("/grafico")
public class GraficoController extends ControllerBase{

	@SuppressWarnings("unchecked")
	@RequestMapping("")
	public ModelAndView paginaGrafico(ModelAndView modelView) throws JsonProcessingException {
		List<Pedido> pedidos = 
				(List<Pedido>) facade.consultar(new Pedido()).getEntidades();
		modelView.setViewName("painel/admin/graficos");
		modelView.addObject("pedidos", mapper.writeValueAsString(pedidos));
		return modelView;
	}
}
