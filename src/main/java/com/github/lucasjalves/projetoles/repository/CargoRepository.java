package com.github.lucasjalves.projetoles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.lucasjalves.projetoles.annotation.Repositorio;
import com.github.lucasjalves.projetoles.entidade.Cargo;

@Repositorio(classe = Cargo.class)
@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long>{

}
