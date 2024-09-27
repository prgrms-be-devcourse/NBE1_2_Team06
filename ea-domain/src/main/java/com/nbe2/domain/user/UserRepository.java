package com.nbe2.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    boolean existsByEmail(String email);

    //    Page<User> findBySignupStatus(SignupStatus signupStatus, Pageable pageable);
}
