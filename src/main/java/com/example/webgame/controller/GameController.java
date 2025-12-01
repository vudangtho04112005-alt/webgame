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
    public String submitScore(@RequestBody Map<String, Object> payload, jakarta.servlet.http.HttpSession session) {
        // 1. Lấy User từ Session (Người đang đăng nhập)
        User currentUser = (User) session.getAttribute("currentUser");

        // Nếu chưa đăng nhập thì không cho lưu điểm
        if (currentUser == null) {
            return "Bạn chưa đăng nhập! Điểm không được lưu.";
        }

        String gameType = (String) payload.get("gameType");
        Integer score = (Integer) payload.get("score");

        // 2. Gọi Service xử lý (Lúc này truyền currentUser thật vào)
        if ("snake".equalsIgnoreCase(gameType)) {
            return snakeService.processResult(currentUser, score);
        } else if ("dino".equalsIgnoreCase(gameType)) {
            return dinoService.processResult(currentUser, score);
        }

        return "Không tìm thấy game!";
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