package com.example.glife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.glife.common.R;
import com.example.glife.entity.Routine;
import com.example.glife.entity.User;
import com.example.glife.mapper.RoutineMapper;
import com.example.glife.service.AssistantService;
import com.example.glife.service.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoutineServiceImp extends ServiceImpl<RoutineMapper, Routine> implements RoutineService {
    @Autowired
    AssistantService assistantService;

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

        return R.success("create routine success");
    }

    public R<Routine> addFromAssistant(HttpServletRequest request){

        return null;
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

        //find
        LambdaQueryWrapper<Routine> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Routine::getUserid, userid);
        lambdaQueryWrapper.orderByAsc(Routine::getCreateTime);

        List<Routine> routines = baseMapper.selectList(lambdaQueryWrapper);
        if (routines != null) {
            routineRepo.addAll(routines);
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
        return R.success("delete success");
    }
}
