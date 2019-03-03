package com.team.shiro;

import com.team.entity.mybatis.system.Function;
import com.team.entity.mybatis.system.Role;
import com.team.repository.mybatis.system.UserRepository;
import com.team.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    public CustomRealm(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("-----权限认证-----");
        String loginName = JwtUtil.GetLoginName(principalCollection.toString());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<Role> roles = userRepository.getRolesByLoginName(loginName);
        List<Function> roleFunctions = userRepository.getFunctionsByRole(roles.stream().map(t -> t.getRoleId()).collect(Collectors.toList()));
        Set<String> roleSet = roles.stream().map(Role::getRoleName).collect(Collectors.toSet());
        Set<String> functionSet = roleFunctions.stream().map(Function::getFunctionCode).collect(Collectors.toSet());
        authorizationInfo.setRoles(roleSet);
        authorizationInfo.setStringPermissions(functionSet);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("-----身份认证方法-----");
        String token = (String) authenticationToken.getCredentials();
        String loginName = JwtUtil.GetLoginName(token);
        return new SimpleAuthenticationInfo(token, token, "MyRealm");
    }
}
