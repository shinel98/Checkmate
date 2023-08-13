package com.likelion.checkmate.common.controller;

import com.likelion.checkmate.follow.application.FollowService;
import com.likelion.checkmate.follow.domain.entity.Follow;
import com.likelion.checkmate.follow.domain.repository.FollowRepository;
import com.likelion.checkmate.hashtag.domain.entity.Hashtag;
import com.likelion.checkmate.hashtag.domain.repository.HashtagRepository;
import com.likelion.checkmate.have.domain.entity.Have;
import com.likelion.checkmate.have.domain.repository.HaveRepository;
import com.likelion.checkmate.item.domain.entity.Item;
import com.likelion.checkmate.item.domain.repository.ItemRepository;
import com.likelion.checkmate.post.application.service.PostService;
import com.likelion.checkmate.post.domain.entity.Post;
import com.likelion.checkmate.post.domain.repository.PostRepository;
import com.likelion.checkmate.report.domain.entity.Report;
import com.likelion.checkmate.report.domain.repository.ReportRepository;
import com.likelion.checkmate.subtopic.domain.entity.Subtopic;
import com.likelion.checkmate.subtopic.domain.repository.SubtopicRepository;
import com.likelion.checkmate.together.domain.entity.Together;
import com.likelion.checkmate.together.domain.repository.TogetherRepository;
import com.likelion.checkmate.user.application.UserService.UserService;
import com.likelion.checkmate.user.domain.entity.User;
import com.likelion.checkmate.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DummyController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final HashtagRepository hashtagRepository;
    private final ItemRepository itemRepository;
    private final SubtopicRepository subtopicRepository;
    private final TogetherRepository togetherRepository;
    private final ReportRepository reportRepository;
    private final HaveRepository haveRepository;
    private final FollowRepository followRepository;


    private User hyeok;
    private User chang;
    private User seo;
    private User wook;
    private User ji;

    @PostMapping("/dummy")
    public void dummyAll() {
        saveUser();
        savePost();
        saveFollow();
    }

    private void saveUser() {
        hyeok = userRepository.save(User.builder().email("inhyeok38@gmail.com").image("img").name("이인혁").kakaoId(3L).build());
        chang = userRepository.save(User.builder().email("chang@gmail.com").image("img").name("이창건").kakaoId(2L).build());
        seo = userRepository.save(User.builder().email("eun@gmail.com").image("img").name("박은서").kakaoId(3L).build());
        wook = userRepository.save(User.builder().email("wook@gmail.com").image("img").name("김종욱").kakaoId(4L).build());
        ji = userRepository.save(User.builder().email("ji@gmail.com").image("img").name("김예지").kakaoId(5L).build());
    }

    private void savePost() {

        Post hyeokPost1 = postRepository.save(Post.builder().scope(1).title("추천도서목록").user(hyeok).build());
        Post hyeokPost2 = postRepository.save(Post.builder().scope(3).title("추천 영화 100선").uploadDate(LocalDateTime.now()).user(hyeok).build());
        Post hyeokPost3 = postRepository.save(Post.builder().scope(1).title("추천도서목록").user(hyeok).build());

        postRepository.save(Post.builder().scope(1).title("추천도서목록").user(chang).build());
        postRepository.save(Post.builder().scope(3).title("추천 영화 100선").uploadDate(LocalDateTime.now()).user(chang).build());
        postRepository.save(Post.builder().scope(1).title("추천도서목록").user(seo).build());
        postRepository.save(Post.builder().scope(3).title("추천 영화 100선").uploadDate(LocalDateTime.now()).user(seo).build());
        postRepository.save(Post.builder().scope(1).title("추천도서목록").user(wook).build());
        postRepository.save(Post.builder().scope(3).title("추천 영화 100선").uploadDate(LocalDateTime.now()).user(wook).build());
        postRepository.save(Post.builder().scope(1).title("추천도서목록").user(ji).build());
        postRepository.save(Post.builder().scope(3).title("추천 영화 100선").uploadDate(LocalDateTime.now()).user(ji).build());

        hashtagRepository.save(Hashtag.builder().name("20대").post(hyeokPost1).build());
        hashtagRepository.save(Hashtag.builder().name("남자").post(hyeokPost1).build());
        hashtagRepository.save(Hashtag.builder().name("대학생").post(hyeokPost1).build());

        hashtagRepository.save(Hashtag.builder().name("20대").post(hyeokPost2).build());
        hashtagRepository.save(Hashtag.builder().name("액션").post(hyeokPost2).build());
        hashtagRepository.save(Hashtag.builder().name("로맨스").post(hyeokPost2).build());

        hashtagRepository.save(Hashtag.builder().name("20대").post(hyeokPost3).build());
        hashtagRepository.save(Hashtag.builder().name("남자").post(hyeokPost3).build());
        hashtagRepository.save(Hashtag.builder().name("대학생").post(hyeokPost3).build());

        Item item1 = itemRepository.save(Item.builder().content("역행자").count(3).post(hyeokPost1).build());
        Item item2 = itemRepository.save(Item.builder().content("클루지").count(1002).post(hyeokPost1).build());
        Item item3 = itemRepository.save(Item.builder().content("아몬드").count(200).post(hyeokPost1).build());

        itemRepository.save(Item.builder().content("엘리멘탈").count(30).post(hyeokPost2).build());
        Item item4 = itemRepository.save(Item.builder().content("스파이더맨").count(100234).post(hyeokPost2).build());
        itemRepository.save(Item.builder().content("노트북").count(20343).post(hyeokPost2).build());

        Item item5 = itemRepository.save(Item.builder().content("역행자").count(3).post(hyeokPost3).build());
        Item item6 = itemRepository.save(Item.builder().content("클루지").count(1002).post(hyeokPost3).build());
        Item item7 = itemRepository.save(Item.builder().content("아몬드").count(200).post(hyeokPost3).build());

        subtopicRepository.save(Subtopic.builder().name("자기계발").item(item1).build());
        subtopicRepository.save(Subtopic.builder().name("자기계발").item(item2).build());
        subtopicRepository.save(Subtopic.builder().name("소설").item(item3).build());

        subtopicRepository.save(Subtopic.builder().name("마블").item(item4).build());

        subtopicRepository.save(Subtopic.builder().name("자기계발").item(item5).build());
        subtopicRepository.save(Subtopic.builder().name("자기계발").item(item6).build());
        subtopicRepository.save(Subtopic.builder().name("소설").item(item7).build());

        togetherRepository.save(Together.builder().post(hyeokPost1).user(chang).build());
        togetherRepository.save(Together.builder().post(hyeokPost1).user(ji).build());
        togetherRepository.save(Together.builder().post(hyeokPost1).user(seo).build());
        togetherRepository.save(Together.builder().post(hyeokPost1).user(wook).build());

        reportRepository.save(Report.builder().content("부적절한 내용이에요").post(hyeokPost2).build());
        reportRepository.save(Report.builder().content("부적절한 내용이에요2").post(hyeokPost2).build());

        haveRepository.save(Have.builder().destId(3L).post(hyeokPost1).build());
    }

    private void saveFollow() {
        followRepository.save(Follow.builder().follower(hyeok).following(chang).build());
        followRepository.save(Follow.builder().follower(hyeok).following(seo).build());
        followRepository.save(Follow.builder().follower(ji).following(hyeok).build());
        followRepository.save(Follow.builder().follower(wook).following(hyeok).build());
    }

}
