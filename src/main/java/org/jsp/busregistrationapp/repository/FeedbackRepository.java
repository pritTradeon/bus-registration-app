package org.jsp.busregistrationapp.repository;

import org.jsp.busregistrationapp.dto.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

}
