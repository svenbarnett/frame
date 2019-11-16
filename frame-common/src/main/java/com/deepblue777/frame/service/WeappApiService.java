package com.deepblue777.frame.service;

import java.util.Map;

/**
 * 供api调用
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2019-11-16 15:13]
 */

public interface WeappApiService {
    Map<String,Object> getExaminfoByNumber(String number,String year,Integer term);
    boolean checkStuValid(String name,String number,String idnumber);
}
