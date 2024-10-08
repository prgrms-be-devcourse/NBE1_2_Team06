package com.nbe2.domain.posts;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.nbe2.domain.global.BaseTimeEntity;
import com.nbe2.domain.user.User;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    private Comment(final Post post, final User user, final String content) {
        setPost(post);
        this.user = user;
        this.content = content;
    }

    public static Comment create(final Post post, final User user, final String content) {
        return new Comment(post, user, content);
    }

    public Long getPostId() {
        return post.getId();
    }

    public Long getWriterId() {
        return user.getId();
    }

    public String getWriterName() {
        return user.getName();
    }

    // ** 연관관계 편의 메서드**//
    private void setPost(final Post post) {
        this.post = post;
        post.addComment(this);
    }

    // ** business logic **//
    public Long update(final String content) {
        this.content = content;
        return id;
    }
}
