package com.github.lucasjalves.projetoles.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.lucasjalves.projetoles.entidade.Usuario;
import com.github.lucasjalves.projetoles.rns.Mensagem;
import com.github.lucasjalves.projetoles.service.DepartamentoService;
import com.github.lucasjalves.projetoles.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController{

	private static final String PAGINA_CADASTRO_USUARIO = "usuario/cadastrarUsuario";
	private static final String PAGINA_LOGIN_USUARIO = "usuario/login";
	private static final String PAGINA_DASHBOARD = "usuario/dashboard";
	private static final String PAGINA_TABELA_USUARIOS = "usuario/todosUsuariosTabela";
	private static final String PAGINA_DETALHE_USUARIO = "usuario/detalheUsuario";
	private static final String SESSAO_USUARIO = "sessaoUsuario";
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private HttpSession httpSession;
	
	@RequestMapping("/cadastrar")
	public ModelAndView paginaCadastroUsuario(ModelAndView modelView) throws JsonProcessingException {
		modelView.setViewName(PAGINA_CADASTRO_USUARIO);
		modelView.addObject("jsonListaDepartamentos", mapper.writeValueAsString(departamentoService.buscarTodos()));
		return modelView;
	}
	
	@PostMapping("/cadastrar/efetivar")
	public String cadastrarUsuario(@ModelAttribute Usuario usuario, ModelAndView modelView) {
		service.salvar(usuario);
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
	public ModelAndView paginaTodosUsuarios(ModelAndView modelView) throws JsonProcessingException {
		modelView.addObject("jsonListaUsuarios", mapper.writeValueAsString(service.buscarTodos()));
		modelView.setViewName(PAGINA_TABELA_USUARIOS);		
		return modelView;
	}
	
	@RequestMapping("/paginaDetalhe")
	public ModelAndView paginaDetalheUsuario(@ModelAttribute Usuario usuario, ModelAndView modelView) throws JsonProcessingException {
		httpSession.setAttribute(SESSAO_USUARIO, service.buscarTodos().get(0));
		Usuario usuarioFiltrado = service.buscarUnicoUsuarioPorId(usuario);
		usuarioFiltrado.setSenha("");
		Usuario usuarioSessao = (Usuario) httpSession.getAttribute(SESSAO_USUARIO);
		modelView.addObject("jsonUsuario", mapper.writeValueAsString(usuarioFiltrado));
		modelView.addObject("usuario", usuarioFiltrado);
		modelView.addObject("mesmoUsuario", (usuarioSessao.getId() == usuarioFiltrado.getId()));
		modelView.addObject("departamentos", mapper.writeValueAsString(departamentoService.buscarTodos()));
		modelView.setViewName(PAGINA_DETALHE_USUARIO);
		return modelView;
		
	}
	
	@ResponseBody
	@RequestMapping(value= "/cpfCadastrado", method = RequestMethod.POST)
	public boolean cpfCadastrado(@RequestBody Usuario usuario) {
		return service.cpfJaCadastrado(usuario);
	}
	
	@ResponseBody
	@RequestMapping(value= "/ramalCadastrado", method = RequestMethod.POST)
	public boolean ramalCadastrado(@RequestBody Usuario usuario) {
		return service.ramalJaCadastrado(usuario);
	}
		
	@ResponseBody
	@RequestMapping(value= "/emailCadastrado", method = RequestMethod.POST)
	public boolean emailCadastrado(@RequestBody Usuario usuario) {
		return service.emailJaCadastrado(usuario);
	}
	
	@ResponseBody
	@RequestMapping(value= "/usernameCadastrado", method = RequestMethod.POST)
	public boolean usernameCadastrado(@RequestBody Usuario usuario) {
		return service.usernameJaCadastrado(usuario);
	}

	@ResponseBody
	@RequestMapping(value="/alterarSenha", method = {RequestMethod.POST})
	public List<Mensagem> alterarSenha(@RequestBody String json) {
		ObjectNode node = null;
		try {
			node = mapper.readValue(json, ObjectNode.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return service.alterarSenha(node.get("senhaNova").asText(), 
				node.get("senhaAntiga").asText(), 
				(Usuario)httpSession.getAttribute(SESSAO_USUARIO));
		
	}
	
	@RequestMapping("/login/confirmar")
	public ModelAndView loginConfirmar(ModelAndView modelView, Usuario user,@RequestParam("usuario") String usuario ,@RequestParam("senha") String senha ) {
		
		service.buscarTodos();
		System.out.println(usuario);
		System.out.println(senha);		
		modelView.setViewName("/usuario/dashboard");
		return modelView;
	}
	
	@RequestMapping("/usuario/DashboardSolicitante")
	public ModelAndView solicitanteDash(ModelAndView modelView) {	
		modelView.setViewName("/usuario/DashboardSolicitante");
		return modelView;
	}
}
