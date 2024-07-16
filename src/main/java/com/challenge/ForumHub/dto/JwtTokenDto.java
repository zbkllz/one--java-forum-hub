package com.challenge.ForumHub.dto;

import java.io.Serializable;

import com.challenge.ForumHub.controller.AuthenticationController;

/**
 * DTO for {@link AuthenticationController}
 */
public record JwtTokenDto(String token) implements Serializable {
}
