package com.likelion.checkmate.post.domain.entity.application;

import com.likelion.checkmate.post.domain.entity.Post;
import com.likelion.checkmate.post.domain.entity.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    @Transactional
    public List<PostDto> getAllPosts() {
        List<Post> allPostList= PostRepository.findAll();
        List<PostDto> allPostDtoList = new ArrayList<>();
        return allPostDtoList;
    }

}
