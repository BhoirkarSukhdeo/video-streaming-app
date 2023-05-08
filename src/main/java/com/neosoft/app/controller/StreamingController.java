package com.neosoft.app.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neosoft.app.response.ResponseDTO;
import com.neosoft.app.service.StreamingService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/streaming")
public class StreamingController {

	private final Logger logger = LogManager.getLogger(StreamingController.class);

	@Autowired
	private StreamingService service;
	

	@GetMapping(value = "/video/{title}/{categoryId}", produces = "video/mp4")
	public Mono<Resource> getVideo(@PathVariable String title, @PathVariable Long categoryId) {
		logger.info("Get Request intercepted for video :: " + title);
		return service.getVideo(title, categoryId);
	}

	@PostMapping(value = "/upload-video-mp3songs")
	public ResponseEntity<ResponseDTO> uploadVideo(
			@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		logger.info("Post Request intercepted for upload video/images :: ");
		return new ResponseEntity<ResponseDTO>(service.uploadVideo(file), HttpStatus.OK);
	}

}
