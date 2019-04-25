package com.team.core.security;

import com.team.entity.mybatis.system.User;
import com.team.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public JwtAuthenticationProvider(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String loginName = authentication.getPrincipal().toString();
            String password = authentication.getCredentials().toString();
            if (!StringUtils.isEmpty(loginName)) {
                User user = userService.SelectBy(User::getLoginName, loginName);
                if (user != null) {
                    if (!passwordEncoder.matches(password, user.getPassword())) {
                        throw new BadCredentialsException("密码错误");
                    }
                } else {
                    throw new BadCredentialsException("该用户不存在");
                }
            }
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        } catch (NullPointerException e) {
            throw new BadCredentialsException("用户名和密码不能为空");
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
