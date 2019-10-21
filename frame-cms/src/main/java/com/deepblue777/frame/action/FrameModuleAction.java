package com.deepblue777.frame.action;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 框架模块视图控制器
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/21 10:43
 * @since 1.0
 */
@RestController
@RequestMapping("/frame/module")
public class FrameModuleAction {

  @GetMapping("/list")
  public ModelAndView list() {
    ModelAndView mv = new ModelAndView("frame/module/list");
    return mv;
  }

  @GetMapping("/add")
  public ModelAndView add() {
    ModelAndView mv = new ModelAndView("frame/module/add");
    return mv;
  }

  @GetMapping("/edit")
  public ModelAndView edit() {
    ModelAndView mv = new ModelAndView("frame/module/edit");
    return mv;
  }
}
