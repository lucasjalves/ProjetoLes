package com.github.lucasjalves.projetoles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.lucasjalves.projetoles.entidade.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

}
