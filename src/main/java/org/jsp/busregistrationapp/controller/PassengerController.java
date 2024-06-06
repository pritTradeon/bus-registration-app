package org.jsp.busregistrationapp.controller;

import org.jsp.busregistrationapp.dto.Passenger;
import org.jsp.busregistrationapp.dto.ResponseStructure;
import org.jsp.busregistrationapp.service.PassengerService;
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
@RequestMapping(value = "/api/passenger")
public class PassengerController {

	@Autowired
	private PassengerService passengerService;
	
	@PostMapping("/{bus_id}")
	public ResponseEntity<ResponseStructure<Passenger>> savePassanger(@RequestBody Passenger passenger,
			@PathVariable int bus_id) {
		return passengerService.savePassenger(passenger, bus_id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Passenger>> updatePassenger(@RequestBody Passenger passenger) {
		return passengerService.updatePassenger(passenger);
	}
	
	@GetMapping("/find-by-id/{id}")
	public ResponseEntity<ResponseStructure<Passenger>> findById(@PathVariable int id) {
		return passengerService.findById(id);
	}
	
	@DeleteMapping("/delete-by-id/{id}")
	public String deleteById(@PathVariable int id) {
		return passengerService.deleteById(id);
	}
}
