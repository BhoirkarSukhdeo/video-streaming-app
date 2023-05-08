package com.neosoft.app.controller;

import java.util.List;

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

import com.neosoft.app.dto.SubscriptionPlanDTO;
import com.neosoft.app.response.ResponseDTO;
import com.neosoft.app.response.SubscriptionPlanResponse;
import com.neosoft.app.service.SubscriptionPlanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/subscriptionPlan")
@RequiredArgsConstructor
public class SubscriptionPlanController {
	
	private final Logger logger = LogManager.getLogger(SubscriptionPlanController.class);

	private final SubscriptionPlanService subscriptionPlanService;
	
	@PostMapping("/add")
	public ResponseEntity<ResponseDTO> saveUser(@Valid  @RequestBody SubscriptionPlanDTO subscriptionPlanDTO) {
		logger.info("Persist new Subscription Plan :: " + subscriptionPlanDTO);
		return new ResponseEntity<>(subscriptionPlanService.addSubscriptionPlan(subscriptionPlanDTO), HttpStatus.CREATED);
	}

	@GetMapping("getSubscriptionPlans")
	public ResponseEntity<List<SubscriptionPlanResponse>> getAllSubscriptionPlan() {
		logger.info("Get all subscription plan request intersepted");
		return new ResponseEntity<>(subscriptionPlanService.fetchAllSubscriptionPlan(), HttpStatus.OK);
	}

	@GetMapping("getSubscriptionPlan/{id}")
	public ResponseEntity<List<SubscriptionPlanResponse>> getSubscriptionPlan(@PathVariable Long id) {
		logger.info("Get Subscription plan by id  :: " + id);
		return new ResponseEntity<>(subscriptionPlanService.fetchSubscriptionPlan(id), HttpStatus.OK);
	}

}
