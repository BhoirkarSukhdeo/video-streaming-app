package com.neosoft.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neosoft.app.entity.Topup;

@Repository
public interface TopupRepository extends JpaRepository<Topup, Long> {

	Optional<Topup> findByUserIdAndSubscriptionPlanId(Long userId, Long subId);
	
	
	@Query(value = "select count(subscription_plan) from subscription_plan_device where subscription_plan=:subscriptionPlanId",nativeQuery = true)
	Long getTotalDeviceCount(Long subscriptionPlanId);
	

}
