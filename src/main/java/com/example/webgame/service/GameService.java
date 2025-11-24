package com.example.webgame.service;

import com.example.webgame.entity.User;

public interface GameService {
    // Mọi game đều phải tuân thủ hàm này: Xử lý kết quả khi chơi xong
    String processResult(User user, int score);
}