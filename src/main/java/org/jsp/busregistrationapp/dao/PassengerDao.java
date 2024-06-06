package org.jsp.busregistrationapp.dao;

import java.util.Optional;

import org.jsp.busregistrationapp.dto.Passenger;
import org.jsp.busregistrationapp.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PassengerDao {
	
	@Autowired
	private PassengerRepository passengerRepository;
	
	public Passenger savePassenger(Passenger passenger) {
		return passengerRepository.save(passenger);
	}
	
	public Optional<Passenger> findById(int id) {
		return passengerRepository.findById(id);
	}
	
	public void delete(int id) {
		passengerRepository.deleteById(id);
	}
}
