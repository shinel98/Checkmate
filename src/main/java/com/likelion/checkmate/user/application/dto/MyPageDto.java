package com.likelion.checkmate.user.application.dto;

import com.likelion.checkmate.follow.application.dto.FollowDto;
import com.likelion.checkmate.post.application.dto.PostHomeDto;
import com.likelion.checkmate.user.domain.entity.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPageDto {
    private String image;
    private String nickname;
    private int follower;
    private int following;

    private List<PostHomeDto> myList;
    private List<PostHomeDto> togetherList;

    public static MyPageDto toDto(User user, int followerCnt, int followingCnt, List<PostHomeDto> myList, List<PostHomeDto> togetherList) {
        return MyPageDto.builder()
                .image(user.getImage())
                .nickname(user.getNickname())
                .follower(followerCnt)
                .following(followingCnt)
                .myList(myList)
                .togetherList(togetherList)
                .build();
    }

}
