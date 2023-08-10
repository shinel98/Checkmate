package com.likelion.checkmate.post.presentation.response;

import com.likelion.checkmate.post.application.dto.PostHomeDto;
import com.likelion.checkmate.post.domain.entity.Post;
import com.likelion.checkmate.subtopic.domain.entity.Subtopic;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDetailResponse {
    private Long postId;
    private String title;
    private List<String> hashtags;
    private LocalDate date;
    private int together;
    private int get;
    private String writer;
    private int scope;
    private List<PostDetailResponse.ContentData> items;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ContentData {
        private String category;
        private Long itemId;
        private String content;
        private int count;
    }

    public static PostDetailResponse from(Post post, int getCount) {
        return PostDetailResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .hashtags(post.getHashtagList().stream()
                        .map(hashtag -> hashtag.getName())
                        .collect(Collectors.toList()))
                .date(post.getUploadDate() != null ? post.getUploadDate().toLocalDate() : null)
                .together(post.getTogetherList().size())
                .get(getCount)
                .writer(post.getUser().getName())
                .scope(post.getScope())
                .items(post.getItemList().stream()
                        .map(item -> {
                            Subtopic subtopic = item.getSubtopic();
                            return ContentData.builder()
                                    .category(subtopic != null ? subtopic.getName() : null)
                                    .itemId(item.getId())
                                    .content(item.getContent())
                                    .count(item.getCount())
                                    .build();
                        })
                        .collect(Collectors.toList()))
                .build();

    }
}
