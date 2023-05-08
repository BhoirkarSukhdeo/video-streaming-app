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
public class SubscriptionPlanDTO {
	
	@NotBlank(message = "Subscription type is mandatory")
	private String subcriptionType;
	

}
