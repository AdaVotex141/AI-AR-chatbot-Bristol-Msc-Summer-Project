package com.example.glife.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.glife.common.R;
import com.example.glife.common.RedisConstants;
import com.example.glife.entity.Routine;
import com.example.glife.entity.User;
import com.example.glife.mapper.RoutineMapper;
import com.example.glife.service.AssistantService;
import com.example.glife.service.RoutineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
public class RoutineServiceImp extends ServiceImpl<RoutineMapper, Routine> implements RoutineService {
    @Autowired
    AssistantService assistantService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     *
     * @param request
     * @param content
     * @return
     */
    public R<String> add(HttpServletRequest request, String content){
        //get current login user
        HttpSession session = request.getSession(false);
        User user = null;
        if(session != null && session.getAttribute("user") != null){
            user = (User) session.getAttribute("user");
        }else{
            return R.error("user hasn't login");
        }

        Long userid = user.getId();

        //set new Routine
        Routine newRoutine = new Routine();
        newRoutine.setUserid(userid);
        newRoutine.setContent(content);
        newRoutine.setTick(0);
        newRoutine.setCreateTime(LocalDateTime.now());
        baseMapper.insert(newRoutine);

        deleteInRedis(request);

        return R.success("create routine success");
    }

    /**
     *
     * @param request
     * @param routine
     * @return
     */

    public R<Routine> update(HttpServletRequest request, Routine routine){
        //find current line of routine


        boolean updateSuccess = updateById(routine);
        if (!updateSuccess) {
            return R.error("fail to update this routine");
        }

        deleteInRedis(request);

        return R.success(routine);
    }

    /**
     *
     * @param request
     * @param id
     * @return
     */
    @Transactional
    public R<Routine> tick(HttpServletRequest request, Long id){

        LambdaQueryWrapper<Routine> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Routine::getId, id);
        Routine selectRoutine = baseMapper.selectOne(lambdaQueryWrapper);
        if(selectRoutine == null){
            return R.error("fail to find this routine");
        }

        if(selectRoutine.getTick() == 0){
            selectRoutine.setTick(1);
        }else if(selectRoutine.getTick() == 1){
            selectRoutine.setTick(0);
        }
        baseMapper.updateById(selectRoutine);

        //delete in Redis

        deleteInRedis(request);


        return R.success(selectRoutine);
    }

    /**
     *
     * @return
     */
    public R<List<Routine>> init(HttpServletRequest request){
        List<Routine> routineRepo = new ArrayList<>();
        //get current login user
        HttpSession session = request.getSession(false);
        User user = null;
        if(session != null && session.getAttribute("user") != null){
            user = (User) session.getAttribute("user");
        }else{
            return R.error("user hasn't login");
        }
        Long userid = user.getId();

        //------------find in Redis----------
        String key = RedisConstants.CUSTOM_ROUTINE+userid;
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
                Routine routine = JSONUtil.toBean( routineJson, Routine.class);
                routineRepo.add(routine);
            }
        }
        //------------find in Database-------
        if(routineRepo.isEmpty()){
            LambdaQueryWrapper<Routine> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Routine::getUserid, userid);
            lambdaQueryWrapper.orderByAsc(Routine::getCreateTime);

            List<Routine> routines = baseMapper.selectList(lambdaQueryWrapper);
            if (routines != null) {
                routineRepo.addAll(routines);

                for (Routine routine : routines) {
                    String field = routine.getId().toString();
                    String routineJson = JSONUtil.toJsonStr(routine);
                    stringRedisTemplate.opsForHash().put(key, field, routineJson);
                }
            }
        }

        return R.success(routineRepo);
    }

    //delete id
    public R<String> delete(HttpServletRequest request, Long id){
        LambdaQueryWrapper<Routine> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Routine::getId, id);
        Routine selectRoutine = baseMapper.selectOne(lambdaQueryWrapper);
        if(selectRoutine == null){
            return R.error("fail to find this routine");
        }

        baseMapper.delete(lambdaQueryWrapper);

        deleteInRedis(request);

        return R.success("delete success");
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
        String key = RedisConstants.CUSTOM_ROUTINE + userid;
        Boolean hasKey = stringRedisTemplate.hasKey(key);
        if(hasKey != null && hasKey){
            stringRedisTemplate.delete(key);
            log.info("success delete:{}", key);
        }
    }
}
