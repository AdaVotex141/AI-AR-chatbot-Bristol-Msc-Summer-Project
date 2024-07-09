package com.example.glife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.glife.common.R;
import com.example.glife.entity.Routine;
import com.example.glife.entity.User;
import com.example.glife.entity.UserTree;
import com.example.glife.mapper.RoutineMapper;
import com.example.glife.mapper.UserTreeMapper;
import com.example.glife.service.RoutineService;
import com.example.glife.service.UserTreeService;
import com.fasterxml.jackson.core.TreeCodec;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserTreeImp extends ServiceImpl<UserTreeMapper, UserTree> implements UserTreeService {

    public R<Integer> init(HttpServletRequest request){
        Long userID = getUserID(request);
        int treeCode = -1;
        if(userID != null){
            LambdaQueryWrapper<UserTree> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(UserTree::getUserid, userID);
            UserTree userTree = baseMapper.selectOne(lambdaQueryWrapper);

            if (userTree != null) {
                treeCode = userTree.getTick_sum();
                return R.success(treeCode);
            }else {
                return R.error("Can't find treeCode for this user");
            }
        }else{
            return R.error("Can't get userId");
        }
    }

    public R<Integer> update(HttpServletRequest request){
        Long userID = getUserID(request);
        int treeCode = -1;
        if(userID != null){
            LambdaQueryWrapper<UserTree> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(UserTree::getUserid, userID);
            UserTree userTree = baseMapper.selectOne(lambdaQueryWrapper);

            if (userTree != null) {
                treeCode = userTree.getTick_sum();
                if(treeCode == 8){
                    treeCode = 1;
                    userTree.setTick_sum(treeCode);
                }else{
                    treeCode = treeCode+1;
                    userTree.setTick_sum(treeCode);
                }
                baseMapper.updateById(userTree);
                return R.success(treeCode);
            }else {
                return R.error("Can't find treeCode for this user");
            }
        }else{
            return R.error("Can't get userId");
        }
    }

    public R<Integer> reUpdate(HttpServletRequest request){
        Long userID = getUserID(request);
        int treeCode = -1;
        if(userID != null){
            LambdaQueryWrapper<UserTree> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(UserTree::getUserid, userID);
            UserTree userTree = baseMapper.selectOne(lambdaQueryWrapper);

            if (userTree != null) {
                treeCode = userTree.getTick_sum();
                if(treeCode == 1){
                    treeCode = 7;
                    userTree.setTick_sum(treeCode);
                }else{
                    treeCode = treeCode-1;
                    userTree.setTick_sum(treeCode);
                }
                baseMapper.updateById(userTree);
                return R.success(treeCode);
            }else {
                return R.error("Can't find treeCode for this user");
            }
        }else{
            return R.error("Can't get userId");
        }
    }

    private Long getUserID(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        User user = null;
        Long userid = null;
        if(session != null && session.getAttribute("user") != null){
            user = (User) session.getAttribute("user");
        }
        userid = user.getId();
        return userid;
    }
}
