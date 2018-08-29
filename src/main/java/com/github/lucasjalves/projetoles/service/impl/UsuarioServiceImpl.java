package com.github.lucasjalves.projetoles.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.lucasjalves.projetoles.entidade.Usuario;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private Facade facade;
	
	@Override
	public Resultado salvar(Usuario usuario) {
		return facade.salvar(usuario);
	}

	@Override
	public List<Usuario> buscarTodos() {
		return (List<Usuario>) facade.buscar(new Usuario()).getEntidades();
	}

	@Override
	public Resultado alterar(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resultado excluir(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Usuario buscarUnicoUsuarioPorId(Usuario usuario) {
		List<Usuario> lista = (List<Usuario>) facade.buscar(new Usuario()).getEntidades();
		lista = lista.stream()
				.filter(user -> usuario.getId() == usuario.getId())
				.collect(Collectors.toList());
		return lista.get(0);
	}

}
