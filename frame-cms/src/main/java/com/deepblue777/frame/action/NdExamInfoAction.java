package com.deepblue777.frame.action;

import com.alibaba.fastjson.JSON;
import com.deepblue777.frame.common.BaseResponse;
import com.deepblue777.frame.domain.NdExamInfo;
import com.deepblue777.frame.service.NdExamInfoService;
import com.deepblue777.frame.vo.TableVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 学生考试成绩
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2019-11-04 10:48]
 */

@RestController()
@RequestMapping("/nd/examinfo")
public class NdExamInfoAction {

    @Autowired
    private NdExamInfoService ndExamInfoService;

    @GetMapping("/list")
    public ModelAndView list() {
        return new ModelAndView("nd/examinfo/list");
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("nd/examinfo/add");
        List<Map<String, String>> codes = ndExamInfoService.getSutdentsCode();
        modelAndView.addObject("studentcodes", codes);
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam("id") int infoId) {
        ModelAndView mv = new ModelAndView("nd/examinfo/edit");
        NdExamInfo info = ndExamInfoService.findById(infoId);
        List<Map<String, String>> codes = ndExamInfoService.getSutdentsCode();
        mv.addObject("studentcodes", codes);
        mv.addObject("info", info);
        return mv;
    }

    @GetMapping("/detail")
    public ModelAndView detail() {
        return new ModelAndView("nd/examinfo/detail");
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
        List<NdExamInfo> infos = ndExamInfoService.getTables(map, page, limit);
        int total = ndExamInfoService.getTablesCount(map);
        return JSON.toJSONString(new TableVO<>(total, infos));
    }

    @PostMapping("/doadd")
    public String doadd(@RequestBody Map<String, Object> map) {
        NdExamInfo info = new NdExamInfo();
        info.setCreateTime(new Date());
        info.setGpa(Float.valueOf(map.get("gpa").toString()));
        info.setStudentName(map.get("number").toString().split(";")[0]);
        info.setYear(map.get("year").toString());
        info.setTerm(Integer.valueOf(map.get("term").toString()));
        info.setStudentNumber(map.get("number").toString().split(";")[1]);
        ndExamInfoService.add(info);
        return JSON.toJSONString(new BaseResponse<>(0, "新增成功！"));
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") String ids) {
        String[] split = ids.split(";");
        int count = 0;
        for (int i = 0; i < split.length; i++) {
            int id = Integer.valueOf(split[i]);
            ndExamInfoService.delete(id);
            count = count + 1;
        }
        return JSON.toJSONString(new BaseResponse<>(0, "删除" + count + "条成功！"));
    }

    @PostMapping("/doedit")
    public String doedit(@RequestBody Map<String ,Object> map) {
        NdExamInfo info = new NdExamInfo();
        info.setId(Integer.valueOf(map.get("id").toString()));
        info.setCreateTime(new Date());
        info.setGpa(Float.valueOf(map.get("gpa").toString()));
        info.setStudentName(map.get("number").toString().split(";")[0]);
        info.setYear(map.get("year").toString());
        info.setTerm(Integer.valueOf(map.get("term").toString()));
        info.setStudentNumber(map.get("number").toString().split(";")[1]);
        ndExamInfoService.update(info);
        return JSON.toJSONString(new BaseResponse<>(0, "更新成功！"));
    }
}
