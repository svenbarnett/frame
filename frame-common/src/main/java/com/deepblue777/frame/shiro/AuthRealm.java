package com.deepblue777.frame.shiro;

import com.deepblue777.frame.domain.FramePermission;
import com.deepblue777.frame.domain.FrameRole;
import com.deepblue777.frame.domain.FrameUser;
import com.deepblue777.frame.service.FrameUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 权限验证shiro
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/12 13:23
 * @since 1.0
 */
public class AuthRealm extends AuthorizingRealm {

  private final static Logger LOGGER = LoggerFactory.getLogger(AuthRealm.class);
  @Autowired
  private FrameUserService frameUserService;

  // 授权
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    FrameUser frameUser = (FrameUser) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
    List<String> permissionList = new LinkedList<>();
    Set<FrameRole> roleSet = frameUser.getRoles();
    Set<String> roleList = new LinkedHashSet<>();
    if (CollectionUtils.isNotEmpty(roleSet)) {
      for (FrameRole frameRole : roleSet) {
        roleList.add(frameRole.getRoleName());
        Set<FramePermission> permissionSet = frameRole.getPermissions();
        if (CollectionUtils.isNotEmpty(permissionSet)) {
          for (FramePermission framePermission : permissionSet) {
            permissionList.add(frameRole.getRoleName()+":"+framePermission.getPermissionName());
          }
        }
      }
    }
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    info.addStringPermissions(permissionList);
    LOGGER.debug("#### 当前登录用户具备的资源权限：{}", permissionList.toString());
    info.addRoles(roleList);
    LOGGER.debug("#### 当前登录用户具备的角色权限：{}", roleList.toString());
    return info;
  }

  // 认证登录
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
    String loginid = usernamePasswordToken.getUsername();
    FrameUser frameUser = frameUserService.findByLoginid(loginid);
    return new SimpleAuthenticationInfo(frameUser, frameUser.getPassword(), this.getClass().getName());
  }
}
