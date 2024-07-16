package com.example.glife.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("user_badge")
public class UserBadge implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long userId;

    @TableId
    private Long badgeId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime earnedTime;
}
