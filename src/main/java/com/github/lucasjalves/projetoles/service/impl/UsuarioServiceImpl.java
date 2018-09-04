package com.github.lucasjalves.projetoles.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.lucasjalves.projetoles.entidade.Usuario;
import com.github.lucasjalves.projetoles.facade.Facade;
import com.github.lucasjalves.projetoles.rns.Mensagem;
import com.github.lucasjalves.projetoles.rns.Resultado;
import com.github.lucasjalves.projetoles.service.UsuarioService;

@Service
final class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private Facade facade;
	
	@Override
	public Resultado salvar(Usuario usuario) {
		Resultado resultado = new Resultado();
		if(cpfJaCadastrado(usuario) || ramalJaCadastrado(usuario) || usernameJaCadastrado(usuario) || emailJaCadastrado(usuario)) {
			resultado.getMensagem().add(new Mensagem("Duplicidade de atributos"));
			return resultado;
		}
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
				.filter(user -> user.getId() == usuario.getId())
				.collect(Collectors.toList());
		return lista.get(0);
	}
	
	public boolean cpfJaCadastrado(Usuario usuario) {
		List<Usuario> lista = (List<Usuario>) facade.buscar(new Usuario()).getEntidades();
		lista = lista.stream()
			.filter(user -> user.getCpf().replaceAll("\\D", "").equals(usuario.getCpf().replaceAll("\\D", "")))
			.collect(Collectors.toList());
		if(lista.size() > 0) {
			return true;
		}
		return false;
	}
	
	public boolean ramalJaCadastrado(Usuario usuario) {
		List<Usuario> lista = (List<Usuario>) facade.buscar(new Usuario()).getEntidades();
		lista = lista.stream()
			.filter(user -> user.getRamal().equals(usuario.getRamal()))
			.collect(Collectors.toList());
		if(lista.size() > 0) {
			return true;
		}
		return false;		
	}
	
	public boolean emailJaCadastrado(Usuario usuario) {
		List<Usuario> lista = (List<Usuario>) facade.buscar(new Usuario()).getEntidades();
		lista = lista.stream()
			.filter(user -> user.getEmail().equals(usuario.getEmail()))
			.collect(Collectors.toList());
		if(lista.size() > 0) {
			return true;
		}
		return false;			
	}
	
	public boolean usernameJaCadastrado(Usuario usuario) {
		List<Usuario> lista = (List<Usuario>) facade.buscar(new Usuario()).getEntidades();
		lista = lista.stream()
			.filter(user -> user.getUsername().equals(usuario.getUsername()))
			.collect(Collectors.toList());
		if(lista.size() > 0) {
			return true;
		}
		return false;			
	}

	@Override
	public List<Mensagem> alterarSenha(String senhaNova, String senhaAntiga, Usuario usuario) {
		List<Mensagem> mensagens = new ArrayList<>();
		List<Usuario> lista = (List<Usuario>) facade.buscar(new Usuario()).getEntidades();
		Usuario usuarioBanco = lista.stream().filter(user -> usuario.getId() == user.getId()).collect(Collectors.toList()).get(0);
		if(!usuarioBanco.getSenha().equals(senhaAntiga)) {
			mensagens.add(new Mensagem("Senha atual está incorreta"));
		}
		else {
			usuarioBanco.setSenha(senhaNova);
			mensagens = facade.alterar(usuarioBanco).getMensagem();
		}
		return mensagens;
	}

}
