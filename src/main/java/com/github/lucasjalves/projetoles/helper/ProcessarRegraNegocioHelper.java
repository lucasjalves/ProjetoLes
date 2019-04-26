package com.github.lucasjalves.projetoles.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.annotation.RegraNegocio;
import com.github.lucasjalves.projetoles.entidade.Entidade;
import com.github.lucasjalves.projetoles.rns.strategy.Strategy;

@Component
final public class ProcessarRegraNegocioHelper {
	
	private Set<Class<?>> classesRegrasNegocio;
	public List<String> processarRegraNegocio(Entidade entidade, String operacao)
	{
		List<String> mensagensRegraNegocio = new ArrayList<>();

		classesRegrasNegocio = new Reflections("com.github.lucasjalves.projetoles.rns.strategy.impl").getTypesAnnotatedWith(RegraNegocio.class);		
		classesRegrasNegocio.forEach(classe -> {
			RegraNegocio anotacaoRegraNegocio = classe.getAnnotation(RegraNegocio.class);
			if(anotacaoRegraNegocio != null)
			{
				
				Set<String> operacoes = new HashSet<>(Arrays.asList(anotacaoRegraNegocio.operacao()));
				if(anotacaoRegraNegocio.classe().getCanonicalName().equals(entidade.getClass().getCanonicalName()) &&
						operacoes.contains(operacao))
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
		Class<?> superClasse = entidade.getClass().getSuperclass();
		while(superClasse != null) {
			//mensagensRegraNegocio = processarRegrasNegocioSuperclasse(operacao, entidade, superClasse, mensagensRegraNegocio);
			superClasse = superClasse.getSuperclass();
		}
		return mensagensRegraNegocio;
	}
	
	private Set<Class<?>> regrasNegocioSuperclasse(String operacao,Entidade entidade,Class<?> superClasse){
		return classesRegrasNegocio.stream()
			.filter(c -> c.getAnnotation(RegraNegocio.class).classe().getCanonicalName().equals(superClasse.getCanonicalName())
					&& Arrays.asList(c.getAnnotation(RegraNegocio.class).operacao()).contains(operacao))
			.collect(Collectors.toSet());	
	}
	
	private List<String> processarRegrasNegocioSuperclasse(String operacao,Entidade entidade,Class<?> superClasse, List<String> mensagensRegraNegocio){
		Set<Class<?>> classes = regrasNegocioSuperclasse(operacao, entidade, superClasse);
		Iterator<Class<?>> it = classes.iterator();
		while(it.hasNext()) {
			try {
				Strategy instanciaRegraNegocio = (Strategy) it.next().newInstance();
				mensagensRegraNegocio.addAll(instanciaRegraNegocio.processar(entidade));
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return mensagensRegraNegocio;
	}
}
