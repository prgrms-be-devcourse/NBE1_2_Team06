package com.nbe2.domain.posts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nbe2.domain.posts.entity.Comment;
import com.nbe2.domain.posts.entity.Post;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
