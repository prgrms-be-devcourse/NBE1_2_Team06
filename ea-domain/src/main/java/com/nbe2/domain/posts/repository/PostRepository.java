package com.nbe2.domain.posts.repository;

import java.util.Optional;

import jakarta.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nbe2.domain.posts.entity.City;
import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.user.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Post p where p.id = :postId")
    Optional<Post> findByIdWithPessimisticWriteLock(Long postId);

    Page<Post> findByCity(City city, Pageable pageable);

    Page<Post> findByUser(User user, Pageable pageable);
}
