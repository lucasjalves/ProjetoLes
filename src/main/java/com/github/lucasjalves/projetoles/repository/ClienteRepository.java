package com.github.lucasjalves.projetoles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.lucasjalves.projetoles.annotation.Repositorio;
import com.github.lucasjalves.projetoles.entidade.Cliente;

@Repository
@Repositorio(classe=Cliente.class)
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
