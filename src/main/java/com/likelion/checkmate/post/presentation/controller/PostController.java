package com.likelion.checkmate.post.presentation.controller;


import com.likelion.checkmate.post.application.dto.PostDto;
import com.likelion.checkmate.post.application.dto.PostHomeDto;
import com.likelion.checkmate.post.application.service.PostService;
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


//    @GetMapping("/post/get")
//    public ResponseEntity<List<PostHomeDto>> getPostListByTime() {
//        return ResponseEntity.ok(postService.getPostListByHave());
//    }
}
