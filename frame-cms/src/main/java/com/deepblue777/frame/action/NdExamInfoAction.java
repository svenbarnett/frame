package com.deepblue777.frame.action;

import com.alibaba.fastjson.JSON;
import com.deepblue777.frame.common.BaseResponse;
import com.deepblue777.frame.dao.FrameAttachinfoDAO;
import com.deepblue777.frame.domain.FrameAttachinfo;
import com.deepblue777.frame.domain.NdExamCourse;
import com.deepblue777.frame.domain.NdExamInfo;
import com.deepblue777.frame.service.NdExamCourseService;
import com.deepblue777.frame.service.NdExamInfoService;
import com.deepblue777.frame.utils.ExcelUtil;
import com.deepblue777.frame.vo.TableVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private NdExamCourseService courseService;

    @Autowired
    private FrameAttachinfoDAO attachinfoDAO;

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
    public ModelAndView edit(@RequestParam("id") String infoId) {
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
        info.setAllscore(Float.valueOf(map.get("allscore").toString()));
        info.setGetscore(Float.valueOf(map.get("getscore").toString()));
        info.setRank(String.valueOf(map.get("rank").toString()));
        ndExamInfoService.add(info);
        return JSON.toJSONString(new BaseResponse<>(0, "新增成功！"));
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") String ids) {
        String[] split = ids.split(";");
        int count = 0;
        for (int i = 0; i < split.length; i++) {
            String id = split[i];
            ndExamInfoService.delete(id);
            count = count + 1;
        }
        return JSON.toJSONString(new BaseResponse<>(0, "删除" + count + "条成功！"));
    }

    @PostMapping("/doedit")
    public String doedit(@RequestBody Map<String, Object> map) {
        NdExamInfo info = new NdExamInfo();
        info.setId(map.get("id").toString());
        info.setCreateTime(new Date());
        info.setGpa(Float.valueOf(map.get("gpa").toString()));
        info.setStudentName(map.get("number").toString().split(";")[0]);
        info.setYear(map.get("year").toString());
        info.setTerm(Integer.valueOf(map.get("term").toString()));
        info.setStudentNumber(map.get("number").toString().split(";")[1]);
        info.setAllscore(Float.valueOf(map.get("allscore").toString()));
        info.setGetscore(Float.valueOf(map.get("getscore").toString()));
        info.setRank(String.valueOf(map.get("rank").toString()));
        ndExamInfoService.update(info);
        return JSON.toJSONString(new BaseResponse<>(0, "更新成功！"));
    }

    @PostMapping("/addFromExcel")
    public String addFromExcel(@RequestParam("attachguid") String attachguid) {
        FrameAttachinfo attachinfo = attachinfoDAO.findById(attachguid);
        if (attachinfo == null) {
            return JSON.toJSONString(new BaseResponse<>(1, "附件不存在！"));
        }

        List<String> courseNameList = new ArrayList<>(2);
        new ExcelUtil(attachinfo.getFilepath(), (sheet, row, data) -> {
            if (row == 0) {
                // 获取表头
                for (int i = 9; i < data.length; i++) {
                    courseNameList.add(String.valueOf(data[i]));
                }
            }


            if (row >= 1) {
                NdExamInfo info = new NdExamInfo();
                info.setId(UUID.randomUUID().toString());
                info.setCreateTime(new Date());
                info.setStudentNumber(new BigDecimal(String.valueOf(data[1])).toPlainString());
                info.setStudentName(String.valueOf(data[2]));
                info.setYear(String.valueOf(data[3]));
                info.setTerm("第一学期".equals(data[4].toString()) ? 1 : 2);
                info.setAllscore(Float.valueOf(data[5].toString()));
                info.setGetscore(Float.valueOf(data[6].toString()));
                info.setGpa(Float.valueOf(data[7].toString()));
                info.setRank((String.valueOf(data[8].toString())));
                ndExamInfoService.add(info);
                // 处理课程
                String reg = ".*[\\[|\\（|\\(|\\【]([0-9]\\d*\\.?\\d*)[\\]|\\）|\\)|\\】]";
                Pattern r = Pattern.compile(reg);
                for (int i = 0; i < courseNameList.size(); i++) {

                    NdExamCourse course = new NdExamCourse();
                    course.setId(UUID.randomUUID().toString());
                    course.setExamInfoId(info.getId());
                    course.setCreateTime(new Date());
                    Matcher m = r.matcher(courseNameList.get(i));
                    if (m.find()) {
                        course.setName(m.group(0));
                        course.setWeight(Float.valueOf(m.group(1)));
                    }

                    try {
                        if ("".equals(String.valueOf(data[9 + i].toString()))) {
                            continue;
                        }
                        course.setScore(String.valueOf(data[9 + i].toString()));
                        courseService.add(course);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).parse();
        return JSON.toJSONString(new BaseResponse<>(0, "导入数据成功！"));
    }
}
