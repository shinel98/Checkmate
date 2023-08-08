package com.likelion.checkmate.post.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.likelion.checkmate.common.BaseEntity;
import com.likelion.checkmate.hashtag.domain.entity.Hashtag;
import com.likelion.checkmate.have.domain.entity.Have;
import com.likelion.checkmate.item.domain.entity.Item;
import com.likelion.checkmate.report.domain.entity.Report;
import com.likelion.checkmate.together.domain.entity.Together;
import com.likelion.checkmate.user.domain.entity.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @OneToMany(mappedBy = "post")
    private List<Have> haveList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime uploadDate;

    public void update(String title, int scope) {
        this.title = title;
        this.scope = scope;
    }
    public void update(String title, int scope, LocalDateTime date) {
        this.title = title;
        this.scope = scope;
        this.uploadDate = date;
    }
}
