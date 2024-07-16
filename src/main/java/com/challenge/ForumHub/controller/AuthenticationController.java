package com.challenge.ForumHub.controller;

import com.challenge.ForumHub.security.JwtTokenService;
import com.challenge.ForumHub.dto.JwtTokenDto;
import com.challenge.ForumHub.dto.AuthenticationDto;
import com.challenge.ForumHub.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping
    public ResponseEntity<JwtTokenDto> authenticateUser(@RequestBody @Valid AuthenticationDto dto) {
        var tokenJwt = authenticateAndGetJwtToken(dto);

        return ResponseEntity.ok(tokenJwt);
    }

    private JwtTokenDto authenticateAndGetJwtToken(AuthenticationDto dto) {
        var authToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var authentication = authenticationManager.authenticate(authToken);
        var tokenJwt = jwtTokenService.generateToken((User) authentication.getPrincipal());

        return new JwtTokenDto(tokenJwt);
    }
}
