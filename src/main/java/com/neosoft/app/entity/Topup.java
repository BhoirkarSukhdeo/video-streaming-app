package com.neosoft.app.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "topup")
public class Topup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long topupId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "subscription_plan_id")
	private SubscriptionPlan subscriptionPlan;

	private double cost;
	private LocalDate startDate;
	private LocalDate expirationDate;
	

}
