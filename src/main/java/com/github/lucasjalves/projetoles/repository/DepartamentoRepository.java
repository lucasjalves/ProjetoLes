package com.github.lucasjalves.projetoles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.lucasjalves.projetoles.annotation.Repositorio;
import com.github.lucasjalves.projetoles.entidade.Departamento;

@Repositorio(classe = Departamento.class)
@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

}
