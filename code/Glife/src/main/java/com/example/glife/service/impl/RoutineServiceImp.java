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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class RoutineServiceImp extends ServiceImpl<RoutineMapper, Routine> implements RoutineService {


    /**
     *
     * @param request
     * @param routine
     * @return
     */
    public R<String> add(HttpServletRequest request, Routine routine){
        //get current login user
        HttpSession session = request.getSession(false);
        User user = null;
        if(session != null && session.getAttribute("user") != null){
            user = (User) session.getAttribute("user");
        }else{
            return R.error("user hasn't login");
        }

        Long userid = user.getId();
        String content = routine.getContent();

        //set new Routine
        Routine newRoutine = new Routine();
        newRoutine.setUserid(userid);
        newRoutine.setContent(content);
        newRoutine.setTick(0);
        newRoutine.setCreateTime(LocalDateTime.now());
        baseMapper.insert(newRoutine);


        return R.success("create routine success");
    }

    /**
     *
     * @param request
     * @param routine
     * @return
     */
    public R<Routine> update(HttpServletRequest request, Routine routine){
        Long id = routine.getId();
        //find current line of routine
        LambdaQueryWrapper<Routine> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Routine::getId, routine.getId());
        Routine selectRoutine = baseMapper.selectOne(lambdaQueryWrapper);
        if(selectRoutine == null){
            return R.error("fail to find this routine");
        }

        String updateContent = routine.getContent();
        selectRoutine.setContent(updateContent);

        return R.success(selectRoutine);
    }

    /**
     *
     * @param request
     * @param routine
     * @return
     */
    public R<Routine> tick(HttpServletRequest request, Routine routine){
 /*       //get current login user
        HttpSession session = request.getSession(false);
        User user = null;
        if(session != null && session.getAttribute("user") != null){
            user = (User) session.getAttribute("user");
        }else{
            return R.error("user hasn't login");
        }*/

        //find current line of routine
        LambdaQueryWrapper<Routine> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Routine::getId, routine.getId());
        Routine selectRoutine = baseMapper.selectOne(lambdaQueryWrapper);
        if(selectRoutine == null){
            return R.error("fail to find this routine");
        }

        if(selectRoutine.getTick() == 0){
            selectRoutine.setTick(1);
        }else if(selectRoutine.getTick() == 1){
            selectRoutine.setTick(0);
        }

        return R.success(selectRoutine);
    }

    /**
     *
     * @return
     */
    public R<String> init(HttpServletRequest request){
        ArrayList<Routine> routineRepo = new ArrayList<>();
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


        //TODO find repo and quick sort


        return null;
    }

    public R<String> delete(HttpServletRequest request, Routine routine){
        Long id = routine.getId();
        LambdaQueryWrapper<Routine> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Routine::getId, routine.getId());
        Routine selectRoutine = baseMapper.selectOne(lambdaQueryWrapper);
        if(selectRoutine == null){
            return R.error("fail to find this routine");
        }
        //TODO delete
       // BaseMapper.delete(selectRoutine);


        return null;
    }

}
