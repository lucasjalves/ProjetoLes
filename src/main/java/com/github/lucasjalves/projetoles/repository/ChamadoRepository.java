package com.github.lucasjalves.projetoles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.lucasjalves.projetoles.annotation.Repositorio;
import com.github.lucasjalves.projetoles.entidade.Chamado;

@Repositorio(classe = Chamado.class)
@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long>{

}
