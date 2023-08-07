package com.likelion.checkmate.together.domain.presentation;

import com.likelion.checkmate.together.domain.application.TogetherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TogetherController {
    private final TogetherService togetherService;
    @DeleteMapping("/together")
    public ResponseEntity<Void> deletebyId(@RequestParam Long userId, @RequestParam Long postId) {
        togetherService.deleteTogether(userId, postId);
        return ResponseEntity.ok(null);
    }

}
