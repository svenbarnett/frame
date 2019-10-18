package com.deepblue777.frame.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * apache shiro 的角色过滤是 and的关系，需要重新写成or的关系。
 * 这个roles的filter是通过subject.hasAllRoles(roles)判断是否满足所有权限,
 * 但是我们真实项目中,很多时候用户只要满足其中一个角色即可认为是授权认证成功。
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/18 14:41
 * @since 1.0
 */
public class FrameRolesAuthorizationFilter extends RolesAuthorizationFilter {
  @Override
  public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
    Subject subject = getSubject(request, response);
    String[] rolesArray = (String[]) mappedValue;
    //没有角色限制，有权限访问
    if (rolesArray == null || rolesArray.length == 0) {
      return true;
    }
    for (int i = 0; i < rolesArray.length; i++) {
      //若当前用户是rolesArray中的任何一个，则有权限访问
      if (subject.hasRole(rolesArray[i])) {
        return true;
      }
    }
    return false;
  }
}
