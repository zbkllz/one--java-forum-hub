package com.challenge.ForumHub.service;

import com.challenge.ForumHub.controller.RegisterUserController;
import com.challenge.ForumHub.entity.User;
import com.challenge.ForumHub.repository.ProfileRepository;
import com.challenge.ForumHub.repository.UserRepository;
import com.challenge.ForumHub.dto.RegisterUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for {@link RegisterUserController}
 */
@Service
public class RegisterUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Transactional
    public User register(RegisterUserDto dto) {
        var userProfile = profileRepository.findByName("user");
        var user = new User();
        var encoder = new BCryptPasswordEncoder();
        var encodedPassword = encoder.encode(dto.password());

        user.addProfile(userProfile);
        user.setNickname(dto.nickname());
        user.setEmail(dto.email());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        return user;
    }
}
