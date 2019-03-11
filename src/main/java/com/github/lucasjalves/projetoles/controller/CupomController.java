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
import com.github.lucasjalves.projetoles.entidade.Cupom;
import com.github.lucasjalves.projetoles.entidade.Produto;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.service.CupomService;

@Controller
@RequestMapping("/cupom")
public class CupomController {

	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private CupomService service;
	@RequestMapping("/cadastro")
	public ModelAndView paginaCadastroProduto(ModelAndView modelView) {
		modelView.setViewName("cupom/cadastro");
		return modelView;
	}
	
	@RequestMapping("/consulta")
	public ModelAndView paginaConsultaProduto(ModelAndView modelView) throws JsonProcessingException {
		Resultado resultado = 
				service.consultar(new Cupom());
		
		List<Cupom> lista = 
				(List<Cupom>) resultado.getEntidades();
		
		modelView.setViewName("cupom/consulta");
		modelView.addObject("cupons", mapper.writeValueAsString(lista));
		return modelView;
	}
	
	@ResponseBody
	@RequestMapping("/alterar")
	public Resultado alterar(@ModelAttribute Cupom cupom) {
		return service.alterar(cupom);
	}
	
	@RequestMapping("/alteracao")
	public ModelAndView paginaAlteracaoProduto(ModelAndView modelView, @RequestParam Long id) throws Exception {
		Cupom cupom = 
				(Cupom) service.consultarPorId(id).getEntidades().get(0);
		modelView.setViewName("cupom/alterar");
		modelView.addObject("cupom", cupom);
		return modelView;
	}
	
	@ResponseBody
	@RequestMapping("/cadastrar")
	public Resultado cadastrar(@ModelAttribute Cupom cupom) {
		return service.cadastrar(cupom);
	}
}
