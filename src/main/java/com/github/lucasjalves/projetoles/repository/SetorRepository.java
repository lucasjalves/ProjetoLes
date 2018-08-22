package com.github.lucasjalves.projetoles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.lucasjalves.projetoles.annotation.Repositorio;
import com.github.lucasjalves.projetoles.entidade.Setor;

@Repositorio(classe = Setor.class)
@Repository
public interface SetorRepository extends JpaRepository<Setor, Long>{

}
