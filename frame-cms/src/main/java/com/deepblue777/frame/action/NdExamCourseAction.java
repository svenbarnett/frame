package com.deepblue777.frame.action;

import com.alibaba.fastjson.JSON;
import com.deepblue777.frame.common.BaseResponse;
import com.deepblue777.frame.domain.NdExamCourse;
import com.deepblue777.frame.service.NdExamCourseService;
import com.deepblue777.frame.vo.TableVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 学生考试科目成绩
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2019-11-04 10:48]
 */

@RestController()
@RequestMapping("/nd/examcourse")
public class NdExamCourseAction {


    @Autowired
    private NdExamCourseService ndExamCourseService;

    @GetMapping("/list")
    public ModelAndView list() {
        return new ModelAndView("nd/examcourse/list");
    }

    @GetMapping("/add")
    public ModelAndView add() {
        return new ModelAndView("nd/examcourse/add");
    }

    @GetMapping("/edit")
    public ModelAndView edit() {
        return new ModelAndView("nd/examcourse/edit");
    }

    @GetMapping("/detail")
    public ModelAndView detail() {
        return new ModelAndView("nd/examcourse/detail");
    }

    @PostMapping("/table")
    public String table(@RequestBody Map<String, Object> map) {

        int page = 1;
        int limit = 15;

        if (StringUtils.isNotBlank(map.get("page").toString())) {
            page = Integer.valueOf(map.get("page").toString());
        }
        if (StringUtils.isNotBlank(map.get("limit").toString())) {
            limit = Integer.valueOf(map.get("limit").toString());
        }
        map.remove("page");
        map.remove("limit");
        List<NdExamCourse> tables = ndExamCourseService.getTables(map, page, limit);
        int count = ndExamCourseService.getTablesCount(map);
        return JSON.toJSONString(new TableVO<>(count, tables));
    }


    @PostMapping("/doadd")
    public String doadd(@RequestBody NdExamCourse course) {
        course.setCreateTime(new Date());
        ndExamCourseService.add(course);
        return JSON.toJSONString(new BaseResponse<>(0, "新增成功！"));
    }


}
