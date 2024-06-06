package org.jsp.busregistrationapp.controller;

import org.jsp.busregistrationapp.dto.Feedback;
import org.jsp.busregistrationapp.dto.Passenger;
import org.jsp.busregistrationapp.dto.ResponseStructure;
import org.jsp.busregistrationapp.service.FeedbackServices;
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
@RequestMapping(value = "/api/feedback")
public class FeedbackController {
	
	@Autowired
	private FeedbackServices feedbackServices;
	
	@PostMapping("/{passenger_id}")
	public ResponseEntity<ResponseStructure<Feedback>> saveFeedback(@RequestBody Feedback feedback,
			@PathVariable int passenger_id) {
		return feedbackServices.saveFeedback(feedback, passenger_id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Feedback>> updateFeedback(@RequestBody Feedback feedback) {
		return feedbackServices.updateFeedback(feedback);
	}
	
	@GetMapping("/find-by-id/{id}")
	public ResponseEntity<ResponseStructure<Feedback>> findById(@PathVariable int id) {
		return feedbackServices.findById(id);
	}
	
	@DeleteMapping("/delete-by-id/{id}")
	public String deleteById(@PathVariable int id) {
		return feedbackServices.deleteById(id);
	}
}
