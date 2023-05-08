package com.neosoft.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.app.entity.SubscriptionPlan;

@Repository
public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long>{
	

	Optional<SubscriptionPlan> findBySubscriptionType(String subscriptionType);
	

}

