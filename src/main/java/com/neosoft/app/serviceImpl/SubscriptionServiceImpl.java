package com.neosoft.app.serviceImpl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.neosoft.app.dto.EmailDetailsDTO;
import com.neosoft.app.dto.SubscriptionDTO;
import com.neosoft.app.entity.Subscription;
import com.neosoft.app.entity.SubscriptionCategory;
import com.neosoft.app.entity.SubscriptionPlan;
import com.neosoft.app.entity.User;
import com.neosoft.app.exception.DurationException;
import com.neosoft.app.exception.SubscriptionCategoryNotFoundException;
import com.neosoft.app.exception.SubscriptionPlanAlreadyExistException;
import com.neosoft.app.exception.SubscriptionPlanNotFoundException;
import com.neosoft.app.exception.UserNotFoundException;
import com.neosoft.app.repository.SubscriptionCategoryRepository;
import com.neosoft.app.repository.SubscriptionPlanRepository;
import com.neosoft.app.repository.SubscriptionRepository;
import com.neosoft.app.repository.UserRepository;
import com.neosoft.app.response.ResponseDTO;
import com.neosoft.app.response.UserCategoryResponse;
import com.neosoft.app.response.UserSubscriptionPlanResponse;
import com.neosoft.app.response.UserSubsplanResponse;
import com.neosoft.app.service.EmailService;
import com.neosoft.app.service.SubscriptionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

	private final Logger logger = LogManager.getLogger(SubscriptionServiceImpl.class);

	private final SubscriptionCategoryRepository subscriptionCategoryRepository;
	private final SubscriptionPlanRepository SubscriptionPlanRepository;
	private final UserRepository userRepository;
	private final SubscriptionRepository subscriptionRepository;
	private final EmailService emailService;

	@Override
	public ResponseDTO userSubscriptionPlan(SubscriptionDTO subscriptionDTO) {
		logger.info("Add user subscription plan :: " + subscriptionDTO);
		
		validateSubscription(subscriptionDTO);
		
		Subscription subscription = Subscription.builder().build();
		BeanUtils.copyProperties(subscriptionDTO, subscription, "subscriptionPlanId", "userId",
				"subscriptionCategoryId","expirationDate","startDate");
		
		setProperties(subscription, subscriptionDTO);
		validateDuration(subscription);
		
		subscriptionRepository.save(subscription);

		return new ResponseDTO(subscription.getId(), "User subscription added successfully..!");
	}

	private void setProperties(Subscription subscription, SubscriptionDTO subscriptionDTO) {
		
		User user = userRepository.findById(subscriptionDTO.getUserId()).orElseThrow(
				() -> new UserNotFoundException("User not found with id :: " + subscriptionDTO.getUserId()));

				
		SubscriptionCategory subscriptionCategory = subscriptionCategoryRepository
				.findById(subscriptionDTO.getSubscriptionCategoryId())
				.orElseThrow(() -> new SubscriptionCategoryNotFoundException(
						"Subscription Category not found with id :: " + subscriptionDTO.getSubscriptionCategoryId()));
		
		
		SubscriptionPlan subscriptionPlan = SubscriptionPlanRepository.findById(subscriptionDTO.getSubscriptionPlanId())
				.orElseThrow(() -> new SubscriptionPlanNotFoundException(
						"Subscription plan not found with id :: " + subscriptionDTO.getSubscriptionPlanId()));
		
		subscription.setUser(user);
		subscription.setSubscriptionCategory(subscriptionCategory);
		subscription.setSubscriptionPlan(subscriptionPlan);
		
	}

	private void validateSubscription(SubscriptionDTO subscriptionDTO) {
		Optional<Subscription> subOptional = subscriptionRepository
				.findBySubscriptionPlanAndUserAndSubscriptionCategory(subscriptionDTO.getSubscriptionPlanId(),
						subscriptionDTO.getUserId(), subscriptionDTO.getSubscriptionCategoryId());

		if (subOptional.isPresent()) {
			throw new SubscriptionPlanAlreadyExistException("Subscription already exist with subscription plan :: "
					+ subOptional.get().getSubscriptionPlan().getSubscriptionType());

		}
			
	}
	
	private void validateDuration(Subscription subscription) {
		LocalDate currentDate = LocalDate.now();
		if (subscription.getSubscriptionCategory().getName().equalsIgnoreCase("Free")
				|| subscription.getSubscriptionCategory().getName().equalsIgnoreCase("Personal")) {
			LocalDate monthDate = LocalDate.now().plusMonths(1);
			Period period = Period.between(currentDate, monthDate);
			if (period.getYears() == 0 && period.getMonths() != 1 && period.getDays() == 0) {
				throw new DurationException("Duration for Free Subscription plan is One Month only..!");
			}
			subscription.setExpirationDate(monthDate);
		} else if (subscription.getSubscriptionCategory().getName().equalsIgnoreCase("Premium")) {
			LocalDate monthDate = LocalDate.now().plusMonths(3);
			Period period = Period.between(currentDate, monthDate);
			if (period.getYears() == 0 && period.getMonths() != 3 && period.getDays() == 0) {
				throw new DurationException("Duration for Premium Subscription plan is three Month only..!");
			}
			subscription.setExpirationDate(monthDate);

		}
	}
	
	@Scheduled(cron = "0  24   13   *   *   *")
	public void scheduleTaskUsingCronExpression() {
		logger.info("Inside scheduleTaskUsingCronExpression() of SubscriptionServiceImpl to excecute scheduler");

		subscriptionRepository.findAllByIsSubscriptionPlanExpire().forEach(subscription -> {
			Period period = Period.between(LocalDate.now(), subscription.getExpirationDate());
			if (period.getDays() == 10) {
				logger.info("Notify before 10 days");
				EmailDetailsDTO emailDTO = EmailDetailsDTO.builder().recipient(subscription.getUser().getUsername())
						.msgBody("Hello User,\n\n Your plan will be expire on "+subscription.getExpirationDate()
								+ " date. \n Please recharge. The total amount for renewal is " + subscription.getCost()
								+ ".\n\nBest regards,\n")
						.subject("Subscription Plan Expire Notification").build();
				String sendSimpleMail = emailService.sendSimpleMail(emailDTO);
				logger.info(sendSimpleMail);

			}

		});

	}

	@Override
	public UserSubsplanResponse getUserSubscriptionPlan(Long userId) {
		List<Subscription> subscriptions = subscriptionRepository.findByUserId(userId);
		UserSubsplanResponse userSubsplanResponse = new UserSubsplanResponse();
		List<UserSubscriptionPlanResponse> list = new ArrayList<>();
		if (!subscriptions.isEmpty()) {
			Map<SubscriptionPlan, List<Subscription>> subList = subscriptions.stream()
					.collect(Collectors.groupingBy(Subscription::getSubscriptionPlan));

			subList.forEach((k, v) -> {
				UserSubscriptionPlanResponse build = UserSubscriptionPlanResponse.builder()
						.subscriptionPlanId(k.getId()).subscriptionType(k.getSubscriptionType())
						.categories(v.stream()
								.map(c -> UserCategoryResponse.builder().categoryId(c.getSubscriptionCategory().getId())
										.category(c.getSubscriptionCategory().getName()).cost(c.getCost())
										.expireDate(c.getExpirationDate()).build())
								.collect(Collectors.toList()))
						.build();
				list.add(build);
			});

		}
		userSubsplanResponse.setPlans(list);
		return userSubsplanResponse;
	}
}
