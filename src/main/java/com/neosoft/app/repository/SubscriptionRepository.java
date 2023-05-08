package com.neosoft.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neosoft.app.entity.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{
	
	@Query(value = "select * from subscription where subscription_plan_id=:subscriptionPlanId and user_id=:userId and subscription_category_id=:subscriptionCategoryId",nativeQuery = true)
	Optional<Subscription> findBySubscriptionPlanAndUserAndSubscriptionCategory(Long subscriptionPlanId, Long userId,Long subscriptionCategoryId);

	
	@Query(value = "select * from subscription where is_subscription_plan_expire=false",nativeQuery = true)
	List<Subscription> findAllByIsSubscriptionPlanExpire();
	
	List<Subscription> findByUserId(Long userId);
}
