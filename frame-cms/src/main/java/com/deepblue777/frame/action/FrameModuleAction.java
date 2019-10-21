package com.deepblue777.frame.action;

import com.alibaba.fastjson.JSON;
import com.deepblue777.frame.domain.FrameModule;
import com.deepblue777.frame.service.FrameModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

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

  @Autowired
  private FrameModuleService frameModuleService;

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
  public ModelAndView edit(@RequestParam("id") Integer id) {
    ModelAndView mv = new ModelAndView("frame/module/edit");
    FrameModule module = frameModuleService.findByID(id);
    mv.addObject("module", module);
    return mv;
  }

  @PostMapping("/dtree")
  public String dtree() {
    return JSON.toJSONString(frameModuleService.getDtreeList());
  }

  @PostMapping("/table")
  public String table(@RequestBody Map map) {
    Integer pid = 0;
    int page = 0;
    int limit = 20;

    if (map.get("pid") != null) {
      pid = Integer.valueOf(map.get("pid").toString());
    }
    if (map.get("page") != null) {
      page = Integer.valueOf(map.get("page").toString());
    }
    if (map.get("limit") != null) {
      limit = Integer.valueOf(map.get("limit").toString());
    }
    return JSON.toJSONString(frameModuleService.getTableList(pid, page, limit));
  }
}
