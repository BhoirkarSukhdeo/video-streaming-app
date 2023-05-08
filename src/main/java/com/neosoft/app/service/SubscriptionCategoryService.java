package com.neosoft.app.service;

import java.util.List;

import com.neosoft.app.dto.SubscriptionCategoryDTO;
import com.neosoft.app.response.ResponseDTO;
import com.neosoft.app.response.SubCategoryResponseDTO;

public interface SubscriptionCategoryService {

	ResponseDTO addSubscriptionCategory(SubscriptionCategoryDTO subscriptionCategoryDTO);
	List<SubCategoryResponseDTO> getAllSubascriptionCategories();
}
