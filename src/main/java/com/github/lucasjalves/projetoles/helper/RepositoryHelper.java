package com.github.lucasjalves.projetoles.helper;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.github.lucasjalves.projetoles.annotation.Repositorio;
import com.github.lucasjalves.projetoles.entidade.Entidade;

@Component
final public class RepositoryHelper {

	@Autowired
	private ApplicationContext context;

	public JpaRepository<?, Long> getRepository(Entidade entidade){
		
		Map<String, Object> map = context.getBeansWithAnnotation(Repositorio.class);
		Iterator<String> ir = map.keySet().iterator();
		while(ir.hasNext()) {
			String key = ir.next();
			JpaRepository<?, Long> classeNoMapa = (JpaRepository<?, Long>) map.get(key);
			Repositorio anotacao = context.findAnnotationOnBean(key, Repositorio.class);
			if(anotacao != null) {
				if(entidade.getClass().getCanonicalName().equals(anotacao.classe().getCanonicalName())) {
					return classeNoMapa;
				}				
			}
		}
		return null;		
	}
}
