package com.github.lucasjalves.projetoles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.lucasjalves.projetoles.entidade.Servico;

@Controller
@RequestMapping("/servico")
public class ServicoController {

	private static final String PAGINA_CADASTOR_SERVICO = "servico/cadastrarServico";
	private static final String PAGINA_CONSULTA_SERVICO = "servico/todosServicosTabela";
	
	@RequestMapping("/paginaCadastro")
	public ModelAndView paginaCadastroServico(ModelAndView modelView) {
		modelView.setViewName(PAGINA_CADASTOR_SERVICO);
		return modelView;
	}
	
	
	@RequestMapping("/paginaConsulta")
	public ModelAndView paginaConsultaServicos(ModelAndView modelView) {
		modelView.setViewName(PAGINA_CONSULTA_SERVICO);
		return modelView;
	}
	
	public String efetivarCadastro(@RequestBody Servico servico) {
		return "forward:/servico/paginaConsulta";
	}
	
	
}
