package org.jsp.busregistrationapp.controller;

import org.jsp.busregistrationapp.dto.Bus;
import org.jsp.busregistrationapp.service.BusService;
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
import org.jsp.busregistrationapp.dto.ResponseStructure;

@RestController
@RequestMapping(value = "/api/bus")
public class BusController {
	
	@Autowired
	private BusService busService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Bus>> saveMerchant(@RequestBody Bus bus) {
		return busService.saveBus(bus);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Bus>> findById(@PathVariable(name = "id") int id) {
		return busService.findById(id);
	}
	
	@PutMapping
	public Bus updateBus(@RequestBody Bus bus) {
		return busService.updateBus(bus);
	}
	
	@DeleteMapping("/{id}")
	public String deleteBus(@PathVariable(name = "id") int id) {
		return busService.deleteBus(id);
	}
}
