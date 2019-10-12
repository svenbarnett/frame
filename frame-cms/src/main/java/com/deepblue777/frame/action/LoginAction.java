package com.deepblue777.frame.action;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录相关控制器
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/11 17:25
 * @since 1.0
 */
@RestController
public class LoginAction {

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView login(Model model) {
    model.addAttribute("userList","user");
    model.addAttribute("title","用户管理");
    return new ModelAndView("index","userModel",model);
  }

  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public ModelAndView index(Model model) {
    model.addAttribute("userList","user");
    model.addAttribute("title","用户管理");
    return new ModelAndView("index","userModel",model);
  }
}
