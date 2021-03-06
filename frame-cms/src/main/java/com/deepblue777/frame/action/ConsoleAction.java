package com.deepblue777.frame.action;

import com.deepblue777.frame.domain.FrameUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 控制台控制器
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/15 12:29
 * @since 1.0
 */
@RestController
public class ConsoleAction {

  @GetMapping("/")
  public ModelAndView index0() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:/frame/index");
    return mv;
  }


  @GetMapping("/frame")
  public ModelAndView index1() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:/frame/index");
    return mv;
  }

  @GetMapping("/frame/index")
  public ModelAndView index() {
    Subject subject = SecurityUtils.getSubject();
    FrameUser user = (FrameUser) subject.getPrincipals().getPrimaryPrincipal();
    ModelAndView mv = new ModelAndView();
    mv.addObject("user", user);
    mv.setViewName("index");
    return mv;
  }

  @GetMapping("/frame/console")
  public ModelAndView console() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("frame/console");
    return mv;
  }

  @GetMapping("/frame/unauthorized")
  public ModelAndView unauthorized() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("unauthorized");
    return mv;
  }
}
