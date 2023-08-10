package com.likelion.checkmate.post.presentation.controller;


import com.likelion.checkmate.follow.application.dto.FollowDeleteDto;
import com.likelion.checkmate.follow.presentation.request.FollowRequest;
import com.likelion.checkmate.post.application.dto.PostDeleteDto;
import com.likelion.checkmate.post.application.dto.PostDto;
import com.likelion.checkmate.post.application.dto.PostHomeDto;
import com.likelion.checkmate.post.application.service.PostService;
import com.likelion.checkmate.post.presentation.request.PostDeleteRequest;
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

    @PatchMapping("/post")
    public ResponseEntity<Long> edit(@RequestBody PostRequest request) {
        Long id = postService.update(PostDto.toDto(request));
        return ResponseEntity.ok(id);
    }


    @GetMapping("/post/time")
    public ResponseEntity<List<PostHomeDto>> getPostListByTime() {
        return ResponseEntity.ok(postService.getPostListByTime());
    }


    @GetMapping("/post/get")
    public ResponseEntity<List<PostHomeDto>> getPostListByHave() {
        return ResponseEntity.ok(postService.getPostListByHave());
    }

//    @DeleteMapping("/post")
//    public ResponseEntity<Void> deleteById (@RequestParam Long userId, @RequestParam Long postId) {
//        postService.deletePost(userId, postId);
//        return ResponseEntity.ok(null);
//    }

    @DeleteMapping("/post")
    public ResponseEntity<Void> deleteById (@RequestBody PostDeleteRequest request) {
        postService.deletePost(PostDeleteDto.toDto(request));
        return ResponseEntity.ok(null);
    }

    @GetMapping("/post/together")
    public ResponseEntity<List<PostHomeDto>> getPostListByTogether() {
        return ResponseEntity.ok(postService.getPostListByTogether());
    }
}
