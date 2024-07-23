package com.example.glife.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Data
@TableName("random_task")
public class RandomTask {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private String description;

    private String creater;

    private LocalDateTime createTime;

    private int schedule;


}
