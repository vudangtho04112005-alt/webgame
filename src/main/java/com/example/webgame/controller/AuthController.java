package com.example.webgame.controller;

import com.example.webgame.entity.User;
import com.example.webgame.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // 1. ĐĂNG KÝ
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        // Kiểm tra xem tên đã tồn tại chưa
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Tên đăng nhập đã tồn tại!");
        }
        // Lưu user mới (Lưu ý: Mật khẩu đang để lộ thiên, thực tế cần mã hóa)
        userRepository.save(user);
        return ResponseEntity.ok("Đăng ký thành công!");
    }

    // 2. ĐĂNG NHẬP
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> data, HttpSession session) {
        String username = data.get("username");
        String password = data.get("password");

        User user = userRepository.findByUsername(username);

        // Kiểm tra tài khoản và mật khẩu
        if (user != null && user.getPassword().equals(password)) {
            // Đăng nhập thành công -> Lưu thông tin User vào Session (Phiên làm việc)
            session.setAttribute("currentUser", user);
            return ResponseEntity.ok("Đăng nhập thành công!");
        }
        return ResponseEntity.status(401).body("Sai tên đăng nhập hoặc mật khẩu!");
    }

    // 3. LẤY THÔNG TIN USER HIỆN TẠI
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return ResponseEntity.status(401).body("Chưa đăng nhập");
        }
        return ResponseEntity.ok(currentUser);
    }

    // 4. ĐĂNG XUẤT
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.removeAttribute("currentUser");
        return ResponseEntity.ok("Đã đăng xuất");
    }
}