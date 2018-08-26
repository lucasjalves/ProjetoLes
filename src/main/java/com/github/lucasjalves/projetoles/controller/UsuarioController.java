package com.github.lucasjalves.projetoles.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.lucasjalves.projetoles.entidade.Departamento;

@Controller
@RequestMapping("/usuario")
public class UsuarioController extends ControllerBase {

	private final String PAGINA_CADASTRO_USUARIO = "usuario/cadastrarUsuario";

	
	@RequestMapping("/cadastrar")
	public ModelAndView paginaCadastroUsuario(ModelAndView modelView) throws JsonProcessingException {
		String json = "{}";
		try {
			List<Departamento> list = (List<Departamento>) facade.buscar(new Departamento()).getEntidades();
			list = list.stream().sorted(Comparator.comparing(Departamento::getNome)).collect(Collectors.toList());
			json = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		modelView.setViewName(PAGINA_CADASTRO_USUARIO);
		modelView.addObject("jsonListaDepartamentos", json);
		return modelView;
	}
	
	@RequestMapping("/login")
	public ModelAndView paginaLogin(ModelAndView modelView) {
		modelView.setViewName("/usuario/login");
		return modelView;
	}
}
