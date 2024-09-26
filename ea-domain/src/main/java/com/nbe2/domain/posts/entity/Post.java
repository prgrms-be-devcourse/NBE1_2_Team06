package com.nbe2.domain.posts.entity;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import com.nbe2.domain.global.BaseTimeEntity;
import com.nbe2.domain.user.User;

@Entity
@Table(name = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String content;

    private Long commentCount;

    private Long likeCount;

    @Enumerated(EnumType.STRING)
    private City city;

    //    @OneToMany(mappedBy = "", cascade = CascadeType.ALL, orphanRemoval = true)
    //    private List<Comments> comments = new LinkedList<>();

    //    @OneToMany(mappedBy = "", cascade = CascadeType.ALL, orphanRemoval = true)
    //    private List<Likes> likes = new LinkedList<>();

    //    @OneToMany(mappedBy = "", cascade = CascadeType.ALL, orphanRemoval = true)
    //    private List<PostFiles> postFiles = new LinkedList<>();
}
