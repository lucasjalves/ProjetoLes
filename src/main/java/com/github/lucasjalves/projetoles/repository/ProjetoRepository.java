package com.github.lucasjalves.projetoles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.lucasjalves.projetoles.annotation.Repositorio;
import com.github.lucasjalves.projetoles.entidade.Projeto;

@Repositorio(classe = Projeto.class)
@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

}
