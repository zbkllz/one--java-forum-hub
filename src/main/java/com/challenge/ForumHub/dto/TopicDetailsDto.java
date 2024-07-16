package com.challenge.ForumHub.dto;

import com.challenge.ForumHub.configuration.DateAndTimeConfigurations;
import com.challenge.ForumHub.entity.Topic;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link Topic}
 */
public record TopicDetailsDto(
        @NotNull(message = "Um id válido precisa ser informado.")
        Long id,
        String title,
        String message,
        String user,
        Long userId,
        String course,
        String category,
        String status,
        String registerDate
) implements Serializable {
    public TopicDetailsDto(Topic topic) {
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getUser().getNickname(),
                topic.getUser().getId(),
                topic.getCourse().getName(),
                topic.getCourse().getCategory(),
                topic.isStatus() ? "Tópico Ativo" : "Tópico Inativo",
                DateAndTimeConfigurations.formatToBr(topic.getRegisterDate())
        );
    }
}
