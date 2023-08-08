package com.likelion.checkmate.user.application.UserService;

import com.likelion.checkmate.user.domain.entity.User;
import com.likelion.checkmate.user.domain.repository.UserRepository;
import com.likelion.checkmate.user.presentation.request.ChangeNicknameRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void changeUserInfo(Long userId, String nickname) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setNickname(nickname);
        userRepository.save(user);
    }

    @Transactional
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}