package com.github.lucasjalves.projetoles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.lucasjalves.projetoles.annotation.Repositorio;
import com.github.lucasjalves.projetoles.entidade.Cupom;

@Repository
@Repositorio(classe=Cupom.class)
public interface CupomRepository extends JpaRepository<Cupom, Long>{

}
