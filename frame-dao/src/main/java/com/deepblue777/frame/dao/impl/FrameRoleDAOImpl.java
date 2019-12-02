package com.deepblue777.frame.dao.impl;

import com.deepblue777.frame.dao.BaseDAO;
import com.deepblue777.frame.dao.FrameRoleDAO;
import com.deepblue777.frame.domain.FrameRole;
import com.deepblue777.frame.mapper.FrameRoleMapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * [简单描述]
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2019-12-02 20:14]
 */
@Repository
public class FrameRoleDAOImpl extends BaseDAO implements FrameRoleDAO {

    @Resource
    private FrameRoleMapper frameRoleMapper;

    @Override
    public List<FrameRole> findAll() {
        Example example = new Example(FrameRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIsNull("deleteTime");
        return frameRoleMapper.selectByExample(example);
    }

    @Override
    public List<FrameRole> findAllByExample(Example example) {
        return null;
    }
}
