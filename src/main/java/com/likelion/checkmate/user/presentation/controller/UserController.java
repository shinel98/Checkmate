package com.likelion.checkmate.user.presentation.controller;


import com.likelion.checkmate.user.application.UserService.UserService;
import com.likelion.checkmate.user.application.dto.UserDto;
import com.likelion.checkmate.user.presentation.request.ChangeNicknameRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    @PatchMapping("/user/my")
    public ResponseEntity<Void> changeNickname(@RequestBody ChangeNicknameRequest request) {
        UserDto userDto = UserDto.from(request);
        userService.changeUserInfo(userDto.getId(), userDto.getNickname());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user")
    public ResponseEntity<Void> delete(@RequestParam Long userId) {
        userService.delete(userId);
        return ResponseEntity.ok(null);
    }
}
