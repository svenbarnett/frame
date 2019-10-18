package com.deepblue777.frame.action;

import com.alibaba.fastjson.JSON;
import com.deepblue777.frame.domain.FrameUser;
import com.deepblue777.frame.service.FrameUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * 框架用户管理控制器
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/15 15:19
 * @since 1.0
 */
@RestController
@RequestMapping("/frame")
public class FrameUserAction {

  private final static Logger logger = LoggerFactory.getLogger(FrameUserAction.class);

  @Autowired
  private FrameUserService frameUserService;

  @GetMapping("/user/list")
  public ModelAndView list() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("frame/user/list");
    logger.info("rest");
    return mv;
  }

  @GetMapping("/user/add")
  public ModelAndView add() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("frame/user/add");
    return mv;
  }

  @RequestMapping(value = "/user/doLogin", method = RequestMethod.POST)
  public String doLogin(@RequestParam("loginid") String loginid, @RequestParam("password") String password, Subject subject1) {
    logger.debug("登录loginid为：{}", loginid);
    FrameUser user = frameUserService.findByLoginid(loginid);
    System.out.println(user);
    Subject subject = SecurityUtils.getSubject();

    UsernamePasswordToken token = new UsernamePasswordToken(loginid, "11111", "spring-boot-shiro");
    subject.login(token);
    return JSON.toJSONString(new HashMap<>());
  }

}
