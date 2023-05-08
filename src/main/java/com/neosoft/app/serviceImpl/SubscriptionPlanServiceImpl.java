package com.neosoft.app.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.neosoft.app.dto.SubscriptionPlanDTO;
import com.neosoft.app.entity.SubscriptionPlan;
import com.neosoft.app.exception.SubscriptionPlanAlreadyExistException;
import com.neosoft.app.repository.SubscriptionPlanRepository;
import com.neosoft.app.response.ResponseDTO;
import com.neosoft.app.response.SubscriptionPlanResponse;
import com.neosoft.app.service.SubscriptionPlanService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

	private final Logger logger = LogManager.getLogger(SubscriptionPlanServiceImpl.class);

	private final SubscriptionPlanRepository subscriptionPlanRepository;

	@Override
	public List<SubscriptionPlanResponse> fetchAllSubscriptionPlan() {
		logger.info("Inside fetchAllSubscriptionPlan method of SubscriptionPlanServiceImpl");
		return subscriptionPlanRepository.findAll().stream().map(sub -> SubscriptionPlanResponse.builder()
				.id(sub.getId()).subscriptionPlan(sub.getSubscriptionType()).build()

		).collect(Collectors.toList());
	}

	@Override
	public ResponseDTO addSubscriptionPlan(SubscriptionPlanDTO subscriptionPlanDTO) {
		logger.info("Inside addSubscriptionPlan method of SubscriptionPlanServiceImpl :: " + subscriptionPlanDTO);
		validateSubscriptionPlan(subscriptionPlanDTO);
		SubscriptionPlan subscriptionPlan = SubscriptionPlan.builder().build();
		subscriptionPlan.setSubscriptionType(subscriptionPlanDTO.getSubcriptionType());
		subscriptionPlanRepository.save(subscriptionPlan);
		return new ResponseDTO(subscriptionPlan.getId(), "Subscription plan added sucsessfully..!");
	}

	private void validateSubscriptionPlan(SubscriptionPlanDTO subscriptionPlanDTO) {
		Optional<SubscriptionPlan> existsSubscriptionPlan = subscriptionPlanRepository
				.findBySubscriptionType(subscriptionPlanDTO.getSubcriptionType());
		if (existsSubscriptionPlan.isPresent()) {
			logger.error(
					"Subscription plan already exist with the same name : " + subscriptionPlanDTO.getSubcriptionType());
			throw new SubscriptionPlanAlreadyExistException(
					"Subscription plan already exist with the same name : " + subscriptionPlanDTO.getSubcriptionType());
		}
	}

	@Override
	public List<SubscriptionPlanResponse> fetchSubscriptionPlan(Long id) {
		return null;
	}

}
