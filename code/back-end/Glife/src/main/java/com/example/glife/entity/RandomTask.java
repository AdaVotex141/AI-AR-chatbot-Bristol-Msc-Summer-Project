package com.example.glife.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("random_task")
public class RandomTask {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private String content;

    private String creater;

    private String sender;

    private LocalDateTime createTime;

    private LocalDateTime lastSendTime;

}
