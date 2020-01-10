package com.deepblue777.frame.dao.impl;

import com.deepblue777.frame.dao.FrameAttachinfoDAO;
import com.deepblue777.frame.domain.FrameAttachinfo;
import com.deepblue777.frame.mapper.FrameAttachinfoMapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * [简单描述]
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2020-01-09 20:36]
 */

@Repository
public class FrameAttachinfoDAOImpl implements FrameAttachinfoDAO {


    @Resource
    private FrameAttachinfoMapper attachinfoMapper;

    @Override
    public void insert(FrameAttachinfo attachinfo) {
        attachinfoMapper.insert(attachinfo);
    }

    @Override
    public void update(FrameAttachinfo attachinfo) {
        attachinfoMapper.updateByPrimaryKeySelective(attachinfo);
    }

    @Override
    public void delete(FrameAttachinfo attachinfo) {
        attachinfo.setDeleteTime(new Date());
        attachinfoMapper.updateByPrimaryKeySelective(attachinfo);
    }

    @Override
    public FrameAttachinfo findById(String attachguid) {
        Example example = new Example(FrameAttachinfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", attachguid);
        return attachinfoMapper.selectOneByExample(example);
    }

    @Override
    public FrameAttachinfo findByClientId(String clientid) {
        return attachinfoMapper.selectByPrimaryKey(clientid);
    }


    @Override
    public List<FrameAttachinfo> findListByClientId(String clientid) {
        Example example = new Example(FrameAttachinfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIsNull("deleteTime");
        criteria.andEqualTo("clientid", clientid);
        return attachinfoMapper.selectByExample(example);
    }

    @Override
    public List<FrameAttachinfo> findListByClientIdAndClientinfo(String clientid, String clientinfo) {
        Example example = new Example(FrameAttachinfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIsNull("deleteTime");
        criteria.andEqualTo("clientid", clientid);
        criteria.andEqualTo("clientinfo", clientinfo);
        return attachinfoMapper.selectByExample(example);
    }

    @Override
    public List<FrameAttachinfo> findListByClientIdAndInfoAndTag(String clientid, String clientinfo, String clienttag) {
        Example example = new Example(FrameAttachinfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIsNull("deleteTime");
        criteria.andEqualTo("clientid", clientid);
        criteria.andEqualTo("clientinfo", clientinfo);
        criteria.andEqualTo("clienttag", clienttag);
        return attachinfoMapper.selectByExample(example);
    }
}
