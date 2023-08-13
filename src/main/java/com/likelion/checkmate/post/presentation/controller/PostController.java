package com.likelion.checkmate.post.presentation.controller;
import com.likelion.checkmate.have.domain.repository.HaveRepository;
import com.likelion.checkmate.post.application.dto.PostDeleteDto;
import com.likelion.checkmate.post.application.dto.PostDto;
import com.likelion.checkmate.post.application.dto.PostHomeDto;
import com.likelion.checkmate.post.application.service.PostService;
import com.likelion.checkmate.post.domain.entity.Post;
import com.likelion.checkmate.post.presentation.request.PostDeleteRequest;
import com.likelion.checkmate.post.presentation.request.PostRequest;
import com.likelion.checkmate.post.presentation.response.PostDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;
    private final HaveRepository haveRepository;


    @PostMapping("/post")
    public ResponseEntity<Long> create(@RequestBody PostRequest request) {
        PostDto postDto = PostDto.toDto(request);
        if (request.getHashtag() != null && request.getHashtag().size() > 5) { //해쉬태그 5개로 제한
            return ResponseEntity.badRequest().build();
        }
        Long postId = postService.create(postDto);
        return ResponseEntity.ok(postId);
    }

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

    @DeleteMapping("/post")
    public ResponseEntity<Void> deleteById (@RequestBody PostDeleteRequest request) {
        postService.deletePost(PostDeleteDto.toDto(request));
        return ResponseEntity.ok(null);
    }

    @GetMapping("/post/together")
    public ResponseEntity<List<PostHomeDto>> getPostListByTogether() {
        return ResponseEntity.ok(postService.getPostListByTogether());
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDetailResponse> getDetailPost(@PathVariable Long postId) {
        Post post = postService.getDetailPost(postId);
        int getCount = haveRepository.findAllGetPost(postId);
        PostDetailResponse postDetailResponse = PostDetailResponse.from(post, getCount);
        return ResponseEntity.ok(PostDetailResponse.from(post,getCount));
    }

    @GetMapping("/post/search")
    public ResponseEntity<List<PostHomeDto>> searchPost(@RequestParam String keyword) {
        if(keyword == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(postService.search(keyword));

    }


}
