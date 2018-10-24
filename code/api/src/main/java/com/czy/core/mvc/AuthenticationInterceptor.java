package com.czy.core.mvc;

import com.czy.core.exception.ErrorCode;
import com.czy.core.exception.WebException;
import com.czy.core.extension.StringExtension;
import com.czy.core.util.JwtUtil;
import com.czy.entity.po.User;
import com.czy.service.RoleMenuService;
import com.czy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            String loginName = JwtUtil.GetLoginName(token);
            if (loginName != null) {
                User user = userService.SelectBy("LoginName", loginName);
                if (user != null) {
                    if (JwtUtil.Verify(token, user.getLoginName(), user.getPassword())) {
                        if (user.getLoginName().equals("admin")) {
                            return true;
                        }
                        List<String> apis = roleMenuService.getPermissionsByUserId(user.getUserId());
                        if (StringExtension.ConvertAllToLower(apis).contains((request.getRequestURI().toLowerCase()))) {
                            return true;
                        }
                    }
                    throw new WebException(ErrorCode.NO_AUTH, "未授权");
                }
            }
        }
        throw new WebException(ErrorCode.TOKEN_ERROR, "令牌为空或输入有误");
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
