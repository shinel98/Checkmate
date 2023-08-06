package com.likelion.checkmate.post.domain.entity.presentation;

import com.likelion.checkmate.post.domain.entity.application.PostDto;
import com.likelion.checkmate.post.domain.entity.application.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")

public class PostController {

    private final  PostService postService;

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<PostDetailsResponse>> getAllPosts () {
        List<PostDto> allPostDtoList = PostService.getAllPosts();
        List<PostDetailsResponse> response = allPostDtoList.stream().map(PostDetailsResponse::from).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

}


