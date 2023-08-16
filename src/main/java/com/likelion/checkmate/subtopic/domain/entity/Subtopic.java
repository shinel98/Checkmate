package com.likelion.checkmate.subtopic.domain.entity;

import com.likelion.checkmate.common.BaseEntity;
import com.likelion.checkmate.hashtag.domain.entity.Hashtag;
import com.likelion.checkmate.item.domain.entity.Item;
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
@SQLDelete(sql = "UPDATE subtopic SET deleted = true WHERE id = ?")
public class Subtopic extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private Item item;

    public Subtopic update(String text, Item item) {
        return Subtopic.builder()
                .name(text)
                .item(item)
                .build();
    }

    public static Subtopic toEntity(String name, Item item) {
        return Subtopic.builder()
                .name(name)
                .item(item)
                .build();
    }

    public Subtopic cloneWithNewItem(Item newItem) {
        return Subtopic.builder()
                .name(this.name)
                .item(newItem)
                .build();
    }
}
