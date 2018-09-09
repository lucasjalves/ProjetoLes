package com.github.lucasjalves.projetoles;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@SpringBootApplication
@EnableJpaRepositories("com.github.lucasjalves.projetoles.repository")
@EntityScan("com.github.lucasjalves.projetoles.entidade")
@ComponentScan({"com.github.lucasjalves.projetoles"})
public class App {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		SpringApplication.run(App.class, args);		
	}

}
