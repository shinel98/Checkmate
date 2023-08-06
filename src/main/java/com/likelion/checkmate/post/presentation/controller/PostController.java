package com.likelion.checkmate.post.presentation.controller;


import com.likelion.checkmate.post.application.dto.PostDto;
import com.likelion.checkmate.post.application.service.PostService;
import com.likelion.checkmate.post.domain.entity.Post;
import com.likelion.checkmate.post.presentation.response.PostDetailsResponse;
import com.likelion.checkmate.post.presentation.request.PostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/post")
    public ResponseEntity<Void> deleteById (@RequestParam Long userId, @RequestParam Long postId) {
        postService.deletePost(userId, postId);
        return ResponseEntity.ok(null);
    }
}
