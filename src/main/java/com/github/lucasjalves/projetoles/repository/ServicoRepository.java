package com.github.lucasjalves.projetoles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.lucasjalves.projetoles.annotation.Repositorio;
import com.github.lucasjalves.projetoles.entidade.Servico;

@Repositorio(classe = Servico.class)
@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
