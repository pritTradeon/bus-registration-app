package org.jsp.busregistrationapp.controller;

import org.jsp.busregistrationapp.dto.Passenger;
import org.jsp.busregistrationapp.dto.ResponseStructure;
import org.jsp.busregistrationapp.dto.Ticket;
import org.jsp.busregistrationapp.service.TicketServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/ticket")
public class TicketController {
	
	@Autowired
	private TicketServices ticketServices;
	
	@PostMapping("/{passenger_id}")
	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(@RequestBody Ticket ticket,
			@PathVariable int passenger_id) {
		return ticketServices.saveTicket(ticket, passenger_id);
	}
	
	@GetMapping("/find-by-id/{id}")
	public ResponseEntity<ResponseStructure<Ticket>> findById(@PathVariable int id) {
		return ticketServices.findById(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Ticket>> updateTicket(@RequestBody Ticket ticket) {
		return ticketServices.updateTicket(ticket);
	}
	
	@DeleteMapping("/delete-by-id/{id}")
	public String deleteById(@PathVariable int id) {
		return ticketServices.deleteById(id);
	}
}
