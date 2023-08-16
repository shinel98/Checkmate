package com.likelion.checkmate.usercheck.domain.entity;

import com.likelion.checkmate.common.BaseEntity;
import com.likelion.checkmate.item.domain.entity.Item;
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
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE id = ?")
public class Usercheck extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    private Long postId;
}
