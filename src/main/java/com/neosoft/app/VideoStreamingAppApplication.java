package com.neosoft.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VideoStreamingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoStreamingAppApplication.class, args);
	}

}
