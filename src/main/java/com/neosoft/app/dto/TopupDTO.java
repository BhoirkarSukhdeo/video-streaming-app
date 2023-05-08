package com.neosoft.app.dto;

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
public class TopupDTO {

	private Long userId;
	private Long subscriptionPlanId;
	private double cost;
	
}
