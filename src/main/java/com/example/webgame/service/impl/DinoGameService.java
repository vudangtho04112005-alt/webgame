package com.example.webgame.service.impl;

import org.springframework.stereotype.Service;

@Service("dinoService")
public class DinoGameService extends BaseGameService {

    @Override
    protected String calculateRank(int score) {
        // Khủng long: Trên 1000 điểm mới giỏi
        if (score > 1000)
            return "Khủng Long Bạo Chúa";
        return "Khủng Long Con";
    }
}