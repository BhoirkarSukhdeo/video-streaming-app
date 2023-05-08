package com.neosoft.app.dto;

import javax.validation.constraints.NotBlank;

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
public class UserRequestDTO {
	
	@NotBlank(message = "userName is mandatory")
	private String username;
	
	@NotBlank(message = "Email is mandatory")
	private String password;
	
}
