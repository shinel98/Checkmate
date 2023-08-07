package com.likelion.checkmate.together.domain.entity;

import com.likelion.checkmate.common.BaseEntity;
import com.likelion.checkmate.post.domain.entity.Post;
import com.likelion.checkmate.user.domain.entity.User;
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
@SQLDelete(sql = "UPDATE together SET deleted = true WHERE id = ?")
public class Together extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


}
