package com.likelion.checkmate.have.application.service;

import com.likelion.checkmate.hashtag.domain.entity.Hashtag;
import com.likelion.checkmate.hashtag.domain.repository.HashtagRepository;
import com.likelion.checkmate.have.application.dto.HaveDto;
import com.likelion.checkmate.item.domain.entity.Item;
import com.likelion.checkmate.item.domain.repository.ItemRepository;
import com.likelion.checkmate.post.domain.entity.Post;
import com.likelion.checkmate.post.domain.repository.PostRepository;
import com.likelion.checkmate.post.presentation.request.PostRequest;
import com.likelion.checkmate.subtopic.domain.entity.Subtopic;
import com.likelion.checkmate.subtopic.domain.repository.SubtopicRepository;
import com.likelion.checkmate.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HaveService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;

    private final ItemRepository itemRepository;
    private final SubtopicRepository subtopicRepository;

    @Transactional
    public Long create(HaveDto dto) {
        System.out.println("dto.getPostId() = " + dto.getPostId());
        System.out.println("dto.getUserId() = " + dto.getUserId());

        Post oldPost = postRepository.findById(dto.getPostId()).orElseThrow(() -> new IllegalArgumentException("no such post"));
        Post newPost = new Post();
        newPost.setTitle(oldPost.getTitle());
        newPost.setScope(1);
        for (Hashtag oldHashtag : oldPost.getHashtagList()) {
            Hashtag newHashtag = new Hashtag();
            newHashtag.setName(oldHashtag.getName());
            newHashtag.setPost(newPost);
            hashtagRepository.save(newHashtag);
        }

        for (Item oldItem : oldPost.getItemList()) {
            Item newItem = new Item();
            newItem.setContent(oldItem.getContent());
            newItem.setCount(oldItem.getCount());
            newItem.setPost(newPost);
            itemRepository.save(newItem);

            if (oldItem.getSubtopic() != null) {
                Subtopic oldSubtopic = oldItem.getSubtopic();
                Subtopic newSubtopic = new Subtopic();
                newSubtopic.setName(oldSubtopic.getName());
                newSubtopic.setItem(newItem);
                subtopicRepository.save(newSubtopic);
            }

        }
        newPost.setUser(userRepository.findById(dto.getUserId()).orElse(null));
        return newPost.getId();
    }
}
