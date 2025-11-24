package com.example.webgame.service.impl;

import org.springframework.stereotype.Service;

@Service("snakeService") // Đặt tên để Controller gọi đúng
public class SnakeGameService extends BaseGameService {

    @Override
    protected String calculateRank(int score) {
        // Rắn: Trên 100 điểm là giỏi
        if (score > 1000)
            return "Vua Rắn (Snake Master)";
        return "Rắn Tập Sự";
    }
}