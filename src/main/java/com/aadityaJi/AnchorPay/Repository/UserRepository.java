package com.aadityaJi.AnchorPay.Repository;

import com.aadityaJi.AnchorPay.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);//babu kya yha pe booleade dedu mehtod return type ?
    Optional<UserEntity> findByUsername(String username);
}
