package com.github.lucasjalves.projetoles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.lucasjalves.projetoles.annotation.Repositorio;
import com.github.lucasjalves.projetoles.entidade.Produto;

@Repositorio(classe=Produto.class)
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
