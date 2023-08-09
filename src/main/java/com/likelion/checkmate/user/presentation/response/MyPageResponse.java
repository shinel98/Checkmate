package com.likelion.checkmate.user.presentation.response;

import com.likelion.checkmate.post.application.dto.PostHomeDto;
import com.likelion.checkmate.user.application.dto.MyPageDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPageResponse {
    private String image;
    private String nickname;
    private int follower;
    private int following;

    private List<PostHomeDto> myList;
    private List<PostHomeDto> togetherList;

    public static MyPageResponse toResponse(MyPageDto dto) {
        return MyPageResponse.builder()
                .image(dto.getImage())
                .nickname(dto.getNickname())
                .follower(dto.getFollower())
                .following(dto.getFollowing())
                .myList(dto.getMyList())
                .togetherList(dto.getTogetherList())
                .build();
    }


}
