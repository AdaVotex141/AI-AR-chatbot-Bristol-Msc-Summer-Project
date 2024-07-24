package com.example.glife.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.glife.entity.RandomTask;
import com.example.glife.entity.Routine;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface RandomTaskMapper  extends BaseMapper<RandomTask> {

}
