package com.neosoft.app.Enum;

public enum SubscriptionPlan {
	
	MUSIC("MUSIC"), PODCAST("PODCAST"), VIDEO("VIDEO");

	private final String subscriptionPlan;

	private SubscriptionPlan(String subscriptionPlan) {
		this.subscriptionPlan = subscriptionPlan;
	}

	public String getSubscriptionPlan() {
		return subscriptionPlan;
	}

	public boolean isValidSubscriptionPlan(String value) {
		for (SubscriptionPlan plan : SubscriptionPlan.values()) {
			if (plan.getSubscriptionPlan().toString().equalsIgnoreCase(value)) 
				return true;
		}

		return false;
	}

}
