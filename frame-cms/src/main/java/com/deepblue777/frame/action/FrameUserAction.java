package com.deepblue777.frame.action;

import com.alibaba.fastjson.JSON;
import com.deepblue777.frame.domain.FrameUser;
import com.deepblue777.frame.service.FrameUserService;
import com.deepblue777.frame.vo.TableVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

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

  @Autowired
  private FrameUserService frameUserService;

  @GetMapping("/user/list")
  public ModelAndView list() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("frame/user/list");
    return mv;
  }

  @PostMapping("/user/table")
  public String table(@RequestBody Map map) {
    int count = frameUserService.findCountByMaps(map);
    List<FrameUser> users;
    users = frameUserService.findByMaps(map);
    return JSON.toJSONString(new TableVO<>(0, "操作成功!", count, users));
  }

  @GetMapping("/user/add")
  public ModelAndView add() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("frame/user/add");
    return mv;
  }

  @GetMapping("/user/edit")
  public ModelAndView edit() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("frame/user/edit");
    return mv;
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView login() {
    Subject subject = SecurityUtils.getSubject();
    boolean authenticated = subject.isAuthenticated();
    if (authenticated) {
      return new ModelAndView("redirect:../frame/index");
    }
    return new ModelAndView("login");
  }

  @RequestMapping(value = "/user/doLogin", method = RequestMethod.POST)
  public String doLogin(@RequestParam("loginid") String loginid, @RequestParam("password") String password) {
    return JSON.toJSONString(frameUserService.doLogin(loginid, password));
  }

  @RequestMapping(value = "/user/logout", method = RequestMethod.POST)
  public String logout() {
    return JSON.toJSONString(frameUserService.logout());
  }
}
