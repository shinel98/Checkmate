package com.likelion.checkmate.user.application.UserService;

import com.likelion.checkmate.follow.application.dto.FollowDto;
import com.likelion.checkmate.follow.domain.entity.Follow;
import com.likelion.checkmate.follow.domain.repository.FollowRepository;
import com.likelion.checkmate.have.domain.repository.HaveRepository;
import com.likelion.checkmate.jwt.JwtPair;
import com.likelion.checkmate.post.application.dto.PostHomeDto;
import com.likelion.checkmate.post.domain.entity.Post;
import com.likelion.checkmate.post.domain.repository.PostRepository;
import com.likelion.checkmate.user.application.dto.MyPageDto;
import com.likelion.checkmate.user.domain.entity.User;
import com.likelion.checkmate.user.domain.repository.UserRepository;
import com.likelion.checkmate.user.presentation.request.ChangeNicknameRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final HaveRepository haveRepository;
    private final FollowRepository followRepository;

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

    @Transactional
    public MyPageDto readMyPage(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Post> myPostList = postRepository.findMyListOrderByModDateDesc(userId);
        List<Post> myTogetherList = postRepository.findTogetherListOrderByUploadDateDesc(userId);

        List<PostHomeDto> myPostDtoList = myPostList.stream()
                .map(post -> {
                    int count = haveRepository.findAllGetPost(post.getId());
                    return PostHomeDto.toLocalDto(post, count);
                })
                .collect(Collectors.toList());

        List<PostHomeDto> myTogetherDtoList = myTogetherList.stream()
                .map(post -> {
                    int count = haveRepository.findAllGetPost(post.getId());
                    return PostHomeDto.toDto(post, count);
                })
                .collect(Collectors.toList());

        List<Follow> followerList = followRepository.findAllByFollowingId(userId);
        List<Follow> followingList = followRepository.findAllByFollowerId(userId);

        return MyPageDto.toDto(user, followerList.size(), followingList.size(), myPostDtoList, myTogetherDtoList);
    }

    public Optional<User> isPresent(Long kakao_id) {
        return Optional.ofNullable(userRepository.findUserByKakaoId(kakao_id));
    }

    @Transactional
    public Long saveUser(HashMap<String, Object> userInfo, JwtPair tokens) {
        User user = userRepository.findUserByKakaoId(Long.parseLong(userInfo.get("userKakaoId").toString()));
        user.setRefreshToken(tokens.getRefreshToken());
        userRepository.save(user);
        return user.getId();
    }
    @Transactional
    public Long saveUser(HashMap<String, Object> userInfo) {
        User newUser = userRepository.save(User.toEntity(userInfo));
        return newUser.getId();
    }

}