package com.neosoft.app.service;

import com.neosoft.app.dto.SubscriptionDTO;
import com.neosoft.app.response.ResponseDTO;
import com.neosoft.app.response.UserSubsplanResponse;

public interface SubscriptionService {

	ResponseDTO userSubscriptionPlan(SubscriptionDTO subscriptionDTO);

	UserSubsplanResponse getUserSubscriptionPlan(Long userId);

}