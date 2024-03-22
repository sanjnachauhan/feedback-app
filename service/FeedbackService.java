package com.ideatitle.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideatitle.entity.Feedback;
import com.ideatitle.payload.request.FeedbackRequest;
import com.ideatitle.repository.FeedbackRepository;

@Service
public class FeedbackService {
	
	@Autowired
	FeedbackRepository feedbackRepo;

	public Feedback saveFeedback(@Valid FeedbackRequest request) {
		Feedback feedback = new Feedback();
		feedback.setComment(request.getComment());
		feedback.setDate(LocalDateTime.now());
		return feedbackRepo.save(feedback);
	}

	public Feedback saveUpvote(Long id) {
		Optional<Feedback> findById = feedbackRepo.findById(id);
		if(findById.isPresent()) {
			Feedback feedback = findById.get();
			feedback.setUpvote(feedback.getUpvote()+1);
			return feedbackRepo.save(feedback);
		}
		return null;
	}
	
	public Feedback saveDownvote(Long id) {
		Optional<Feedback> findById = feedbackRepo.findById(id);
		if(findById.isPresent()) {
			Feedback feedback = findById.get();
			feedback.setDownvote(feedback.getDownvote()+1);
			return feedbackRepo.save(feedback);
		}
		return null;
	}
	
	public List<Feedback> getAllFeedbacks() {
		return feedbackRepo.findAllByOrderByDateDesc();
	}
	
}
