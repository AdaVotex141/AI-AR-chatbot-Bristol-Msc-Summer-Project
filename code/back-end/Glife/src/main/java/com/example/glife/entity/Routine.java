package com.example.glife.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("routine")
public class Routine implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userid;

    private String content;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private int tick;
}
