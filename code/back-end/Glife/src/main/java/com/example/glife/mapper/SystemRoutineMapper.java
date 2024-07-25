package com.example.glife.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.glife.common.R;
import com.example.glife.entity.SystemRoutine;
import com.example.glife.entity.User;
import org.apache.ibatis.annotations.Mapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Mapper
public interface SystemRoutineMapper extends BaseMapper<SystemRoutine> {


}
