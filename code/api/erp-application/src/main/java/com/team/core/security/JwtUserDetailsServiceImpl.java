package com.team.core.security;

import com.team.entity.mybatis.system.Function;
import com.team.entity.mybatis.system.User;
import com.team.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component(value = "JwtUserDetailsServiceImpl")
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        User sysUser = userService.SelectBy(User::getLoginName, loginName);
        if (sysUser == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", sysUser));
        } else {
            return create(sysUser, userService.getFunctionsByUser(sysUser.getUserId()));
        }
    }

    private JwtUserDetails create(User user, List<Function> functions) {
        return new JwtUserDetails(user.getLoginName(), user.getPassword(), functions.stream().map(t -> new SimpleGrantedAuthority(t.getFunctionCode())).collect(Collectors.toList()));
    }
}
