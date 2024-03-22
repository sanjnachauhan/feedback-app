package com.ideatitle.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideatitle.entity.Feedback;
import com.ideatitle.payload.request.FeedbackRequest;
import com.ideatitle.service.FeedbackService;

@RestController
@CrossOrigin
@RequestMapping("/feedback")
public class FeedbackController {
	
	@Autowired
	FeedbackService feedbackService;
	
	@PostMapping
	public ResponseEntity<Feedback> saveFeedback(@Valid @RequestBody FeedbackRequest request){
		Feedback feedback = feedbackService.saveFeedback(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(feedback);
	}
	
	@PutMapping("/{id}/upVote")
	public ResponseEntity<Feedback> saveUpvote(@PathVariable("id") Long id){
		Feedback feedback = feedbackService.saveUpvote(id);
		return ResponseEntity.status(HttpStatus.OK).body(feedback);
	}
	
	@PutMapping("/{id}/downVote")
	public ResponseEntity<Feedback> saveDownvote(@PathVariable("id") Long id){
		Feedback feedback = feedbackService.saveDownvote(id);
		return ResponseEntity.status(HttpStatus.OK).body(feedback);
	}
	
	@GetMapping
	public ResponseEntity<List<Feedback>> getAllFeedback(){
		List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
		return ResponseEntity.status(HttpStatus.OK).body(feedbacks);
	}
}
