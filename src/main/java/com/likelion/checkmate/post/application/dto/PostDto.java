package com.likelion.checkmate.post.application.dto;

import com.likelion.checkmate.post.presentation.request.PostRequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private Long userId;
    private Long postId;
    private String title;
    private int scope;
    private List<String> hashtags;
    private List<PostRequest.ContentData> content;

    public static PostDto toDto(PostRequest request) {
        return PostDto.builder()
                .userId(request.getUserId())
                .postId(request.getPostId())
                .title(request.getTitle())
                .scope(request.getScope())
                .hashtags(request.getHashtag())
                .content(request.getContent())
                .build();
    }
}
