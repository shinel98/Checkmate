package com.likelion.checkmate.post.presentation.response;

import com.likelion.checkmate.hashtag.domain.entity.Hashtag;
import com.likelion.checkmate.post.domain.entity.Post;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDetailsResponse {
    private Long postId;
    private String title;
    private List<String> hashtags;
    private LocalDateTime date;
    private int together;
    private int get;
    private String writer;
    private int scope;
    private List<ItemData> items;
    private LocalDateTime created_time;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ItemData {
        private String category;
        private Long itemId;
        private String name;
        private int count;

    }
}
