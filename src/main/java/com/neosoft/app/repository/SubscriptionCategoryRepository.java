package com.neosoft.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.app.entity.SubscriptionCategory;

@Repository
public interface SubscriptionCategoryRepository extends JpaRepository<SubscriptionCategory, Long> {

	Optional<SubscriptionCategory> findByName(String name);

}
