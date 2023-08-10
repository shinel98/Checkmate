package com.likelion.checkmate.together.presentation;

import com.likelion.checkmate.follow.presentation.request.FollowRequest;
import com.likelion.checkmate.together.application.dto.TogetherDeleteDto;
import com.likelion.checkmate.together.application.service.TogetherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TogetherController {
    private final TogetherService togetherService;
    @DeleteMapping("/together")
    public ResponseEntity<Void> deletebyId(@RequestBody TogetherDeleteRequest request) {
        togetherService.deleteTogether(TogetherDeleteDto.toDto(request));
        return ResponseEntity.ok(null);
    }

}
