package com.nbe2.domain.posts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nbe2.domain.posts.entity.Post;

@Repository
public interface JpaPostsRepository extends JpaRepository<Post, Long> {}
