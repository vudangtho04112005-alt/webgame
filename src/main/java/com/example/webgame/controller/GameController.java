package com.example.webgame.controller;

import com.example.webgame.entity.User;
import com.example.webgame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController // Báo hiệu đây là nơi nhận yêu cầu từ Web
@RequestMapping("/api/game") // Đường dẫn gốc: localhost:8080/api/game
public class GameController {

    // 1. Tiêm Service Rắn vào
    @Autowired
    @Qualifier("snakeService") // Tìm cái tên "snakeService" đã đặt lúc nãy
    private GameService snakeService;

    // 2. Tiêm Service Khủng long vào
    @Autowired
    @Qualifier("dinoService")
    private GameService dinoService;

    // API nhận điểm: POST localhost:8080/api/game/submit
    // Dữ liệu gửi lên (JSON): { "gameType": "snake", "score": 150, "username":
    // "Nam" }
    @PostMapping("/submit")
    public String submitScore(@RequestBody Map<String, Object> payload) {
        String gameType = (String) payload.get("gameType");
        Integer score = (Integer) payload.get("score");
        String username = (String) payload.get("username");

        // Tạo User giả lập từ dữ liệu gửi lên
        User user = new User();
        user.setUsername(username);

        // Logic chọn game (Factory Pattern đơn giản)
        if ("snake".equalsIgnoreCase(gameType)) {
            return snakeService.processResult(user, score);
        } else if ("dino".equalsIgnoreCase(gameType)) {
            return dinoService.processResult(user, score);
        }

        return "Lỗi: Không tìm thấy game này!";
    }

    // --- Thêm đoạn này ---
    @Autowired
    private com.example.webgame.repository.GameSessionRepository gameSessionRepository;

    // API lấy danh sách top 10 điểm cao nhất
    @GetMapping("/leaderboard")
    public java.util.List<com.example.webgame.entity.GameSession> getLeaderboard() {
        return gameSessionRepository.findTop10ByOrderByScoreDesc();
    }
}