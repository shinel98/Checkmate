package com.likelion.checkmate.item.domain.entity;

import com.likelion.checkmate.common.BaseEntity;
import com.likelion.checkmate.post.application.dto.PostDto;
import com.likelion.checkmate.post.domain.entity.Post;
import com.likelion.checkmate.subtopic.domain.entity.Subtopic;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE item SET deleted = true WHERE id = ?")
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private int count;
    private String category;
    private String name; // 확인

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;



    public Item update(String content, int count, Post post) {
        return Item.builder()
                .content(content)
                .count(count)
                .post(post)
                .build();
    }
    public static Item toEntity(String content, int count, Post post) {
        return Item.builder()
                .content(content)
                .count(count)
                .post(post)
                .build();
    }

}
