package com.nbe2.domain.posts;

import java.util.Optional;

import jakarta.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nbe2.domain.user.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Post p where p.id = :postId")
    Optional<Post> findByIdWithPessimisticWriteLock(Long postId);

    @Query(
            """
    select distinct post
     from Post post
     join fetch post.user
     left join fetch post.postFiles postFiles
     where post.id = :postId
    """)
    Optional<Post> findDetailById(Long postId);

    @EntityGraph(attributePaths = {"user"})
    @Query("select p from Post p")
    Page<Post> findByCity(City city, Pageable pageable);

    @EntityGraph(attributePaths = {"user"})
    @Query("select p from Post p")
    Page<Post> findByUser(User user, Pageable pageable);
}
