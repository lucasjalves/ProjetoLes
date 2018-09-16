package com.github.lucasjalves.projetoles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.lucasjalves.projetoles.annotation.Repositorio;
import com.github.lucasjalves.projetoles.entidade.Dias;

@Repository
@Repositorio(classe=Dias.class)
public interface DiasRepository extends JpaRepository<Dias, Long>{

}
