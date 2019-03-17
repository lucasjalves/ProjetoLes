package com.github.lucasjalves.projetoles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.lucasjalves.projetoles.entidade.Pedido;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
