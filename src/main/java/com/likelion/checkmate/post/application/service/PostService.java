package com.likelion.checkmate.post.application.service;

import com.likelion.checkmate.hashtag.domain.entity.Hashtag;
import com.likelion.checkmate.hashtag.domain.repository.HashtagRepository;
import com.likelion.checkmate.item.domain.entity.Item;
import com.likelion.checkmate.item.domain.repository.ItemRepository;
import com.likelion.checkmate.post.application.dto.PostDto;
import com.likelion.checkmate.post.domain.entity.Post;
import com.likelion.checkmate.post.domain.repository.PostRepository;
import com.likelion.checkmate.post.presentation.request.PostRequest;
import com.likelion.checkmate.subtopic.domain.entity.Subtopic;
import com.likelion.checkmate.subtopic.domain.repository.SubtopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final HashtagRepository hashtagRepository;
    private final ItemRepository itemRepository;
    private final SubtopicRepository subtopicRepository;


    @Transactional
    public Long update(PostDto dto) {
        Post post = postRepository.findByIdAndUserId(dto.getPostId(), dto.getUserId());

        post.update(dto.getTitle(), dto.getScope());

//        List<Hashtag> hashtag = hashtagRepository.findAllByPostId(dto.getPostId());
        List<Item> items = itemRepository.findByPostId(dto.getPostId());

        items.get(0).getId();
        if (items != null && !items.isEmpty())
            subtopicRepository.deleteAllByItemId(items.get(0).getId());


        hashtagRepository.deleteByPostId(dto.getPostId());
        itemRepository.deleteByPostId(dto.getPostId());

        for(String name : dto.getHashtags()) {
            hashtagRepository.save(Hashtag.toEntity(name,post));
        }

        for(PostRequest.ContentData data : dto.getContent()) {
            Item item = Item.toEntity(data.getContent(), data.getCount(), post);
            itemRepository.save(item);
            subtopicRepository.save(Subtopic.toEntity(data.getText(), item));
        }

        return post.getId();
    }

    @Transactional
    public Post OneDetailPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("no such post"));
        return post;
    }

}

