package com.example.glife.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("routine")
public class Routine implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;

    private Long userid;

    private String content;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private int tick;
}
