package com.deepblue777.frame.action;

import com.alibaba.fastjson.JSON;
import com.deepblue777.frame.common.BaseResponse;
import com.deepblue777.frame.service.WeappApiService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 小程序使用的api
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2019-11-16 15:03]
 */

@RestController
@RequestMapping("/api/v1")
public class WeappApiAction {

    @Autowired
    private WeappApiService weappApiService;

    @PostMapping("/examinfo")
    @ResponseBody
    public String getExaminfo(@RequestBody Map<String,String> conditionMap){
        String name = conditionMap.get("name");
        String number = conditionMap.get("number");
        String idnumber = conditionMap.get("idnumber");
        String year = conditionMap.get("year");
        String term = conditionMap.get("term");
        if (!StringUtils.isNotBlank(name)){
            return JSON.toJSONString(new BaseResponse<>(1,"缺少name字段条件"));
        }
        if (!StringUtils.isNotBlank(number)){
            return JSON.toJSONString(new BaseResponse<>(1,"缺少number字段条件"));
        }
        if (!StringUtils.isNotBlank(idnumber)){
            return JSON.toJSONString(new BaseResponse<>(1,"缺少idnumber字段条件"));
        }
        if (!StringUtils.isNotBlank(year)){
            return JSON.toJSONString(new BaseResponse<>(1,"缺少year字段条件"));
        }
        if (!StringUtils.isNotBlank(term)){
            return JSON.toJSONString(new BaseResponse<>(1,"缺少term字段条件"));
        }
        if (!weappApiService.checkStuValid(name,number,idnumber)){
            return JSON.toJSONString(new BaseResponse<>(1,"找不到对应的学生信息"));
        }
        Map<String, Object> examinfo= weappApiService.getExaminfoByNumber(number,year,Integer.valueOf(term));

        if (examinfo.get("courses")==null){
            return JSON.toJSONString(new BaseResponse<>(1,"不存在课程信息"));
        }

        return JSON.toJSONString(new BaseResponse<>(0,"成功！",examinfo));
    }
}
