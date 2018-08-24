package com.github.lucasjalves.projetoles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.lucasjalves.projetoles.entidade.Departamento;

@Controller
@RequestMapping("/usuario")
public class UsuarioController extends ControllerBase {

	private final String PAGINA_CADASTRO_USUARIO = "usuario/cadastrarUsuario";
	private final String PAGINA_LOGIN_USUARIO = "usuario/login";

	@RequestMapping("/cadastrar")
	public ModelAndView paginaCadastroUsuario(ModelAndView modelView) {
		modelView.setViewName(PAGINA_CADASTRO_USUARIO);
		consultar.execute(new Departamento());
		return modelView;
	}
	
	@RequestMapping("/login")
	public ModelAndView paginaLogin(ModelAndView modelView) {
		modelView.setViewName(PAGINA_LOGIN_USUARIO);
		consultar.execute(new Departamento());
		return modelView;
	}
}
