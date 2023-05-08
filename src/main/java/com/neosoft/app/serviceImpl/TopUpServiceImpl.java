package com.neosoft.app.serviceImpl;

import java.time.LocalDate;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.neosoft.app.dto.TopupDTO;
import com.neosoft.app.entity.SubscriptionPlan;
import com.neosoft.app.entity.Topup;
import com.neosoft.app.entity.User;
import com.neosoft.app.exception.InvalidInputFoundException;
import com.neosoft.app.exception.TopupAlreadyExistException;
import com.neosoft.app.repository.SubscriptionPlanRepository;
import com.neosoft.app.repository.TopupRepository;
import com.neosoft.app.repository.UserRepository;
import com.neosoft.app.response.ResponseDTO;
import com.neosoft.app.service.TopUpService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopUpServiceImpl implements TopUpService {

	private final Logger logger = LogManager.getLogger(TopUpServiceImpl.class);

	private final TopupRepository topupRepository;
	private final UserRepository userRepository;
	private final SubscriptionPlanRepository subscriptionPlanRepository;

	@Override
	public ResponseDTO addTopUp(TopupDTO topupDTO) {
		logger.info("In side addTopUp of TopUpServiceImpl");
		validateTopUp(topupDTO);
		Topup topup = Topup.builder().build();
		BeanUtils.copyProperties(topupDTO, topup, "userId", "subscriptionPlanId");
		setDefaultProperties(topupDTO, topup);
		topupRepository.save(topup);
		return new ResponseDTO(topup.getTopupId(), "Top up added sucsessfully..!");
	}

	private void setDefaultProperties(TopupDTO topupDTO, Topup topup) {
		User user = userRepository.findById(topupDTO.getUserId()).orElseThrow(
				() -> new InvalidInputFoundException("User not found with given id :: " + topupDTO.getUserId()));

		SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(topupDTO.getSubscriptionPlanId())
				.orElseThrow(() -> new InvalidInputFoundException(
						"Subscription Plan not found with given id :: " + topupDTO.getSubscriptionPlanId()));
		topup.setSubscriptionPlan(subscriptionPlan);
		topup.setUser(user);
		topup.setExpirationDate(LocalDate.now().plusMonths(1));
		topup.setStartDate(LocalDate.now());
	}

	private void validateTopUp(TopupDTO topupDTO) {
		Optional<Topup> existTopUp = topupRepository.findByUserIdAndSubscriptionPlanId(topupDTO.getUserId(),
				topupDTO.getSubscriptionPlanId());
		if (existTopUp.isPresent()) {
			throw new TopupAlreadyExistException("Top up already added for subscription plan ");
		}
	}

}
