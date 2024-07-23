package com.example.glife.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("user")
@JsonSerialize
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;

    private String username;

    private String password;

    private String email;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime lastLogin;

    private int loginDays;

}
