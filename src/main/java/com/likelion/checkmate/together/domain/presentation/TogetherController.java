package com.likelion.checkmate.together.domain.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class TogetherController {

    @DeleteMapping("/room")
    public ResponseEntity<Void> deleteById(@RequestParam Long roomId) {
        roomService.deleteRoom(roomId);
        return ResponseEntity.ok(null);
    }
}
