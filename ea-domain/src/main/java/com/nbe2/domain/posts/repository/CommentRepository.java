package com.nbe2.domain.posts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nbe2.domain.posts.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {}
