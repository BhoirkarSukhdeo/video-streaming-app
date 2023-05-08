package com.neosoft.app.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "device")
public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	private String name;

	@Builder.Default
	private LocalDateTime createdDate = LocalDateTime.now();
	private LocalDateTime modifiedDate;

	@ManyToMany
	@JoinTable(name = "subscription_plan_device", joinColumns = {
			@JoinColumn(name = "device_plan") }, inverseJoinColumns = { @JoinColumn(name = "subscription_plan") })
	private List<SubscriptionPlan> subscriptionPlans = new ArrayList<>();

}
