package com.likelion.checkmate.post.application.service;

import com.likelion.checkmate.hashtag.domain.entity.Hashtag;
import com.likelion.checkmate.hashtag.domain.repository.HashtagRepository;
import com.likelion.checkmate.have.domain.repository.HaveRepository;
import com.likelion.checkmate.item.domain.entity.Item;
import com.likelion.checkmate.item.domain.repository.ItemRepository;
import com.likelion.checkmate.post.application.dto.PostDto;
import com.likelion.checkmate.post.application.dto.PostHomeDto;
import com.likelion.checkmate.post.domain.entity.Post;
import com.likelion.checkmate.post.domain.repository.PostRepository;
import com.likelion.checkmate.post.presentation.request.PostRequest;
import com.likelion.checkmate.subtopic.domain.entity.Subtopic;
import com.likelion.checkmate.subtopic.domain.repository.SubtopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Long update(PostDto dto) {
        Post post = postRepository.findByIdAndUserId(dto.getPostId(), dto.getUserId());

        if(dto.getScope() == 3) {
            LocalDateTime now = LocalDateTime.now();
            post.update(dto.getTitle(), dto.getScope(), now);
        }
        else {
            post.update(dto.getTitle(), dto.getScope());
        }

        List<Item> items = itemRepository.findByPostId(dto.getPostId());

        items.get(0).getId();
        if (items != null && !items.isEmpty())
            subtopicRepository.deleteAllByItemId(items.get(0).getId());

        hashtagRepository.deleteAllByPostId(dto.getPostId());

        itemRepository.deleteAllByPostId(dto.getPostId());

        for(String name : dto.getHashtags()) {
            hashtagRepository.save(Hashtag.toEntity(name,post));
        }

        for(PostRequest.ContentData data : dto.getContent()) {
            Item item = Item.toEntity(data.getContent(), data.getCount(), post);
            itemRepository.save(item);
            subtopicRepository.save(Subtopic.toEntity(data.getCategory(), item));
        }

        return post.getId();
    }

    @Transactional
    public List<PostHomeDto> getPostListByTime() {
        List<Post> postList = postRepository.findAllOrderByRegDateDesc();

        List<PostHomeDto> postHomeDtoList = postList.stream()
                .map(post -> {
                    int count = haveRepository.findAllGetPost(post.getId());
                    return PostHomeDto.toDto(post, count);
                })
                .collect(Collectors.toList());
        System.out.println("postHomeDtoList = " + postHomeDtoList);
        return postHomeDtoList;
    }

    @Transactional
    public List<PostHomeDto> getPostListByHave() {
        List<Post> postList = postRepository.findAllOrderByRegDateDesc();

        List<PostHomeDto> postHomeDtoList = postList.stream()
                .map(post -> {
                    int count = haveRepository.findAllGetPost(post.getId());
                    return PostHomeDto.toDto(post, count);
                })
                .collect(Collectors.toList());
        System.out.println("postHomeDtoList = " + postHomeDtoList);
        return postHomeDtoList;
    }
    @Transactional
    public void deletePost (Long postId, Long userId) {
        Post post = postRepository.findByIdAndUserId(postId, userId);
        postRepository.deleteById(postId);
    }
}
}
