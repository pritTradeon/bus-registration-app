package org.jsp.busregistrationapp.dao;

import java.util.Optional;

import org.jsp.busregistrationapp.dto.Feedback;
import org.jsp.busregistrationapp.dto.Passenger;
import org.jsp.busregistrationapp.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FeedbackDao {
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	public Feedback saveFeedback(Feedback feedback) {
		return feedbackRepository.save(feedback);
	}
	
	public Optional<Feedback> findById(int id) {
		return feedbackRepository.findById(id);
	}
	
	public void delete(int id) {
		feedbackRepository.deleteById(id);
	}
}
