package com.likelion.checkmate.follow.domain.entity;

import com.likelion.checkmate.common.BaseEntity;
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
@ToString
@SQLDelete(sql = "UPDATE follow SET deleted = true WHERE id = ?")
public class Follow extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY)
    private User following;
//    private Long following_id;

}
