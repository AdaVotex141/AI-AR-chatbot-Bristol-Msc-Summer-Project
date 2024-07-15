package com.example.glife.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("admin")
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;

    private String username;

    private String password;

    private Integer permission;

    private LocalDateTime createTime;
}
