package com.example.glife.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.glife.entity.Admin;
import com.example.glife.entity.Routine;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper extends BaseMapper<Admin> {
    @Select("SELECT * FROM admin ORDER BY create_time ASC")
    List<Admin> findAllOrderByCreateTimeAsc();
}
