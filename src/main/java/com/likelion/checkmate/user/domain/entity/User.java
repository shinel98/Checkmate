package com.likelion.checkmate.user.domain.entity;

import com.likelion.checkmate.common.BaseEntity;
import com.likelion.checkmate.follow.domain.entity.Follow;
import com.likelion.checkmate.post.domain.entity.Post;
import com.likelion.checkmate.together.domain.entity.Together;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE id = ?")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long kakaoId;

    private String name;
    private String nickname;
    private String email;
    private String image;
    @Column(columnDefinition = "TEXT")
    private String refreshToken;

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Follow> followingList = new ArrayList<>();

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Follow> followerList = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Together> togetherList = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

//    public static User toEntity(HashMap<String, Object> userInfo, String refreshToken) {
//        return User.builder()
//                .kakaoId(Long.parseLong(userInfo.get("userKakaoId").toString()))
//                .name(userInfo.get("name").toString())
//                .nickname(userInfo.get("nickname").toString())
//                .email(userInfo.get("email").toString())
//                .refreshToken(refreshToken)
//                .build();
//    }

    public static User toEntity(HashMap<String, Object> userInfo) {
        System.out.println("userInfo = " + userInfo);
        return User.builder()
                .kakaoId(Long.parseLong(userInfo.get("userKakaoId").toString()))
                .name(userInfo.get("nickname").toString())
//                .nickname(userInfo.get("nickname").toString())
                .email(userInfo.get("email").toString())
                .build();
    }

}
