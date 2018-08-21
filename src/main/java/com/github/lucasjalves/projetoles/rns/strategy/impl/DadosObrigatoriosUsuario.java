package com.github.lucasjalves.projetoles.rns.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.lucasjalves.projetoles.annotation.RegraNegocio;
import com.github.lucasjalves.projetoles.entidade.Usuario;
import com.github.lucasjalves.projetoles.rns.Mensagem;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;
import com.github.lucasjalves.projetoles.util.ValidadorUtils;


@RegraNegocio(classe = Usuario.class, operacao = "SALVAR")
public class DadosObrigatoriosUsuario implements Strategy {

	private List<Mensagem> mensagens = new ArrayList<Mensagem>();
	@Override
	public List<Mensagem> processar(Object object) {
		Usuario usuario = (Usuario)object;
		if(!ValidadorUtils.emailValido(usuario.getEmail())) {
			mensagens.add(new Mensagem("Email inválido"));
		}
		if(!ValidadorUtils.cpfValido(usuario.getCpf())) {
			mensagens.add(new Mensagem("CPF inválido"));
		}
		if(!ValidadorUtils.senhaForte(usuario.getSenha())) {
			mensagens.add(new Mensagem("A senha deve conter no mínimo 8 caracteres, pelo menos uma letra, um digito e um caracter especial"));
		}
		if(usuario.getRamal().trim().length() == 0) {
			mensagens.add(new Mensagem("Digite o ramal"));
		}
		if(usuario.getSetor().getNome().trim().length() == 0) {
			mensagens.add(new Mensagem("Selecione um setor"));
		}
		
		return mensagens;
	}

}
