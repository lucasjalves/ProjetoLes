package com.github.lucasjalves.projetoles.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.lucasjalves.projetoles.entidade.Usuario;
import com.github.lucasjalves.projetoles.rns.Resultado;

@Service
public interface UsuarioService {
	Resultado salvar(Usuario usuario);
	List<Usuario> buscarTodos();
	Resultado alterar(Usuario usuario);
	Resultado excluir(Usuario usuario);
	Usuario buscarUnicoUsuarioPorId(Usuario usuario);
	boolean cpfJaCadastrado(Usuario usuario);
	boolean ramalJaCadastrado(Usuario usuario);
	boolean emailJaCadastrado(Usuario usuario);
	boolean usernameJaCadastrado(Usuario usuario);
}
