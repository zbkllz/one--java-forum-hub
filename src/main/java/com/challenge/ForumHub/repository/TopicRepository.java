package com.challenge.ForumHub.repository;

import com.challenge.ForumHub.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Boolean existsByIdAndUserId(Long id, Long userId);
}