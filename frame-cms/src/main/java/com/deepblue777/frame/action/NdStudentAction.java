package com.deepblue777.frame.action;

import com.alibaba.fastjson.JSON;
import com.deepblue777.frame.common.BaseResponse;
import com.deepblue777.frame.dao.FrameAttachinfoDAO;
import com.deepblue777.frame.domain.FrameAttachinfo;
import com.deepblue777.frame.domain.NdStudent;
import com.deepblue777.frame.service.NdStudentService;
import com.deepblue777.frame.utils.ExcelUtil;
import com.deepblue777.frame.vo.TableVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    private FrameAttachinfoDAO attachinfoDAO;

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
    public ModelAndView edit(@RequestParam("id") String id) {
        ModelAndView mv = new ModelAndView("nd/student/edit");
        NdStudent student = ndStudentService.getStudentByID(id);
        student.setIdcard(null);
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
        for (int i = 0; i < tables.size(); i++) {
            NdStudent student = tables.get(i);
            student.setIdcard(idMask(student.getIdcard(), 5, 4));
        }
        int count = ndStudentService.getTablesCount(map);
        return JSON.toJSONString(new TableVO<>(count, tables));
    }

    /**
     * 用户身份证号码的打码隐藏加星号加*
     * <p>18位和非18位身份证处理均可成功处理</p>
     * <p>参数异常直接返回null</p>
     *
     * @param idCardNum 身份证号码
     * @param front     需要显示前几位
     * @param end       需要显示末几位
     * @return 处理完成的身份证
     */
    private String idMask(String idCardNum, int front, int end) {
        //身份证不能为空
        if (StringUtils.isBlank(idCardNum)) {
            return null;
        }
        //需要截取的长度不能大于身份证号长度
        if ((front + end) > idCardNum.length()) {
            return null;
        }
        //需要截取的不能小于0
        if (front < 0 || end < 0) {
            return null;
        }
        //计算*的数量
        int asteriskCount = idCardNum.length() - (front + end);
        StringBuffer asteriskStr = new StringBuffer();
        for (int i = 0; i < asteriskCount; i++) {
            asteriskStr.append("*");
        }
        String regex = "(\\w{" + String.valueOf(front) + "})(\\w+)(\\w{" + String.valueOf(end) + "})";
        return idCardNum.replaceAll(regex, "$1" + asteriskStr + "$3");
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
            String id = String.valueOf(split[i]);
            ndStudentService.delete(id);
            count = count + 1;
        }
        return JSON.toJSONString(new BaseResponse<>(0, "删除" + count + "条成功！"));
    }

    @PostMapping("/addFromExcel")
    public String addFromExcel(@RequestParam("attachguid") String attachguid) {
        FrameAttachinfo attachinfo = attachinfoDAO.findById(attachguid);
        if (attachinfo == null) {
            return JSON.toJSONString(new BaseResponse<>(1, "附件不存在！"));
        }
        new ExcelUtil(attachinfo.getFilepath(), (sheet, row, data) -> {
            if (row >= 1) {
                NdStudent student = new NdStudent();
                student.setId(UUID.randomUUID().toString());
                student.setCreateTime(new Date());
                student.setNumber(new BigDecimal(String.valueOf(data[1])).toPlainString());
                student.setName(String.valueOf(data[2]));
                student.setIdcard(String.valueOf(data[3]));
                student.setYear(String.valueOf(data[4]));
                student.setMajor(String.valueOf(data[5]));
                student.setClassname(String.valueOf(data[6]));
                student.setGender("男".equals(data[7].toString()) ? 1 : 2);
                student.setEmail(String.valueOf(data[8]));
                student.setStatus(1);
                ndStudentService.add(student);
            }
        }).parse();
        return JSON.toJSONString(new BaseResponse<>(0, "导入数据成功！"));
    }

}
