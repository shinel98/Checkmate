package com.likelion.checkmate.subtopic.domain.entity;

import com.likelion.checkmate.common.BaseEntity;
import com.likelion.checkmate.item.domain.entity.Item;
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
@SQLDelete(sql = "UPDATE subtopic SET deleted = true WHERE id = ?")
public class Subtopic extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "subtopic")
    private Item item;
}
