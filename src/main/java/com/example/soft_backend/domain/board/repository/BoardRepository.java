package com.example.soft_backend.domain.board.repository;

import com.example.soft_backend.domain.board.entity.BoardEntity;
import com.example.soft_backend.domain.board.entity.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    Optional<BoardEntity> findByType(BoardType type);
}