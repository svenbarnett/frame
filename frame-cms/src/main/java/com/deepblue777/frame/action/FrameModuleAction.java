package com.deepblue777.frame.action;

import com.alibaba.fastjson.JSON;
import com.deepblue777.frame.common.BaseResponse;
import com.deepblue777.frame.domain.FrameModule;
import com.deepblue777.frame.domain.FrameRole;
import com.deepblue777.frame.service.FrameModuleService;
import com.deepblue777.frame.service.FrameRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
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

  @Autowired
  private FrameRoleService frameRoleService;

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
  @GetMapping("/right")
  public ModelAndView right(@RequestParam("id") Integer id) {
    FrameModule module = frameModuleService.findByID(id);
    ModelAndView mv = new ModelAndView("frame/module/right");
    List<FrameRole> roles = frameRoleService.findAll();
    mv.addObject("roles",roles);
    mv.addObject("module", module);
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


  @PostMapping("doadd")
  public String doadd(@RequestBody FrameModule frameModule){
    System.out.println(frameModule);
    frameModule.setCreateTime(new Date());
    int count = frameModuleService.add(frameModule);
    return JSON.toJSONString(new BaseResponse<>(0,"新增成功！"));
  }

  @PostMapping("delete")
  public String dodelete(@RequestParam("id") String ids){
    String[] idArr = ids.split(";");
    int count = 0;
    for (int i = 0; i < idArr.length; i++) {
      frameModuleService.deleteById(Integer.valueOf(idArr[i]));
      count = count + 1;
    }
    return JSON.toJSONString(new BaseResponse<>(0,"删除"+count+"条成功！"));
  }
}
