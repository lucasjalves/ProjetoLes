package com.github.lucasjalves.projetoles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.github.lucasjalves.projetoles.util.ValidadorUtils;

@SpringBootApplication
@EnableJpaRepositories("com.github.lucasjalves.projetoles.repository")
@EntityScan("com.github.lucasjalves.projetoles.entidade")
@ComponentScan({"com.github.lucasjalves.projetoles"})
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
