package com.example.glife.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.glife.entity.Routine;
import com.example.glife.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoutineMapper  extends BaseMapper<Routine> {
}
