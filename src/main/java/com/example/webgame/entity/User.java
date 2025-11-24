package com.example.webgame.entity;

import jakarta.persistence.*;

@Entity // Đánh dấu đây là một bảng trong CSDL
@Table(name = "users") // Tên bảng là "users"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng ID (1, 2, 3...)
    private Long id;

    private String username;
    private String email;
    private String password;

    // --- Constructor ---
    public User() {
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // --- Getter & Setter (Bắt buộc để Spring Boot đọc dữ liệu) ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}