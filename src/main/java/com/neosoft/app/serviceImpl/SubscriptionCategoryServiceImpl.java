package com.neosoft.app.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.neosoft.app.dto.SubscriptionCategoryDTO;
import com.neosoft.app.entity.SubscriptionCategory;
import com.neosoft.app.exception.SubscriptionCategoryAlreadyExistException;
import com.neosoft.app.repository.SubscriptionCategoryRepository;
import com.neosoft.app.response.ResponseDTO;
import com.neosoft.app.response.SubCategoryResponseDTO;
import com.neosoft.app.service.SubscriptionCategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionCategoryServiceImpl implements SubscriptionCategoryService {
	
	
	private final Logger logger = LogManager.getLogger(SubscriptionCategoryServiceImpl.class);
	
	private final SubscriptionCategoryRepository subscriptionCategoryRepository;
	
	
	@Override
	public ResponseDTO addSubscriptionCategory(SubscriptionCategoryDTO subscriptionCategoryDTO) {
		logger.info("Inside addSubscriptionCategory method of SubscriptionCategoryServiceImpl :: " + subscriptionCategoryDTO);
		validateSubscriptionCategory(subscriptionCategoryDTO.getName());
		SubscriptionCategory subscriptionCategory = SubscriptionCategory.builder().build();
		subscriptionCategory.setName(subscriptionCategoryDTO.getName());
		subscriptionCategoryRepository.save(subscriptionCategory);
		return new ResponseDTO(subscriptionCategory.getId(), "Subscription category added sucsessfully..!");
	}

	private void validateSubscriptionCategory(String name) {
		Optional<SubscriptionCategory> existsSubscriptionCategory = subscriptionCategoryRepository.findByName(name);
		if (existsSubscriptionCategory.isPresent()) {
			logger.error("Subscription category already exist with the same name : " + existsSubscriptionCategory.get().getName());
			throw new SubscriptionCategoryAlreadyExistException("Subscription category already exist with the same name : " + existsSubscriptionCategory.get().getName());
		}
	}

	@Override
	public List<SubCategoryResponseDTO> getAllSubascriptionCategories() {
		logger.info("Inside getAllSubascriptionCategories method of SubscriptionCategoryServiceImpl");
		return subscriptionCategoryRepository.findAll().stream()
				.map(subscriptionCategoryRepository -> SubCategoryResponseDTO.builder()
						.id(subscriptionCategoryRepository.getId()).name(subscriptionCategoryRepository.getName())
						.build()

				).collect(Collectors.toList());
	}
}
