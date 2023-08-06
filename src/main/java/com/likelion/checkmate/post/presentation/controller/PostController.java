package com.likelion.checkmate.post.presentation.controller;


import com.likelion.checkmate.post.application.dto.PostDto;
import com.likelion.checkmate.post.application.service.PostService;
import com.likelion.checkmate.post.domain.entity.Post;
import com.likelion.checkmate.post.presentation.response.PostDetailsResponse;
import com.likelion.checkmate.post.presentation.request.PostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    @PostMapping("/post/time")
    public ResponseEntity<Long> edit(@RequestBody PostRequest request) {
        Long id = postService.update(PostDto.toDto(request));
        return ResponseEntity.ok(id);
    }
//    @GetMapping("/post/{postId}")
//    public ResponseEntity<PostDetailsResponse> OneDetailPost(@PathVariable Long postId) {
//        Post post = postService.OneDetailPost(postId);
//        return ResponseEntity.ok(PostDetailsResponse.from(post));
//    }

}
