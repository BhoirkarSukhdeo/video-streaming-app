package com.neosoft.app.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SubscriptionPlanResponse {

	private Long id;
	private String subscriptionPlan;
	
}
