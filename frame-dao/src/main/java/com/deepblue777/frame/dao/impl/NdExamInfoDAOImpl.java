package com.deepblue777.frame.dao.impl;

import com.deepblue777.frame.dao.BaseDAO;
import com.deepblue777.frame.dao.NdExamInfoDAO;
import com.deepblue777.frame.domain.NdExamInfo;
import com.deepblue777.frame.mapper.NdExamInfoMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * [简单描述]
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2019-11-04 11:33]
 */

@Repository
public class NdExamInfoDAOImpl extends BaseDAO implements NdExamInfoDAO {


    @Resource
    private NdExamInfoMapper ndExamInfoMapper;


    @Override
    public void add(NdExamInfo ndExamInfo) {
        ndExamInfoMapper.insert(ndExamInfo);
    }

    @Override
    public List<NdExamInfo> findAll(Map<String, Object> map, int page, int limit) {
        Example example = this.combineExample(map, NdExamInfo.class);
        int start = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(start, limit);
        return ndExamInfoMapper.selectByExampleAndRowBounds(example, rowBounds);
    }

    @Override
    public int findAllCount(Map<String, Object> map) {
        Example example = this.combineExample(map, NdExamInfo.class);
        return ndExamInfoMapper.selectCountByExample(example);
    }

    @Override
    public NdExamInfo findById(int id) {
        return ndExamInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public NdExamInfoMapper findByMap(Map<String, Object> map) {
        return null;
    }

    @Override
    public void update(NdExamInfo ndExamInfo) {
        ndExamInfoMapper.updateByPrimaryKeySelective(ndExamInfo);
    }

    @Override
    public void deleteById(int id) {
        deleteById(id,true);

    }

    @Override
    public void deleteById(int id, boolean softdelete) {
        if (softdelete) {
            NdExamInfo info = findById(id);
            info.setDeleteTime(new Date());
            ndExamInfoMapper.updateByPrimaryKey(info);
        } else {
            ndExamInfoMapper.deleteByPrimaryKey(id);
        }
    }
}
