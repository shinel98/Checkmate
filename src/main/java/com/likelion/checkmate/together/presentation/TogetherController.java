package com.likelion.checkmate.together.presentation;

import com.likelion.checkmate.follow.presentation.request.FollowRequest;

import com.likelion.checkmate.together.application.dto.TogetherDto;
import com.likelion.checkmate.together.application.service.TogetherService;
import com.likelion.checkmate.together.presentation.request.TogetherRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TogetherController {
    private final TogetherService togetherService;
    @DeleteMapping("/together")
    public ResponseEntity<Void> deletebyId(@RequestBody TogetherRequest request) {
        togetherService.deleteTogether(TogetherDto.toDto(request));
        return ResponseEntity.ok(null);
    }
    @PostMapping("/together")
    public ResponseEntity<Void> create(@RequestBody TogetherRequest request) {
        togetherService.createTogether(TogetherDto.toDto(request));
        return ResponseEntity.ok(null);
    }

}
