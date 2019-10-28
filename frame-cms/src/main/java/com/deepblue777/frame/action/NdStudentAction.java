package com.deepblue777.frame.action;

import com.alibaba.fastjson.JSON;
import com.deepblue777.frame.common.BaseResponse;
import com.deepblue777.frame.domain.NdStudent;
import com.deepblue777.frame.service.NdStudentService;
import com.deepblue777.frame.vo.TableVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 学生视图控制器
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/28 11:21
 * @since 1.0
 */
@RestController
@RequestMapping("/nd/student")
public class NdStudentAction {

  @Autowired
  private NdStudentService ndStudentService;

  @GetMapping(value = "/list")
  public ModelAndView list() {
    return new ModelAndView("nd/student/list");
  }

  @GetMapping(value = "/add")
  public ModelAndView add() {
    return new ModelAndView("nd/student/add");
  }

  @GetMapping(value = "/edit")
  public ModelAndView edit(@RequestParam("id") Integer id) {
    ModelAndView mv = new ModelAndView("nd/student/edit");
    NdStudent student = ndStudentService.getStudentByID(id);
    mv.addObject("student", student);
    return mv;
  }

  @GetMapping(value = "/detail")
  public ModelAndView detail() {
    return new ModelAndView("nd/student/detail");
  }

  @PostMapping("/table")
  public String table(@RequestBody Map<String, Object> map) {
    int page = 1;
    int limit = 15;
    if (StringUtils.isNotBlank(map.get("page").toString())) {
      page = Integer.valueOf(map.get("page").toString());
      map.remove("page");
    }

    if (StringUtils.isNotBlank(map.get("limit").toString())) {
      limit = Integer.valueOf(map.get("limit").toString());
      map.remove("limit");
    }

    List<NdStudent> tables = ndStudentService.getTables(map, page, limit);
    int count = ndStudentService.getTablesCount(map);
    return JSON.toJSONString(new TableVO<>(count, tables));
  }

  @PostMapping("/doadd")
  public String doadd(@RequestBody NdStudent student) {
    student.setCreateTime(new Date());
    ndStudentService.add(student);
    return JSON.toJSONString(new BaseResponse<>(0, "新增成功！"));
  }

  @PostMapping("/doedit")
  public String doedit(@RequestBody NdStudent student) {
    student.setUpdateTime(new Date());
    ndStudentService.update(student);
    return JSON.toJSONString(new BaseResponse<>(0, "更新成功！"));
  }

  @PostMapping("/delete")
  public String delete(@RequestParam("id") String ids) {
    String[] split = ids.split(";");
    int count = 0;
    for (int i = 0; i < split.length; i++) {
      int id = Integer.valueOf(split[i]);
      ndStudentService.delete(id);
      count = count + 1;
    }
    return JSON.toJSONString(new BaseResponse<>(0, "删除" + count + "条成功！"));
  }

}
