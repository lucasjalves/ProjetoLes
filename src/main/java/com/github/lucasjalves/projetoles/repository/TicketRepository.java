package com.github.lucasjalves.projetoles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.lucasjalves.projetoles.entidade.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{

}
