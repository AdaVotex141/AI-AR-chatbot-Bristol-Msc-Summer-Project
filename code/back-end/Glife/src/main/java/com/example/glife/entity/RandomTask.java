package com.example.glife.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Data
public class RandomTask {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private String content;

    private String creater;

    private LocalDateTime createTime;

    private int schedule;


}
