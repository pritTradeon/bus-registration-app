package org.jsp.busregistrationapp.repository;

import org.jsp.busregistrationapp.dto.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	Ticket findById(Ticket ticket);
}
