package com.neosoft.app.Enum;

public enum SubscriptionPlanCategory {
	
	FREE(100.00), PERSONAL(250.00), PRIEMIUM(250.00);

	private final double price;

	private SubscriptionPlanCategory(double price) {
		this.price = price;
	}

	public double getSubscriptionPlan() {
		return price;
	}

	public boolean isValidSubscriptionPlanCategory(Double value) {
		for (SubscriptionPlanCategory plan : SubscriptionPlanCategory.values()) {
			if (plan.getSubscriptionPlan() == value)
				return true;
		}

		return false;
	}

}
