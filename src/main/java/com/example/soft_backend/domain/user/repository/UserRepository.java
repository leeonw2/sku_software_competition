package com.example.soft_backend.domain.user.repository;

import com.example.soft_backend.domain.user.entity.UserEntity;
import com.example.soft_backend.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByStudentId(String studentId);

    Optional<UserEntity> findByStudentId(String studentId);
}