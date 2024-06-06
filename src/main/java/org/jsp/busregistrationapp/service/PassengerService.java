package org.jsp.busregistrationapp.service;

import java.util.Optional;

import org.jsp.busregistrationapp.dao.BusDao;
import org.jsp.busregistrationapp.dao.PassengerDao;
import org.jsp.busregistrationapp.dto.Bus;
import org.jsp.busregistrationapp.dto.Passenger;
import org.jsp.busregistrationapp.dto.ResponseStructure;
import org.jsp.busregistrationapp.exception.BusNotFoundException;
import org.jsp.busregistrationapp.exception.PassengerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {
	
	@Autowired
	private PassengerDao passengerDao;
	@Autowired
	private BusDao busDao;
	
	public ResponseEntity<ResponseStructure<Passenger>> savePassenger(Passenger passenger, int bus_id) {
		Optional<Bus> recBus = busDao.findById(bus_id);
		if (recBus.isPresent()) {
			Bus bus = recBus.get();
			passenger.setBus(bus);// Assigning Bus to Passenger
			bus.getPassengers().add(passenger);// Assigning Passenger to Bus
			ResponseStructure<Passenger> structure = new ResponseStructure<>();
			structure.setData(passengerDao.savePassenger(passenger));// Adding Passenger
			busDao.saveBus(bus);// Updating bus
			structure.setMessage("Passenger added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		throw new PassengerNotFoundException("Cannot add passenger as Bus Id is Invalid");
	}
	
	public ResponseEntity<ResponseStructure<Passenger>> updatePassenger(Passenger passenger) {
		Optional<Passenger> recPassenger = passengerDao.findById(passenger.getId());
		ResponseStructure<Passenger> structure = new ResponseStructure<>();
		if (recPassenger.isPresent()) {
			Passenger dbPassenger = recPassenger.get();
			dbPassenger.setFirstName(passenger.getFirstName());
			dbPassenger.setLastName(passenger.getLastName());
			dbPassenger.setAge(passenger.getAge());
			dbPassenger.setEmail(passenger.getEmail());
			dbPassenger.setGender(passenger.getGender());
			dbPassenger.setPhone(passenger.getPhone());
			structure.setData(passengerDao.savePassenger(dbPassenger));
			structure.setMessage("Passenger Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new PassengerNotFoundException("Cannot Update Passenger as Id is Invalid");
	}
	
	public ResponseEntity<ResponseStructure<Passenger>> findById(int id) {
		Optional<Passenger> recPassenger = passengerDao.findById(id);
		ResponseStructure<Passenger> structure = new ResponseStructure<>();
		if (recPassenger.isPresent()) {
			structure.setData(recPassenger.get());
			structure.setMessage("Passenger found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new PassengerNotFoundException("Cannot Find Passenger as Id is Invalid");
	}
	
	public String deleteById(int id) {
		Optional<Passenger> recPassenger = passengerDao.findById(id);
		if (recPassenger.isPresent()) {
			passengerDao.delete(id);
			return "Passenger Deleted";
		}
		throw new PassengerNotFoundException("Cannot delete Passenger as Id is Invalid");
	}

}
