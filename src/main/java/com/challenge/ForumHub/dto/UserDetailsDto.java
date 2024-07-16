package com.challenge.ForumHub.dto;

import com.challenge.ForumHub.entity.User;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
public record UserDetailsDto(
        @NotNull(message = "Um id válido precisa ser informado.")
        Long id,
        String nickname,
        String email
) implements Serializable {
    public UserDetailsDto(User user) {
        this(
                user.getId(),
                user.getNickname(),
                user.getEmail()
        );
    }
}
