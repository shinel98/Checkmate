package com.likelion.checkmate.user.presentation.controller;


import com.likelion.checkmate.user.application.service.UserService;
import com.likelion.checkmate.user.application.dto.UserDto;
import com.likelion.checkmate.user.presentation.request.ChangeNicknameRequest;
import com.likelion.checkmate.user.presentation.request.RegisterNicknameRequest;
import com.likelion.checkmate.user.presentation.response.MyPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @PostMapping("/user/my")
    public ResponseEntity<Void> registerNickname(@RequestBody RegisterNicknameRequest request) {
        UserDto userDto = UserDto.toDto(request);
        userService.registerUserNickname(userDto.getId(), userDto.getNickname());
        return ResponseEntity.ok().build();
    }
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

    @GetMapping("/user/my")
    public ResponseEntity<MyPageResponse> readMyPage(@RequestParam Long userId) {
        MyPageResponse response = MyPageResponse.toResponse(userService.readMyPage(userId));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file , @RequestParam Long userId) {
        String imageUrl = userService.saveImage(file, userId);
        return ResponseEntity.ok(imageUrl);
    }


}
