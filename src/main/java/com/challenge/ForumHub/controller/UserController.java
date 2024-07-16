package com.challenge.ForumHub.controller;

import com.challenge.ForumHub.dto.UserDetailsDto;
import com.challenge.ForumHub.dto.UserUpdateDto;
import com.challenge.ForumHub.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDetailsDto>> list(Pageable pageable) {
        var page = userService.list(pageable).map(UserDetailsDto::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid UserUpdateDto dto) {
        if (userService.update(dto) != null) {
            var user = userService.update(dto);

            return ResponseEntity.ok(new UserDetailsDto(user));
        }

        return ResponseEntity.status(401).body("Acesso não autorizado.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (userService.delete(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(401).body("Acesso não autorizado.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDto> detail(@PathVariable Long id) {
        var user = userService.detail(id);

        return ResponseEntity.ok(new UserDetailsDto(user));
    }
}
