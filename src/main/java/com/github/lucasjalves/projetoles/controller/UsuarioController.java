package com.github.lucasjalves.projetoles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.lucasjalves.projetoles.rns.Resultado;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	private final String PAGINA_CADASTRO_USUARIO = "usuario/cadastrarUsuario";
	
	@RequestMapping("/cadastrar")
	public ModelAndView paginaCadastroUsuario(ModelAndView modelView) {
		modelView.setViewName(PAGINA_CADASTRO_USUARIO);
		return modelView;
	}
}
