package com.challenge.ForumHub.dto;

import com.challenge.ForumHub.entity.Answer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link Answer}
 */
public record AnswerUpdateDto(
        @NotNull(message = "É necessário informar o id da resposta.")
        Long id,

        @NotBlank(message = "A mensagem não pode estar em branco.")
        String message
) implements Serializable {
}
