package org.jsp.busregistrationapp.repository;

import org.jsp.busregistrationapp.dto.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Integer> {
	
	
}
