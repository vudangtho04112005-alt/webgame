package com.example.webgame.repository;

import com.example.webgame.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Hàm tìm user bằng tên đăng nhập
    User findByUsername(String username);
}