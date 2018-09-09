package com.github.lucasjalves.projetoles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.lucasjalves.projetoles.annotation.Repositorio;
import com.github.lucasjalves.projetoles.entidade.Sla;

@Repositorio(classe=Sla.class)
@Repository
public interface SlaRepository extends JpaRepository<Sla, Long>{

}
