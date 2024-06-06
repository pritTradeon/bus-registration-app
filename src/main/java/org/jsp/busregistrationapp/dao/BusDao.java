package org.jsp.busregistrationapp.dao;

import java.util.Optional;

import org.jsp.busregistrationapp.dto.Bus;
import org.jsp.busregistrationapp.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BusDao {
	
	@Autowired
	private BusRepository busRepository;
	
	public Bus saveBus(Bus bus) {
		return busRepository.save(bus);
	}
	
	public Optional<Bus> findById(int id) {
		return busRepository.findById(id);
	}
	
	public void delete(int id) {
		 busRepository.deleteById(id);
	}
}
