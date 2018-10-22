package com.czy.core.shiro;

import com.czy.entity.po.User;
import com.czy.service.RoleMenuService;
import com.czy.service.UserRoleService;
import com.czy.service.UserService;
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
import org.springframework.stereotype.Service;

@Component
public class MyRealm extends AuthorizingRealm {


    private UserService userService;

    @Autowired
    public MyRealm(UserService userService) {
        this.userService = userService;
    }

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String loginName = JwtUtil.GetLoginName(principals.toString());
        User user = userService.SelectBy("LoginName", loginName);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();

        String loginName = JwtUtil.GetLoginName(token);

        if (loginName == null) {
            throw new AuthenticationException("token invalid");
        }

        User user = userService.SelectBy("LoginName", loginName);
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (!JwtUtil.Verify(token, loginName, user.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }
        return new SimpleAuthenticationInfo(token, token, "my_realm");

    }
}
