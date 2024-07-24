package com.example.glife.common;

import com.alibaba.fastjson.JSON;
import com.example.glife.common.PasswordEncoder;
import com.example.glife.entity.Admin;
import com.example.glife.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Order(1)
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Not needed for this filter
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();


        // Exclude URLs that do not require login check
        if (check(requestURI)) {
            filterChain.doFilter(request, response);
            log.info("This website:{},--------pass--------",requestURI);
            return;
        }
        // get current session
        HttpSession session = request.getSession(false);

        if(session != null && session.getAttribute("user") != null){
            log.info("user has logged in, --------pass-------");
            filterChain.doFilter(request,response) ;
            return;
        }

        Admin admin = AdminHolder.getAdmin();
        if(admin != null){
            log.info("admin has logged in, -------pass-------");
            filterChain.doFilter(request,response) ;
            return;
        }

        response.getWriter().write((JSON.toJSONString(R.error("NOTLOGIN"))));
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/index.html");
        log.info("request not pass:{}", request.getRequestURI());
        return;
    }

    @Override
    public void destroy() {
        // Not needed for this filter
    }

    private boolean check(String requestURI){
        String[] urls = new String[]{
            "/index.html",
                "/login",
                "/register",
                "/sendCode",
                "/routine/**",
                "/assistant/**",
                "/system_routine/**",
                "/admin/**",
                "/badges/**",
                "/user_badges/**",
                "/message/**",
                "/ARTree/**"
        };
        for(String url: urls){
            if(PATH_MATCHER.match(url,requestURI)){
                return true;
            }
        }
        return false;
    }
}
