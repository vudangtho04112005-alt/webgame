package com.example.webgame.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "game_sessions")
public class GameSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String gameType; // "snake" hoặc "dino"
    private int score;
    private LocalDateTime playedAt; // Thời gian chơi

    // Constructor
    public GameSession() {
        this.playedAt = LocalDateTime.now(); // Tự lấy giờ hiện tại
    }

    public GameSession(String username, String gameType, int score) {
        this.username = username;
        this.gameType = gameType;
        this.score = score;
        this.playedAt = LocalDateTime.now();
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDateTime getPlayedAt() {
        return playedAt;
    }
}