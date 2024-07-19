package com.example.glife.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RandomTask {

    private Long id;

    private String title;

    private String content;

    private String creater;

    private String sender;

    private LocalDateTime createTime;

    private LocalDateTime lastSendTime;

}
