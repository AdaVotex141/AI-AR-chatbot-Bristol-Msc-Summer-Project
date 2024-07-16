package com.example.glife.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("user_badge")
public class UserBadge implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableField("user_id")
    private Long userId;

    @TableField("badge_id")
    private Long badgeId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime earnedTime;

    // Composite key class
    public UserBadgeId getId() {
        return new UserBadgeId(userId, badgeId);
    }

    public void setId(UserBadgeId id) {
        this.userId = id.getUserId();
        this.badgeId = id.getBadgeId();
    }
}
