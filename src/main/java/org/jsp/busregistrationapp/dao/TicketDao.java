package org.jsp.busregistrationapp.dao;

import java.util.Optional;

import org.jsp.busregistrationapp.dto.Passenger;
import org.jsp.busregistrationapp.dto.Ticket;
import org.jsp.busregistrationapp.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDao {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	public Ticket saveTicket(Ticket ticket) {
		return ticketRepository.save(ticket);
	}
	
	public Optional<Ticket> findById(int id) {
		return ticketRepository.findById(id);
	}
	
	public void delete(int id) {
		ticketRepository.deleteById(id);
	}
}
