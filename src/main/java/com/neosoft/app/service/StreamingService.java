package com.neosoft.app.service;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.neosoft.app.response.ResponseDTO;

import reactor.core.publisher.Mono;

public interface StreamingService {

	Mono<Resource> getVideo(String title, Long categoryId);

	ResponseDTO uploadVideo(MultipartFile file)
			throws IllegalStateException, IOException;

}
