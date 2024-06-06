package org.jsp.busregistrationapp.service;

import java.util.Optional;

import org.jsp.busregistrationapp.dao.FeedbackDao;
import org.jsp.busregistrationapp.dao.PassengerDao;
import org.jsp.busregistrationapp.dto.Bus;
import org.jsp.busregistrationapp.dto.Feedback;
import org.jsp.busregistrationapp.dto.Passenger;
import org.jsp.busregistrationapp.dto.ResponseStructure;
import org.jsp.busregistrationapp.exception.BusNotFoundException;
import org.jsp.busregistrationapp.exception.FeedbackNotFoundException;
import org.jsp.busregistrationapp.exception.PassengerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServices {
	
	@Autowired
	private FeedbackDao feedbackDao;
	
	@Autowired
	private PassengerDao passengerDao;
	
	public ResponseEntity<ResponseStructure<Feedback>> saveFeedback(Feedback feedback, int passenger_id) {
		Optional<Passenger> recPassenger = passengerDao.findById(passenger_id);
		if (recPassenger.isPresent()) {
			Passenger passenger = recPassenger.get();
			feedback.setPassenger(passenger);
			passenger.getFeedback().add(feedback);
			ResponseStructure<Feedback> structure = new ResponseStructure<>();
			structure.setData(feedbackDao.saveFeedback(feedback));
			passengerDao.savePassenger(passenger);
			structure.setMessage("Feedback added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		throw new FeedbackNotFoundException("Cannot add feedback as Passenger Id is Invalid");
	}
	
	public ResponseEntity<ResponseStructure<Feedback>> updateFeedback(Feedback feedback) {
		Optional<Feedback> recFeedback = feedbackDao.findById(feedback.getId());
		ResponseStructure<Feedback> structure = new ResponseStructure<>();
		if (recFeedback.isPresent()) {
			Feedback dbFeedback = recFeedback.get();
			dbFeedback.setComment(feedback.getComment());
			dbFeedback.setOverallRating(feedback.getOverallRating());
			structure.setData(feedbackDao.saveFeedback(dbFeedback));
			structure.setMessage("Feedback Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new PassengerNotFoundException("Cannot Update Feedback as Id is Invalid");
	}
	
	public ResponseEntity<ResponseStructure<Feedback>> findById(int id) {
		Optional<Feedback> recFeedback = feedbackDao.findById(id);
		ResponseStructure<Feedback> structure = new ResponseStructure<>();
		if (recFeedback.isPresent()) {
			structure.setData(recFeedback.get());
			structure.setMessage("Feedback found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new FeedbackNotFoundException("Cannot Find Feedback as Id is Invalid");
	}
	
	public String deleteById(int id) {
		Optional<Feedback> recFeedback = feedbackDao.findById(id);
		if (recFeedback.isPresent()) {
			feedbackDao.delete(id);
			return "Feedack Deleted";
		}
		throw new FeedbackNotFoundException("Cannot delete feedback as Id is Invalid");
	}
}
