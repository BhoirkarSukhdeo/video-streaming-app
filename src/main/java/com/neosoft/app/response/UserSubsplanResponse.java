package com.neosoft.app.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSubsplanResponse {
	
	List<UserSubscriptionPlanResponse> plans = new ArrayList<>();

}
