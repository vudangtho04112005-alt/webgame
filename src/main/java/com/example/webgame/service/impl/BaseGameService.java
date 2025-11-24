package com.example.webgame.service.impl;

import com.example.webgame.entity.GameSession;
import com.example.webgame.entity.User;
import com.example.webgame.repository.GameSessionRepository;
import com.example.webgame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseGameService implements GameService {

    @Autowired
    private GameSessionRepository gameSessionRepository; // Tiêm Repository vào

    @Override
    public String processResult(User user, int score) {
        // 1. Logic chung: Lưu xuống database THẬT
        saveToDatabase(user, score);

        // 2. Logic riêng
        String rank = calculateRank(score);
        return "Điểm: " + score + " - Xếp hạng: " + rank;
    }

    protected void saveToDatabase(User user, int score) {
        // Vì chúng ta dùng đa hình, cần biết đây là game gì để lưu
        // Cách đơn giản: Lấy tên class service đang chạy (SnakeGameService...)
        String gameType = this.getClass().getSimpleName().replace("GameService", "");

        GameSession session = new GameSession(user.getUsername(), gameType, score);
        gameSessionRepository.save(session); // Lệnh này sẽ INSERT vào MySQL

        System.out.println(">> Đã lưu điểm vào DB: " + score);
    }

    protected abstract String calculateRank(int score);
}