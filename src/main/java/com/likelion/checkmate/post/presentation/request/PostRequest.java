package com.likelion.checkmate.post.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostRequest {
    private Long userId;
    private Long postId;
    private String title;
    private int scope;
    private List<String> hashtag;
    private List<ContentData> content;


    @Getter
    @Setter
    @NoArgsConstructor
    public static class ContentData {
        private String text;
        private String content;
        private int count;
    }

}
