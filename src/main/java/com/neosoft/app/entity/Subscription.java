package com.neosoft.app.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subscription {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private Long id;
	
	
	@ManyToOne
    @JoinColumn(name = "subscription_plan_id")
    private SubscriptionPlan subscriptionPlan;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	@ManyToOne
    @JoinColumn(name = "subscription_category_id")
    private SubscriptionCategory subscriptionCategory;
	
	@Column(name = "cost")
    private Double cost;
	
	@Builder.Default
	@Column(name = "start_date")
	private LocalDate startDate = LocalDate.now();
	
	@Column(name = "expiration_date")
    private LocalDate expirationDate;
	
	@Column(name = "is_subscription_plan_expire")
	private boolean isSubscriptionPlanExpire;


}
