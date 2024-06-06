package org.jsp.busregistrationapp.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name,busNumber,destination;
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime pickupTime,dropTime;
	@Column(nullable = false)
	private String duretion;
	@Column(nullable = false)
	private String busType;
	@OneToMany(mappedBy = "bus")
	private List<Passenger> passengers;
	
	public List<Passenger> getPassengers() {
		return passengers;
	}
	public void setTickets(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public LocalDateTime getPickupTime() {
		return pickupTime;
	}
	public void setPickupTime(LocalDateTime pickupTime) {
		this.pickupTime = pickupTime;
	}
	public LocalDateTime getDropTime() {
		return dropTime;
	}
	public void setDropTime(LocalDateTime dropTime) {
		this.dropTime = dropTime;
	}
	
	public String getDuretion() {
		return duretion;
	}
	public void setDuretion(String duretion) {
		this.duretion = duretion;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	
	
	

}
