package com.likelion.checkmate.report.domain.entity;

import com.likelion.checkmate.common.BaseEntity;
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
@SQLDelete(sql = "UPDATE report SET deleted = true WHERE id = ?")
public class Report extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public static Report toEntity(String content, Post post) {
        return Report.builder()
                .content(content)
                .post(post)
                .build();
    }
}
