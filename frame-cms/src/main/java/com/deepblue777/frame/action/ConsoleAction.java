package com.deepblue777.frame.action;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    mv.setViewName("redirect:index");
    return mv;
  }

  @GetMapping("/index")
  public ModelAndView index() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("index");
    return mv;
  }

  @GetMapping("/frame/console")
  public ModelAndView console() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("frame/console");
    return mv;
  }
}
