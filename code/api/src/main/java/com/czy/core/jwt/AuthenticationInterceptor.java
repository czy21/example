package com.czy.core.jwt;

import com.czy.core.exception.ErrorCode;
import com.czy.core.exception.WebException;
import com.czy.core.extension.StringExtension;
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
                        if (StringExtension.ConvertAllToLower(apis).contains((request.getRequestURI()))) {
                            return true;
                        }
                    }
                    throw new WebException(ErrorCode.NO_AUTH, "未授权");
                }
            }
        }
        throw new WebException(ErrorCode.NO_TOKEN, "Token为空");
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
