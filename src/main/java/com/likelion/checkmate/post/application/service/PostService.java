package com.likelion.checkmate.post.application.service;

import com.likelion.checkmate.hashtag.domain.entity.Hashtag;
import com.likelion.checkmate.hashtag.domain.repository.HashtagRepository;
import com.likelion.checkmate.have.domain.repository.HaveRepository;
import com.likelion.checkmate.item.domain.entity.Item;
import com.likelion.checkmate.item.domain.repository.ItemRepository;
import com.likelion.checkmate.post.application.dto.PostDeleteDto;
import com.likelion.checkmate.post.application.dto.PostDto;
import com.likelion.checkmate.post.application.dto.PostHomeDto;
import com.likelion.checkmate.post.domain.entity.Post;
import com.likelion.checkmate.post.domain.repository.PostRepository;
import com.likelion.checkmate.post.presentation.request.PostRequest;
import com.likelion.checkmate.post.presentation.response.PostDetailResponse;
import com.likelion.checkmate.subtopic.domain.entity.Subtopic;
import com.likelion.checkmate.subtopic.domain.repository.SubtopicRepository;
import com.likelion.checkmate.user.domain.entity.User;
import com.likelion.checkmate.user.domain.repository.UserRepository;
import com.likelion.checkmate.usercheck.domain.entity.Usercheck;
import com.likelion.checkmate.usercheck.presentation.controller.UsercheckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final HashtagRepository hashtagRepository;
    private final ItemRepository itemRepository;
    private final SubtopicRepository subtopicRepository;
    private final HaveRepository haveRepository;
    private final UserRepository userRepository;
    private final UsercheckRepository usercheckRepository;

    @Transactional
    public Long update(PostDto dto) {
        Post post = postRepository.findByIdAndUserId(dto.getPostId(), dto.getUserId());

        if (dto.getScope() == 3) {
            LocalDateTime now = LocalDateTime.now();
            post.update(dto.getTitle(), dto.getScope(), now);
        } else {
            post.update(dto.getTitle(), dto.getScope());
        }

        List<Item> items = itemRepository.findByPostId(dto.getPostId());

        items.get(0).getId();
        if (items != null && !items.isEmpty())
            subtopicRepository.deleteAllByItemId(items.get(0).getId());

        hashtagRepository.deleteAllByPostId(dto.getPostId());

        itemRepository.deleteAllByPostId(dto.getPostId());

        for (String name : dto.getHashtags()) {
            hashtagRepository.save(Hashtag.toEntity(name, post));
        }

        for (PostRequest.ContentData data : dto.getContent()) {
            Item item = Item.toEntity(data.getContent(), data.getCount(), post);
            itemRepository.save(item);
            subtopicRepository.save(Subtopic.toEntity(data.getCategory(), item));
        }

        return post.getId();
    }

    @Transactional
    public List<PostHomeDto> getPostListByTime() {
        List<Post> postList = postRepository.findAllOrderByUploadDateDesc();

        List<PostHomeDto> postHomeDtoList = postList.stream()
                .map(post -> {
                    // 이 리스트는 각 item에 대한 userId의 목록을 저장합니다.
                    List<PostHomeDto.ContentData> contentDataList = post.getItemList().stream()
                            .map(item -> {
                                List<Long> checkedListForItem = usercheckRepository.findAllUserCheckList(post.getId(), item.getId());
                                PostHomeDto.ContentData contentData = new PostHomeDto.ContentData();
                                contentData.setCategory(item.getSubtopic() != null ? item.getSubtopic().getName() : null);
                                contentData.setItemId(item.getId());
                                contentData.setContent(item.getContent());
                                contentData.setCheck(checkedListForItem);
                                return contentData;
                            })
                            .collect(Collectors.toList());

                    int count = haveRepository.findAllGetPost(post.getId());

                    return PostHomeDto.toDto(post, count, contentDataList);
                })
                .collect(Collectors.toList());
        return postHomeDtoList;
    }

    @Transactional
    public List<PostHomeDto> getPostListByHave() {
        List<Post> postList = postRepository.findPostsByMostCountedHave();


        List<PostHomeDto> postHomeDtoList = postList.stream()
                .map(post -> {
                    // 이 리스트는 각 item에 대한 userId의 목록을 저장합니다.
                    List<PostHomeDto.ContentData> contentDataList = post.getItemList().stream()
                            .map(item -> {
                                List<Long> checkedListForItem = usercheckRepository.findAllUserCheckList(post.getId(), item.getId());
                                PostHomeDto.ContentData contentData = new PostHomeDto.ContentData();
                                contentData.setCategory(item.getSubtopic() != null ? item.getSubtopic().getName() : null);
                                contentData.setItemId(item.getId());
                                contentData.setContent(item.getContent());
                                contentData.setCheck(checkedListForItem);
                                return contentData;
                            })
                            .collect(Collectors.toList());

                    int count = haveRepository.findAllGetPost(post.getId());

                    return PostHomeDto.toDto(post, count, contentDataList);
                })
                .collect(Collectors.toList());

        return postHomeDtoList;
    }

    @Transactional
    public List<PostHomeDto> getPostListByTogether() {
        List<Post> postList = postRepository.findPostsByMostCountedTogether();

        List<PostHomeDto> postHomeDtoList = postList.stream()
                .map(post -> {
                    // 이 리스트는 각 item에 대한 userId의 목록을 저장합니다.
                    List<PostHomeDto.ContentData> contentDataList = post.getItemList().stream()
                            .map(item -> {
                                List<Long> checkedListForItem = usercheckRepository.findAllUserCheckList(post.getId(), item.getId());
                                PostHomeDto.ContentData contentData = new PostHomeDto.ContentData();
                                contentData.setCategory(item.getSubtopic() != null ? item.getSubtopic().getName() : null);
                                contentData.setItemId(item.getId());
                                contentData.setContent(item.getContent());
                                contentData.setCheck(checkedListForItem);
                                return contentData;
                            })
                            .collect(Collectors.toList());

                    int count = haveRepository.findAllGetPost(post.getId());

                    return PostHomeDto.toDto(post, count, contentDataList);
                })
                .collect(Collectors.toList());

        return postHomeDtoList;
    }

    @Transactional
    public void deletePost(PostDeleteDto dto) {
        postRepository.findByIdAndUserId(dto.getPostId(), dto.getUserId());
        postRepository.deleteById(dto.getPostId());
    }

    @Transactional
    public Post getDetailPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        return post;
    }

    @Transactional
    public Long create(PostDto dto) {
        LocalDateTime now = LocalDateTime.now();
        Post post = Post.builder()
                .user(User.builder().id(dto.getUserId()).build())
                .title(dto.getTitle())
                .uploadDate(now)
                .scope(dto.getScope())
                .build();
        postRepository.save(post);

        for (String name : dto.getHashtags()) {
            hashtagRepository.save(Hashtag.toEntity(name, post));
        }

        for (PostRequest.ContentData data : dto.getContent()) {
            Item item = Item.toEntity(data.getContent(), data.getCount(), post);
            itemRepository.save(item);
            subtopicRepository.save(Subtopic.toEntity(data.getCategory(), item));
        }

        return post.getId();
    }

    public List<PostHomeDto> search(String keyword) {
        List<Post> postList = postRepository.findPostsByTitleOrHashTag(keyword);

        List<PostHomeDto> postHomeDtoList = postList.stream()
                .map(post -> {
                    // 이 리스트는 각 item에 대한 userId의 목록을 저장합니다.
                    List<PostHomeDto.ContentData> contentDataList = post.getItemList().stream()
                            .map(item -> {
                                List<Long> checkedListForItem = usercheckRepository.findAllUserCheckList(post.getId(), item.getId());
                                PostHomeDto.ContentData contentData = new PostHomeDto.ContentData();
                                contentData.setCategory(item.getSubtopic() != null ? item.getSubtopic().getName() : null);
                                contentData.setItemId(item.getId());
                                contentData.setContent(item.getContent());
                                contentData.setCheck(checkedListForItem);
                                return contentData;
                            })
                            .collect(Collectors.toList());

                    int count = haveRepository.findAllGetPost(post.getId());

                    return PostHomeDto.toDto(post, count, contentDataList);
                })
                .collect(Collectors.toList());

        return postHomeDtoList;
    }

    @Transactional
    public void changePostScope(Long postId, int scope) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        post.setScope(scope);
        postRepository.save(post);
    }
}
