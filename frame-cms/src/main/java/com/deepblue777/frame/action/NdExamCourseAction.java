package com.deepblue777.frame.action;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 学生考试科目成绩
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2019-11-04 10:48]
 */

@RestController()
@RequestMapping("/nd/examcourse")
public class NdExamCourseAction {

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
}
