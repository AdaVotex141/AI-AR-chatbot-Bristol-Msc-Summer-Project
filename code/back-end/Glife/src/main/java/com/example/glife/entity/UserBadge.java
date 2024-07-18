package com.example.glife.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.IdClass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("user_badge")
@IdClass(UserBadgeId.class)
public class UserBadge implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long badgeId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime earnedTime;
}