package com.example.myrh.Repository;

import com.example.myrh.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUsername(String name);

    Optional<UserEntity> findByEmail(String email);
    Boolean existsByUsername(String name);
}
