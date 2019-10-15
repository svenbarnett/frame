package com.deepblue777.frame.action;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

  @GetMapping("/user/list")
  public ModelAndView list() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("frame/user/list");
    return mv;
  }
  @GetMapping("/user/add")
  public ModelAndView add() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("frame/user/add");
    return mv;
  }
}
