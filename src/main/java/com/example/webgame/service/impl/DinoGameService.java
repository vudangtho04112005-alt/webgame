package com.example.webgame.service.impl;

import org.springframework.stereotype.Service;

@Service("dinoService") // Tên này phải khớp với cái @Qualifier bên Controller
public class DinoGameService extends BaseGameService { // <--- Phải có extends này

    @Override
    protected String calculateRank(int score) {
        if (score > 1000)
            return "Khủng Long Bạo Chúa";
        return "Khủng Long Con";
    }
}