package com.nbe2.domain.posts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nbe2.domain.user.User;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findByUser(User user);
}
