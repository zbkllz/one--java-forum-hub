package com.challenge.ForumHub.repository;

import com.challenge.ForumHub.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findFirstByName(String name);
}