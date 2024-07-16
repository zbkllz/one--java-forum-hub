package com.challenge.ForumHub.dto;

import com.challenge.ForumHub.entity.Answer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link Answer}
 */
public record AnswerCreateDto(
        @NotNull(message = "Informe o id do tópico onde a resposta será criada.")
        Long topicId,

        @NotBlank(message = "É preciso informar o texto da resposta.")
        String message
) implements Serializable {
}
