package com.czy.core.shrio;


//public class CustomRealm extends AuthorizingRealm {
//
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private UserRoleService userRoleService;
//    @Autowired
//    private RoleMenuService roleMenuService;
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        if (principals == null) {
//            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
//        }
//        User user = (User) getAvailablePrincipal(principals);
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setRoles(user.getRoles());
//        info.setStringPermissions(user.getPermissions());
//        return info;
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//
////        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
////
////        String loginName = upToken.getUsername();
////
////        if (loginName == null) {
////            throw new AccountException("Null userName are not allowed by this realm.");
////        }
////        User userDB = userService.SelectBy("LoginName" , loginName);
////        if (userDB == null) {
////            throw new UnknownAccountException("No account found for admin [" + loginName + "]");
////        }
////
////        List<String> roleList = userRoleService.getRolesByUserId(userDB.getUserId());
////        List<String> permissionList = roleMenuService.getPermissionsByUserId(userDB.getUserId());
////
////        Set<String> roles = new HashSet<>(roleList);
////        Set<String> permissions = new HashSet<>(permissionList);
////        userDB.setRoles(roles);
////        userDB.setPermissions(permissions);
////        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDB, userDB.getPassword(), getName());
//////        info.setCredentialsSalt(ByteSource.Util.bytes(userDB.getSalt()));
//        return null;
//    }
//}
