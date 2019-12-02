package com.deepblue777.frame.service.impl;

import com.deepblue777.frame.dao.FrameRoleDAO;
import com.deepblue777.frame.domain.FrameRole;
import com.deepblue777.frame.service.FrameRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * [简单描述]
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2019-12-02 20:22]
 */
@Service
public class FrameRoleServiceImpl implements FrameRoleService {

    @Autowired
    private FrameRoleDAO frameRoleDAO;

    @Override
    public List<FrameRole> findAll() {
        return frameRoleDAO.findAll();
    }
}
