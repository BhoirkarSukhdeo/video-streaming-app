package com.neosoft.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.app.dto.TopupDTO;
import com.neosoft.app.response.ResponseDTO;
import com.neosoft.app.service.TopUpService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/topup")
@RequiredArgsConstructor
public class TopUpController {

	private final Logger logger = LogManager.getLogger(TopUpController.class);
	private final TopUpService topUpService;

	@PostMapping("/addTopup")
	public ResponseEntity<ResponseDTO> saveUser(@RequestBody TopupDTO topupDTO) {
		logger.info("Persist new Topup entity :: " + topupDTO);
		return new ResponseEntity<>(topUpService.addTopUp(topupDTO), HttpStatus.CREATED);
	}

}
