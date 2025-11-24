package com.example.webgame.repository;

import com.example.webgame.entity.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {
    // Hàm tìm Top 10 điểm cao nhất, sắp xếp giảm dần
    List<GameSession> findTop10ByOrderByScoreDesc();
}