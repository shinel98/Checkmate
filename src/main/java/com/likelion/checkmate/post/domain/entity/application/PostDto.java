package com.likelion.checkmate.post.domain.entity.application;

import com.likelion.checkmate.hashtag.domain.entity.Hashtag;
import com.likelion.checkmate.item.domain.entity.Item;
import com.likelion.checkmate.report.domain.entity.Report;
import com.likelion.checkmate.together.domain.entity.Together;
import com.likelion.checkmate.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PostDto {
    // title hashtag,, date,together, get, writer, scope, items

    private Long id;
    private String title;
    private int scope;
    private List<Together> togetherList = new ArrayList<>();
    private List<Hashtag> hashtagList = new ArrayList<>();
    private List<Report> reportList = new ArrayList<>();
    private List<Item> itemList = new ArrayList<>();
    private User user;
    private LocalDateTime created_time;


}
