package org.jsp.busregistrationapp.repository;

import org.jsp.busregistrationapp.dto.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Integer>{

}
