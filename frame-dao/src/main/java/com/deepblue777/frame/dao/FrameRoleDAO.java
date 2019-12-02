package com.deepblue777.frame.dao;

import com.deepblue777.frame.domain.FrameRole;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * [简单描述]
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2019-12-02 20:09]
 */
public interface FrameRoleDAO {

    List<FrameRole> findAll();

    List<FrameRole> findAllByExample(Example example);
}
