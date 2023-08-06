package com.likelion.checkmate.hashtag.domain.entity;

import com.likelion.checkmate.common.BaseEntity;
import com.likelion.checkmate.post.application.dto.PostDto;
import com.likelion.checkmate.post.domain.entity.Post;
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
@SQLDelete(sql = "UPDATE hashtag SET deleted = true WHERE id = ?")
public class Hashtag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public Hashtag update(String name, Post post){
        return Hashtag.builder()
                .name(name)
                .post(post)
                .build();
    }
    public static Hashtag toEntity(String name, Post post) {
        return Hashtag.builder()
                .name(name)
                .post(post)
                .build();
    }
}
