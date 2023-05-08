package com.neosoft.app.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SubscriptionCategoryResponse {
	
	private Long id;
	private String category;
	private float cost;

}
