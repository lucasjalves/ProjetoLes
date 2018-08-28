package com.github.lucasjalves.projetoles.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.lucasjalves.projetoles.entidade.Departamento;
import com.github.lucasjalves.projetoles.entidade.Usuario;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Controller
@RequestMapping("/usuario")
public class UsuarioController extends ControllerBase {

	private static final String PAGINA_CADASTRO_USUARIO = "usuario/cadastrarUsuario";
	private static final String PAGINA_LOGIN_USUARIO = "usuario/login";
	private static final String PAGINA_DASHBOARD = "usuario/dashboard";
	private static final String PAGINA_TABELA_USUARIOS = "usuario/todosUsuariosTabela";
	private static final String PAGINA_DETALHE_USUARIO = "usuario/detalheUsuario";
	
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
	
	@PostMapping("/cadastrar/efetivar")
	public String cadastrarUsuario(@ModelAttribute Usuario usuario, ModelAndView modelView) {
		System.out.println(usuario.getSetor().getId());
		Resultado resultado = facade.salvar(usuario);
		modelView.setViewName(PAGINA_TABELA_USUARIOS);
		return "forward:/usuario/consultarTodos";
	}
	
	@RequestMapping("/login")
	public ModelAndView paginaLogin(ModelAndView modelView) {
		modelView.setViewName(PAGINA_LOGIN_USUARIO);
		return modelView;
	}
	
	@RequestMapping("/dashboard")
	public ModelAndView paginaDashboard(ModelAndView modelView) {
		modelView.setViewName(PAGINA_DASHBOARD);
		return modelView;
	}
	
	@RequestMapping("/consultarTodos")
	public ModelAndView paginaTodosUsuarios(ModelAndView modelView) {
		String json ="{}";
		List<Usuario> list = (List<Usuario>) facade.buscar(new Usuario()).getEntidades();
		list = list.stream().sorted(Comparator.comparing(Usuario::getUsername)).collect(Collectors.toList());
		try {
			json = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		modelView.addObject("jsonListaUsuarios", json);
		modelView.setViewName(PAGINA_TABELA_USUARIOS);		
		return modelView;
	}
	
	@RequestMapping("/paginaDetalhe")
	public ModelAndView paginaDetalheUsuario(@ModelAttribute Usuario usuario, ModelAndView modelView) {
		String json = "{}";
		List<Usuario> list = (List<Usuario>) facade.buscar(new Usuario()).getEntidades();
		Usuario usuari = list.stream().filter(user -> usuario.getId() == usuario.getId()).collect(Collectors.toList()).get(0);
		try {
			json = mapper.writeValueAsString(usuari);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		modelView.addObject("jsonUsuario", json);
		modelView.addObject("usuario", usuari);
		modelView.setViewName(PAGINA_DETALHE_USUARIO);
		return modelView;
		
	}
	
	
}
