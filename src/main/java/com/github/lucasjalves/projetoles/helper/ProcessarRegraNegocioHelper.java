package com.github.lucasjalves.projetoles.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.annotation.RegraNegocio;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.rns.Mensagem;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;

@Component
public class ProcessarRegraNegocioHelper {

	Set<Class<?>> classesRegrasNegocio;
	public List<Mensagem> processarRegraNegocio(Entidade entidade, String operacao)
	{
		List<Mensagem> mensagensRegraNegocio = new ArrayList<Mensagem>();
		classesRegrasNegocio = new Reflections("com.github.lucasjalves.projetoles.rns.strategy.impl").getTypesAnnotatedWith(RegraNegocio.class);		
		classesRegrasNegocio.forEach(classe -> {
			RegraNegocio anotacaoRegraNegocio = classe.getAnnotation(RegraNegocio.class);
			if(anotacaoRegraNegocio != null)
			{
				if(anotacaoRegraNegocio.classe().getCanonicalName().equals(entidade.getClass().getCanonicalName()) &&
						operacao.equals(anotacaoRegraNegocio.operacao()))
				{
					try {
						Strategy instanciaRegraNegocio = (Strategy) classe.newInstance();
						mensagensRegraNegocio.addAll(instanciaRegraNegocio.processar(entidade));
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}				
			}
		});		
		return mensagensRegraNegocio;
	}
}
