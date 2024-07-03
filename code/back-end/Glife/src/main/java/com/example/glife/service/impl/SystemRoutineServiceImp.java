package com.example.glife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.glife.common.R;
import com.example.glife.common.RedisConstants;
import com.example.glife.entity.Routine;
import com.example.glife.entity.SystemRoutine;
import com.example.glife.entity.User;
import com.example.glife.mapper.SystemRoutineMapper;
import com.example.glife.mapper.UserMapper;
import com.example.glife.service.AssistantService;
import com.example.glife.service.SystemRoutineService;
import com.example.glife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SystemRoutineServiceImp extends ServiceImpl<SystemRoutineMapper, SystemRoutine> implements SystemRoutineService {

    @Autowired
    AssistantService assistantService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     *
     * @param request
     * @return
     */
    public R<List<SystemRoutine>> init(HttpServletRequest request){
        List<SystemRoutine> routineRepo = new ArrayList<>();
        //get current login user
        Long userid = assistantService.getUserID(request);

        //find
        LambdaQueryWrapper<SystemRoutine> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SystemRoutine::getUserid, userid);
        lambdaQueryWrapper.orderByAsc(SystemRoutine::getCreateTime);

        List<SystemRoutine> routines = baseMapper.selectList(lambdaQueryWrapper);
        if (routines != null) {
            routineRepo.addAll(routines);
        }

        return R.success(routineRepo);
    }

    /**
     *
     * @param request
     * @param id
     * @return
     */
    public R<String> delete(HttpServletRequest request, Long id){
        LambdaQueryWrapper<SystemRoutine> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SystemRoutine::getId, id);
        SystemRoutine selectRoutine = baseMapper.selectOne(lambdaQueryWrapper);
        if(selectRoutine == null){
            return R.error("fail to find this routine");
        }
        baseMapper.delete(lambdaQueryWrapper);
        return R.success("delete success");

    }

    /**
     *
     * @param request
     * @param id
     * @return
     */
    @Transactional
    public R<SystemRoutine> tick(HttpServletRequest request, Long id){
        LambdaQueryWrapper<SystemRoutine> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SystemRoutine::getId, id);
        SystemRoutine selectRoutine = baseMapper.selectOne(lambdaQueryWrapper);
        if(selectRoutine == null){
            return R.error("fail to find this routine");
        }

        if(selectRoutine.getTick() == 0){
            selectRoutine.setTick(1);
        }else if(selectRoutine.getTick() == 1){
            selectRoutine.setTick(0);
        }
        baseMapper.updateById(selectRoutine);

        return R.success(selectRoutine);
    }

    /**
     *
     * @param request
     * @return
     */
    public R<String> addFromAssistant(HttpServletRequest request){
        //get selection from redis
        Long userid = assistantService.getUserID(request);
        String key =  RedisConstants.User_SELECTION+userid;

        String content = stringRedisTemplate.opsForValue().get(key);

        //set new Routine
        SystemRoutine newRoutine = new SystemRoutine();
        newRoutine.setUserid(userid);
        newRoutine.setContent(content);
        newRoutine.setTick(0);
        newRoutine.setCreateTime(LocalDateTime.now());
        baseMapper.insert(newRoutine);

        return R.success("create routine success");
    }

    /**
     * TODO
     * @param request
     * @param content
     * @return
     */
    public R<String> addFromRandomTask(HttpServletRequest request, String content){

        return null;
    }

    /**
     * reset the routine everyday in GMT 0:00
     */
    @Scheduled(cron = "0 0 * * *",zone = "GMT")
    public void reset(){
        UpdateWrapper<SystemRoutine> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("tick", 0);
        baseMapper.update(null, updateWrapper);
    }





}
