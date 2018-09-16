package com.github.lucasjalves.projetoles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.lucasjalves.projetoles.annotation.Repositorio;
import com.github.lucasjalves.projetoles.entidade.HorasNegocio;

@Repository
@Repositorio(classe=HorasNegocio.class)
public interface HorasNegocioRepository extends JpaRepository<HorasNegocio, Long>{

}
