package com.likelion.checkmate.post.domain.entity;

import com.likelion.checkmate.common.BaseEntity;
import com.likelion.checkmate.hashtag.domain.entity.Hashtag;
import com.likelion.checkmate.item.domain.entity.Item;
import com.likelion.checkmate.report.domain.entity.Report;
import com.likelion.checkmate.together.domain.entity.Together;
import com.likelion.checkmate.user.domain.entity.User;
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
@SQLDelete(sql = "UPDATE post SET deleted = true WHERE id = ?")
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int scope;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Together> togetherList = new ArrayList<>();
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Hashtag> hashtagList = new ArrayList<>();
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Report> reportList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Item> itemList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public void update(String title, int scope) {
        this.title = title;
        this.scope = scope;
    }
}
