package com.challenge.ForumHub.service;

import com.challenge.ForumHub.configuration.DateAndTimeConfigurations;
import com.challenge.ForumHub.controller.AnswerController;
import com.challenge.ForumHub.entity.Answer;
import com.challenge.ForumHub.entity.User;
import com.challenge.ForumHub.repository.AnswerRepository;
import com.challenge.ForumHub.repository.TopicRepository;
import com.challenge.ForumHub.repository.UserRepository;
import com.challenge.ForumHub.dto.AnswerCreateDto;
import com.challenge.ForumHub.dto.AnswerUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for {@link AnswerController}
 */
@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicRepository topicRepository;

    @Transactional
    public Answer create(AnswerCreateDto dto) {
        var answer = new Answer();
        var topic = topicRepository.findById(dto.topicId()).orElseThrow();
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userRepository.findById(authenticatedUser.getId()).orElseThrow();
        var now = DateAndTimeConfigurations.generateDateForNow();

        answer.setTopic(topic);
        answer.setUser(user);
        answer.setRegisterDate(now);
        answer.setLastUpdate(now);
        answer.setMessage(dto.message());

        user.addAnswer(answer);
        topic.addAnswer(answer);

        answerRepository.save(answer);

        return answer;
    }

    public Page<Answer> list(Pageable pageable) {
        return answerRepository.findAll(pageable);
    }

    @Transactional
    public Answer update(AnswerUpdateDto dto) {
        Answer answer = null;

        if (isUserAuthorized(dto.id())) {
            answer = answerRepository.findById(dto.id()).orElseThrow();
            var now = DateAndTimeConfigurations.generateDateForNow();
            answer.setLastUpdate(now);
            answer.setMessage(dto.message());
            answerRepository.save(answer);
        }

        return answer;
    }

    @Transactional
    public boolean delete(Long id) {

        if (isUserAuthorized(id)) {
            answerRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public Answer detail(Long id) {
        return answerRepository.findById(id).orElseThrow();
    }

    private boolean isUserAuthorized(Long id) {
        var answer = answerRepository.findById(id).orElseThrow();
        var authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return answerRepository.existsByIdAndUserId(answer.getId(), authenticatedUser.getId());
    }
}
