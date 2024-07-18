package com.example.glife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.glife.common.AdminHolder;
import com.example.glife.common.PasswordEncoder;
import com.example.glife.common.R;
import com.example.glife.entity.Admin;
import com.example.glife.entity.Routine;
import com.example.glife.entity.User;
import com.example.glife.mapper.AdminMapper;
import com.example.glife.mapper.UserMapper;
import com.example.glife.service.AdminService;
import com.example.glife.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
public class AdminServiceImp extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * login
     * @param request
     * @param admin
     * @return
     */
    public R<Admin> login(HttpServletRequest request, Admin admin){
        String inputPassword = admin.getPassword();

        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Admin::getUsername, admin.getUsername());

        Admin foundAdmin = getOne(lambdaQueryWrapper);

        if (foundAdmin == null) {
            return R.error("This user has no permission to enter admin system.");
        }
        if(!passwordEncoder.matchPassword(inputPassword, foundAdmin.getPassword())){
            return R.error("password wrong");
        }

        if(foundAdmin.getPermission() == 0){
            return R.error("Doesn't have permission to the admin panel");
        }

        request.getSession().setAttribute("admin", foundAdmin);
//        AdminHolder.saveAdmin(foundAdmin);
//        log.info("adminHolder:{}", AdminHolder.getAdmin());

        return R.success(foundAdmin);
    }

    /**
     *
     * @param request
     * @param admin
     * @return
     */
    public R<String> add(HttpServletRequest request, Admin admin){
        //check duplicate
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Admin::getUsername, admin.getUsername());
        Admin existingAdmin = baseMapper.selectOne(lambdaQueryWrapper);
        if (existingAdmin != null) {
            return R.error("Username already exists");
        }

        String encodedPassword = passwordEncoder.encodePassword(admin.getPassword());
        admin.setPassword(encodedPassword);
        admin.setCreateTime(LocalDateTime.now());
        admin.setPermission(1);
        baseMapper.insert(admin);
        return R.success("insert success");
    }

    /**
     * change permission
     * @param request
     * @param admin
     * @return
     */
    public R<String> changePermission(HttpServletRequest request, Admin admin){
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Admin::getUsername, admin.getUsername());

        Admin foundAdmin = getOne(lambdaQueryWrapper);
        //get session admin
        Admin loginAdmin = (Admin) request.getSession(false).getAttribute("admin");

        //check current login admin
        if(foundAdmin == null ||loginAdmin.getPermission() != 2 ||foundAdmin.getPermission() == 2){
            return R.error("You don't have permission to change others' permission");
        }

        if (foundAdmin != null) {
            baseMapper.deleteById(foundAdmin);
            return R.success("Change permission success");
        } else {
            return R.error("Admin not found");
        }
    }

    public R<List<Admin>> init(HttpServletRequest request){
        List<Admin> adminRepo = baseMapper.findAllOrderByCreateTimeAsc();
        return R.success(adminRepo);
    }

    public R<String> logout(HttpServletRequest request){
        AdminHolder.removeAdmin();
        return R.success("success logout");
    }



}
