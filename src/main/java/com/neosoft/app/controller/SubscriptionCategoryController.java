package com.neosoft.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.app.dto.SubscriptionCategoryDTO;
import com.neosoft.app.response.ResponseDTO;
import com.neosoft.app.response.SubCategoryResponseDTO;
import com.neosoft.app.service.SubscriptionCategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/subscriptionCategory")
@RequiredArgsConstructor
public class SubscriptionCategoryController {

	private final Logger logger = LogManager.getLogger(SubscriptionCategoryController.class);
	
	private final SubscriptionCategoryService subscriptionCategoryService;
	
	@PostMapping("/add")
	public ResponseEntity<ResponseDTO> addSubscriptionCategory(@Valid @RequestBody SubscriptionCategoryDTO subscriptionCategoryDTO) {
		logger.info("Persist new Subscription Category :: " + subscriptionCategoryDTO);
		return new ResponseEntity<>(subscriptionCategoryService.addSubscriptionCategory(subscriptionCategoryDTO), HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllCategories")
	public ResponseEntity<List<SubCategoryResponseDTO>> getAllSubscriptionCategories() {
		logger.info("Get Request intercepted, Subscription Categories");
		return new ResponseEntity<>(subscriptionCategoryService.getAllSubascriptionCategories(), HttpStatus.OK);
	}

}
