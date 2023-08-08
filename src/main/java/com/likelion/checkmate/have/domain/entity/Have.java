package com.likelion.checkmate.have.domain.entity;

import com.likelion.checkmate.common.BaseEntity;
import com.likelion.checkmate.hashtag.domain.entity.Hashtag;
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
public class Have extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
    private Long destId;

}
