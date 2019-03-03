package com.team.core.configure;

import org.springframework.web.servlet.HandlerInterceptor;

public class AuthenticationInterceptor implements HandlerInterceptor {

//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private RoleMenuService roleMenuService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        String token = request.getHeader("Authorization");
//        if (token != null) {
//            String loginName = JwtUtil.GetLoginName(token);
//            if (loginName != null) {
//                User user = userService.SelectBy(User::getLoginName, loginName);
//                if (user != null) {
//                    User currentUser = JwtUtil.getCurrentUser();
//                    if (currentUser == null || !currentUser.getLoginName().equals(user.getLoginName())) {
//                        JwtUtil.setCurrentUser(user);
//                    }
//                    if (JwtUtil.Verify(token, user.getLoginName(), user.getPassword())) {
//                        if (user.getLoginName().equals("admin")) {
//                            return true;
//                        }
//                        List<String> apis = roleMenuService.getPermissionsByUserId(user.getUserId());
//                        if (apis.stream().map(String::toLowerCase).collect(Collectors.toList()).contains((request.getRequestURI().toLowerCase()))) {
//                            return true;
//                        }
//                    }
//                    throw new WebException(ErrorCode.NO_AUTH, "未授权");
//                }
//            }
//        }
//        throw new WebException(ErrorCode.TOKEN_ERROR, "令牌为空或输入有误");
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//
//    }
}
