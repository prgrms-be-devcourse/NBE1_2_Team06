package com.nbe2.domain.posts.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nbe2.domain.posts.entity.City;
import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.user.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByCity(City city, Pageable pageable);

    Page<Post> findByUser(User user, Pageable pageable);
}
