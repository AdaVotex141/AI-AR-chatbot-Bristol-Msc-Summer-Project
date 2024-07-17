package com.example.glife.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.EmbeddedId;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("user_badge")
public class UserBadge implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UserBadgeId id;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime earnedTime;

    public UserBadgeId getId() {
        return id;
    }

    public void setId(UserBadgeId id) {
        this.id = id;
    }
}
