package com.deepblue777.frame.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 一句话简单描述
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/12 14:50
 * @since 1.0
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {

  @Override
  public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
    UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) info;
    String loginid = usernamePasswordToken.getUsername();
    String password = new String(usernamePasswordToken.getPassword());
    String dbpassword = (String) usernamePasswordToken.getCredentials();
    return this.equals(password, dbpassword);

  }
}
