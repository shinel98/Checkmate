package com.likelion.checkmate.user.application.dto;
import com.likelion.checkmate.user.presentation.request.ChangeNicknameRequest;
import com.likelion.checkmate.user.presentation.request.RegisterNicknameRequest;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String nickname;
    public static UserDto from(ChangeNicknameRequest request) {
        return UserDto.builder()
                .id(request.getId())
                .nickname(request.getNickname())
                .build();
    }

    public static UserDto toDto (RegisterNicknameRequest request) {
        return UserDto.builder()
                .id(request.getId())
                .nickname(request.getNickname())
                .build();
    }
}
