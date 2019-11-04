package com.deepblue777.frame.dao;

import tk.mybatis.mapper.entity.Example;

import java.util.Map;

/**
 * [简单描述]
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2019-11-04 11:39]
 */
public abstract class BaseDAO{
    protected Example combineExample(Map<String, Object> map,Class classtype) {
        Example example = new Example(classtype);
        Example.Criteria criteria = example.createCriteria();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String mapKey = entry.getKey();
            Object mapValue = entry.getValue();
            criteria.andEqualTo(mapKey, mapValue);
        }
        criteria.andIsNull("deleteTime");
        example.orderBy("createTime").asc();
        return example;
    }
}
