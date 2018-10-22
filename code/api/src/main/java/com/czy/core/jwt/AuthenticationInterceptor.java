package com.czy.core.jwt;

import com.alibaba.fastjson.JSONObject;
import com.czy.entity.po.User;
import com.czy.service.RoleMenuService;
import com.czy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;

        String token = request.getHeader("Authorization");// 从 http 请求头中取出 token
        if (token != null) {
            String loginName = JwtUtil.GetLoginName(token);
            if (loginName != null) {
                User user = userService.SelectBy("LoginName", loginName);
                if (user != null) {
                    if (JwtUtil.Verify(token, user.getLoginName(), user.getPassword())) {
                        List<String> apis = roleMenuService.getPermissionsByUserId(user.getUserId());
                        if (user.getLoginName().equals("admin")) {
                            return true;
                        }
                        if (apis.contains(request.getRequestURI())) {
                            return true;
                        }
                    }
                }
            }
            try {
                out = response.getWriter();
                JSONObject json = new JSONObject();
                json.put("status", "401");
                json.put("message", "未授权");
                out.append(json.toString());
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return request.getRequestURI().equals("/user/login");
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
