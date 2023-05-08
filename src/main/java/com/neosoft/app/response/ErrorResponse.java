package com.neosoft.app.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
	
	
	private String message;
	private LocalDateTime localDateTime;
	private int statusCode;

}
