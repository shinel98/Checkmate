package com.likelion.checkmate.post.application.dto;

import com.likelion.checkmate.post.domain.entity.Post;
import com.likelion.checkmate.subtopic.domain.entity.Subtopic;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostHomeDto
{
    private Long postId;
    private Long userId;
    private String title;
    private List<String> hashtags;
    private LocalDate date;
    private int together;
    private int get;
    private String writer;
    private int scope;
    private List<ContentData> items;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ContentData {
        private Long itemId;
        private String category;
        private String content;

        private List<Long> check;
    }


    public static PostHomeDto toDto(Post post, int getCount, List<ContentData> contentDataList) {
        PostHomeDto dto = new PostHomeDto();

        dto.postId = post.getId();
        dto.userId = post.getUser().getId();
        dto.title = post.getTitle();
        dto.hashtags = post.getHashtagList().stream()
                .map(hashtag -> hashtag.getName())
                .collect(Collectors.toList());
        if(post.getUploadDate() != null)
            dto.date = post.getUploadDate().toLocalDate();
        dto.together = post.getTogetherList().size();
        dto.get = getCount;
        dto.writer = post.getUser().getName();
        dto.scope = post.getScope();

        dto.items = contentDataList;
        return dto;
    }

    public static PostHomeDto toLocalDto(Post post, int getCount) {
        PostHomeDto dto = new PostHomeDto();

        dto.postId = post.getId();
        dto.userId = post.getUser().getId();
        dto.title = post.getTitle();
        dto.hashtags = post.getHashtagList().stream()
                .map(hashtag -> hashtag.getName())
                .collect(Collectors.toList());
        dto.date = post.getModifiedDate().toLocalDate();
        dto.together = post.getTogetherList().size();
        dto.get = getCount;
        dto.writer = post.getUser().getName();
        dto.scope = post.getScope();

        dto.items = post.getItemList().stream()
                .map(item -> {
                    Subtopic subtopic = item.getSubtopic();
                    ContentData contentData = new ContentData();
                    contentData.setCategory(subtopic != null ? subtopic.getName() : null);
                    contentData.setItemId(item.getId());
                    contentData.setContent(item.getContent());
                    return contentData;
                })
                .collect(Collectors.toList());

        return dto;
    }
}
