package org.jsp.busregistrationapp.service;

import java.util.Optional;

import org.jsp.busregistrationapp.dao.PassengerDao;
import org.jsp.busregistrationapp.dao.TicketDao;
import org.jsp.busregistrationapp.dto.Bus;
import org.jsp.busregistrationapp.dto.Passenger;
import org.jsp.busregistrationapp.dto.ResponseStructure;
import org.jsp.busregistrationapp.dto.Ticket;
import org.jsp.busregistrationapp.exception.BusNotFoundException;
import org.jsp.busregistrationapp.exception.PassengerNotFoundException;
import org.jsp.busregistrationapp.exception.TicketNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TicketServices {
	
	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private PassengerDao passengerDao;
	
	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(Ticket ticket, int passenger_id) {
		Optional<Passenger> recPassenger = passengerDao.findById(passenger_id);
		if (recPassenger.isPresent()) {
			Passenger passenger = recPassenger.get();
			ticket.setPassenger(passenger);// Assigning Bus to Passenger
//			passenger.setTicket(ticket);
			Ticket savedTicket = ticketDao.saveTicket(ticket);
			ResponseStructure<Ticket> structure = new ResponseStructure<>();
	        structure.setData(savedTicket);
	        structure.setMessage("Ticket added");
	        structure.setStatusCode(HttpStatus.CREATED.value());
	        return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		throw new TicketNotFoundException("Cannot add Ticket as Passenger Id is Invalid");
	}
	//infinite time run
	public ResponseEntity<ResponseStructure<Ticket>> findById(int id) {
		Optional<Ticket> recTicket = ticketDao.findById(id);
		ResponseStructure<Ticket> structure = new ResponseStructure<>();
		if (recTicket.isPresent()) {
			structure.setData(recTicket.get());
			structure.setMessage("Ticket found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new TicketNotFoundException("Cannot Find Ticket as Id is Invalid");
	}
	//infinite time run
	public ResponseEntity<ResponseStructure<Ticket>> updateTicket(Ticket ticket) {
		Optional<Ticket> recTicket = ticketDao.findById(ticket.getId());
		ResponseStructure<Ticket> structure = new ResponseStructure<>();
		if (recTicket.isPresent()) {
			Ticket dbTicket = recTicket.get();
			dbTicket.setSeatNumber(ticket.getSeatNumber());
			dbTicket.setTicketType(ticket.getTicketType());
			structure.setData(ticketDao.saveTicket(dbTicket));
			structure.setMessage("Ticket Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new TicketNotFoundException("Cannot Update Ticket as Id is Invalid");
	}
	
	public String deleteById(int id) {
		Optional<Ticket> recTicket = ticketDao.findById(id);
		if (recTicket.isPresent()) {
			ticketDao.delete(id);
			return "Ticket Deleted";
		}
		throw new TicketNotFoundException("Cannot delete Ticket as Id is Invalid");
	}
}
