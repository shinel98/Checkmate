package com.likelion.checkmate.item.domain.entity;

import com.likelion.checkmate.common.BaseEntity;
import com.likelion.checkmate.post.application.dto.PostDto;
import com.likelion.checkmate.post.domain.entity.Post;
import com.likelion.checkmate.subtopic.domain.entity.Subtopic;
import com.likelion.checkmate.usercheck.domain.entity.Usercheck;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @OneToMany(mappedBy = "item")
    private List<Usercheck> usercheckList = new ArrayList<>();


    @OneToOne(mappedBy = "item")
    private Subtopic subtopic;


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
    public Item cloneWithNewPost(Post newPost) {
        Item clonedItem = Item.builder()
                .content(this.content)
                .count(this.count)
                .post(newPost)
                .build();

        if (this.subtopic != null) {
            clonedItem.setSubtopic(this.subtopic.cloneWithNewItem(clonedItem));

        }

        return clonedItem;
    }

}
