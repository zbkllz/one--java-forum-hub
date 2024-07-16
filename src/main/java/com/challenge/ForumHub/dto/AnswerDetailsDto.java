package com.challenge.ForumHub.dto;

import com.challenge.ForumHub.controller.AnswerController;
import com.challenge.ForumHub.entity.Answer;

import java.io.Serializable;

/**
 * DTO for {@link AnswerController}
 */
public record AnswerDetailsDto(
        Long id,
        Long userId,
        String nickname,
        Long topicId,
        String title,
        String message
) implements Serializable {
    public AnswerDetailsDto(Answer answer) {
        this(
                answer.getId(),
                answer.getUser().getId(),
                answer.getUser().getNickname(),
                answer.getTopic().getId(),
                answer.getTopic().getTitle(),
                answer.getMessage()
        );
    }
}
