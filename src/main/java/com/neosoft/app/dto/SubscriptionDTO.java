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
public class SubscriptionDTO {
	
	private Long subscriptionPlanId;
	private Long userId;
	private Long subscriptionCategoryId;
	private double cost;
	
}
