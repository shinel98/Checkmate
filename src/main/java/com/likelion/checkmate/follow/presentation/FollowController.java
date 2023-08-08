package com.likelion.checkmate.follow.presentation;


import com.likelion.checkmate.follow.application.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FollowController {

    private final FollowService followService;

    @DeleteMapping("/follow")
    public ResponseEntity<Void> deleteById (@RequestParam Long userId, @RequestParam Long followId) {
        followService.deleteFollow(userId, followId);
        return ResponseEntity.ok(null);
    }
}
