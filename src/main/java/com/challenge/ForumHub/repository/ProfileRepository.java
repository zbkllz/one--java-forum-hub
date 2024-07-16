package com.challenge.ForumHub.repository;

import com.challenge.ForumHub.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByName(String user);
}