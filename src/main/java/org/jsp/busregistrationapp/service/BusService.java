package org.jsp.busregistrationapp.service;

import java.util.Optional;

import org.jsp.busregistrationapp.dao.BusDao;
import org.jsp.busregistrationapp.dto.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.jsp.busregistrationapp.dto.ResponseStructure;
import org.jsp.busregistrationapp.exception.BusNotFoundException;

@Service
public class BusService {
	
	@Autowired
	private BusDao busDao;
	
	public ResponseEntity<ResponseStructure<Bus>> saveBus(@RequestBody Bus bus) {
		ResponseStructure<Bus> structure = new ResponseStructure<>();
		structure.setMessage("Bus data has been Saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setData(busDao.saveBus(bus));
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<Bus>> findById(@PathVariable(name = "id") int id) {
		ResponseStructure<Bus> structure = new ResponseStructure<>();
		Optional<Bus> recBus = busDao.findById(id);
		if (recBus.isPresent()) {
			structure.setMessage("Bus Found");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(recBus.get());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new BusNotFoundException("Invalid Bus Id");
	}
	
	public Bus updateBus(@RequestBody Bus bus) {
		Optional<Bus> recBus = busDao.findById(bus.getId());
		if (recBus.isPresent()) {
			Bus dbBus = recBus.get();
			dbBus.setName(bus.getName());
			dbBus.setBusNumber(bus.getBusNumber());
			dbBus.setBusType(bus.getBusType());
			dbBus.setDestination(bus.getDestination());
			dbBus.setDropTime(bus.getDropTime());
			dbBus.setDuretion(bus.getDuretion());
			dbBus.setPickupTime(bus.getPickupTime());
			busDao.saveBus(bus);
			return dbBus;
		}
		throw new BusNotFoundException("Invalid Bus Id");
	}
	
	public String deleteBus(int id) {
		Optional<Bus> recBus = busDao.findById(id);
		if (recBus.isPresent()) {
			busDao.delete(id);
			return "Bus Deleted";
		}
		throw new BusNotFoundException("Cannot Delete Bus As Id is Invalid");
	}
}
