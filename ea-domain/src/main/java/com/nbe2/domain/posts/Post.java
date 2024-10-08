package com.nbe2.domain.posts;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.nbe2.domain.global.BaseTimeEntity;
import com.nbe2.domain.user.User;

@Entity
@Getter
@Table(name = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private City city;

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private final List<Comment> comments = new LinkedList<>();

    private Long commentCount;

    private Long likeCount;

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private final List<PostFile> postFiles = new LinkedList<>();

    private Post(final User user, final String title, final String content, final City city) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.city = city;
        commentCount = 0L;
        likeCount = 0L;
    }

    public static Post create(
            final User user, final String title, final String content, final City city) {
        return new Post(user, title, content, city);
    }

    public String getWriterName() {
        return user.getName();
    }

    // ** 연관관계 편의 메서드 **//
    public void addComment(final Comment comment) {
        comments.add(comment);
    }

    // ** business logic **//
    public Post update(final String title, final String content, final City city) {
        this.title = title;
        this.content = content;
        this.city = city;
        return this;
    }

    public void addFile(final PostFile file) {
        postFiles.add(file);
    }

    public void increaseCommentCount() {
        commentCount++;
    }

    public void decreaseCommentCount() {
        if (commentCount <= 0) return;
        commentCount--;
    }

    public void increaseLikeCount() {
        likeCount++;
    }

    public void decreaseLikeCount() {
        if (likeCount <= 0) return;
        likeCount--;
    }
}
