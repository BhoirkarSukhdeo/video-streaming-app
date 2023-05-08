package com.neosoft.app.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.app.dto.SubscriptionDTO;
import com.neosoft.app.response.ResponseDTO;
import com.neosoft.app.response.UserSubsplanResponse;
import com.neosoft.app.service.SubscriptionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionController {
	
	private final Logger logger = LogManager.getLogger(SubscriptionController.class);
	
	private final SubscriptionService subscriptionService;
	
	@PostMapping("/addSubscription")
	public ResponseEntity<ResponseDTO> userSubscriptionPlan(@Valid @RequestBody SubscriptionDTO subscriptionDTO) {
		logger.info("Add user subscription plan :: " + subscriptionDTO);
		return new ResponseEntity<>(subscriptionService.userSubscriptionPlan(subscriptionDTO), HttpStatus.CREATED);
	}
	
	@GetMapping("/getSubscriptions/{userId}")
	public ResponseEntity<UserSubsplanResponse> getUserSubscriptionPlan(@PathVariable Long userId) {
		logger.info("Get user subscription plan :: " + userId);
		return new ResponseEntity<>(subscriptionService.getUserSubscriptionPlan(userId), HttpStatus.CREATED);
	}

}
