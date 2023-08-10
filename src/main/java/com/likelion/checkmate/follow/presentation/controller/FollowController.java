package com.likelion.checkmate.follow.presentation.controller;


import com.likelion.checkmate.follow.application.FollowService;
import com.likelion.checkmate.follow.application.dto.FollowDeleteDto;
import com.likelion.checkmate.follow.presentation.request.FollowRequest;
import com.likelion.checkmate.follow.presentation.response.FollowResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FollowController {

    private final FollowService followService;

    @DeleteMapping("/follow")
    public ResponseEntity<Void> deleteById (@RequestBody FollowRequest request) {
        followService.deleteFollow(FollowDeleteDto.toDto(request));
        return ResponseEntity.ok(null);
    }

    @GetMapping("/follow")
    public ResponseEntity<FollowResponse> readAll (@RequestParam Long userId) {
        return ResponseEntity.ok(followService.readAll(userId));
    }
}
