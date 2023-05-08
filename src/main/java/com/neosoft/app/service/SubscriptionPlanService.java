package com.neosoft.app.service;

import java.util.List;

import com.neosoft.app.dto.SubscriptionPlanDTO;
import com.neosoft.app.response.ResponseDTO;
import com.neosoft.app.response.SubscriptionPlanResponse;

public interface SubscriptionPlanService {
	
	 ResponseDTO addSubscriptionPlan(SubscriptionPlanDTO subscriptionPlanDTO);
	 
	 List<SubscriptionPlanResponse> fetchAllSubscriptionPlan();
	 
	 List<SubscriptionPlanResponse> fetchSubscriptionPlan(Long id);
	
	

}
