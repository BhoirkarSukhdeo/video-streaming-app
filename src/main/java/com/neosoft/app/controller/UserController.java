package com.neosoft.app.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.app.dto.UserRequestDTO;
import com.neosoft.app.response.ResponseDTO;
import com.neosoft.app.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final Logger logger = LogManager.getLogger(UserController.class);

	private final UserService userService;

	@PostMapping("saveUser")
	public ResponseEntity<ResponseDTO> saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
		logger.info("Persist new User :: " + userRequestDTO);
		return new ResponseEntity<>(userService.saveUser(userRequestDTO), HttpStatus.CREATED);
	}
}
