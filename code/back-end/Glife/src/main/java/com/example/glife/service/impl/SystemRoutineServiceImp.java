package com.example.glife.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
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
import lombok.extern.slf4j.Slf4j;
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
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
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

        //------------find in Redis----------
        String key = RedisConstants.SYS_ROUTINE+userid;
        Set<Object> fields = stringRedisTemplate.opsForHash().keys(key);
        //sort by ID
        List<Integer> sortedIds = fields.stream()
                .map(Object::toString)
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());
        for(Integer routineID: sortedIds){
            String field = routineID.toString();
            String routineJson= (String) stringRedisTemplate.opsForHash().get(key, field);
            if(StrUtil.isNotBlank(routineJson)) {
                SystemRoutine routine = JSONUtil.toBean( routineJson, SystemRoutine.class);
                routineRepo.add(routine);
            }
        }

        //------------find in Database-------
        if(routineRepo.isEmpty()){
            LambdaQueryWrapper<SystemRoutine> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(SystemRoutine::getUserid, userid);
            lambdaQueryWrapper.orderByAsc(SystemRoutine::getCreateTime);

            List<SystemRoutine> routines = baseMapper.selectList(lambdaQueryWrapper);
            if (routines != null) {
                routineRepo.addAll(routines);
            }

                for (SystemRoutine routine : routines) {
                    String field = routine.getId().toString();
                    String routineJson = JSONUtil.toJsonStr(routine);
                    stringRedisTemplate.opsForHash().put(key, field, routineJson);
                }
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
        deleteInRedis(request);



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
        deleteInRedis(request);


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
        deleteInRedis(request);


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

    private Long getUserID(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        User user = null;
        Long userid = Long.valueOf(0);
        if(session != null && session.getAttribute("user") != null){
            user = (User) session.getAttribute("user");
        }
        userid = user.getId();
        return userid;
    }

    private void deleteInRedis(HttpServletRequest request){
        String userid = getUserID(request).toString();
        String key = RedisConstants.SYS_ROUTINE + userid;
        Boolean hasKey = stringRedisTemplate.hasKey(key);
        if(hasKey != null && hasKey){
            stringRedisTemplate.delete(key);
            log.info("success delete:{}", key);
        }
    }





}
