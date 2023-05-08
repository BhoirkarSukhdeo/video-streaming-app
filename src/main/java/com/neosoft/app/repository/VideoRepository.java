package com.neosoft.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.app.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

	Optional<Video> findByTitle(String title);

	Optional<Video> findByTitleAndSubscriptionCategoryId(String title, Long categoryId);

}
