package com.challenge.ForumHub.repository;

import com.challenge.ForumHub.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Boolean existsByIdAndUserId(Long id, Long userId);
}