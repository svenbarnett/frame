package com.deepblue777.frame.shiro;

import com.alibaba.fastjson.JSON;
import com.deepblue777.frame.common.BaseResponse;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.http.HttpMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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

  @Override
  public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
    HttpServletRequest servletRequest = (HttpServletRequest) request;
    HttpServletResponse servletResponse = (HttpServletResponse) response;
    //处理跨域问题，跨域的请求首先会发一个options类型的请求
    if (servletRequest.getMethod().equals(HttpMethod.OPTIONS.name())) {
      return true;
    }
    boolean isAccess = isAccessAllowed(request, response, mappedValue);
    if (isAccess) {
      return true;
    }
    servletResponse.setCharacterEncoding("UTF-8");
    Subject subject = getSubject(request, response);
    PrintWriter printWriter = servletResponse.getWriter();
    servletResponse.setContentType("application/json;charset=UTF-8");
    servletResponse.setHeader("Access-Control-Allow-Origin", servletRequest.getHeader("Origin"));
    servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
    servletResponse.setHeader("Vary", "Origin");
    String respStr;
    if (subject.getPrincipal() == null) {
      respStr = JSON.toJSONString(new BaseResponse<>(207, "您还未登录，请先登录"));
    } else {
      respStr = JSON.toJSONString(new BaseResponse<>(403, "您没有此权限，请联系管理员"));
    }
    printWriter.write(respStr);
    printWriter.flush();
    servletResponse.setHeader("content-Length", respStr.getBytes().length + "");
    return false;
  }
}
