package com.github.lucasjalves.projetoles.controller;

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
	public ModelAndView paginaCadastroUsuario(ModelAndView modelView) {
		String json = "{}";
		try {
			json = mapper.writeValueAsString(facade.buscar(new Departamento()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		modelView.setViewName(PAGINA_CADASTRO_USUARIO);
		modelView.addObject("jsonListaDepartamentos", json);
		return modelView;
	}
	
}
