package com.deepblue777.frame.service.impl;

import com.deepblue777.frame.common.BaseResponse;
import com.deepblue777.frame.dao.FrameModuleDAO;
import com.deepblue777.frame.domain.FrameModule;
import com.deepblue777.frame.service.FrameModuleService;
import com.deepblue777.frame.vo.TableVO;
import com.deepblue777.frame.vo.TreeListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * 框架模块实现类
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/21 14:32
 * @since 1.0
 */
@Service
public class FrameModuleServiceImpl implements FrameModuleService {

  @Autowired
  private FrameModuleDAO frameModuleDAO;

  @Override
  public BaseResponse getDtreeList() {
    List<FrameModule> modules = frameModuleDAO.findAll();
    List<TreeListVO> trees = new LinkedList<TreeListVO>();
    for (int i = 0; i < modules.size(); i++) {
      FrameModule module = modules.get(i);
      TreeListVO treeListVO = new TreeListVO(module.getId(), module.getName(), "", module.getPid(), module.getIcon());
      trees.add(treeListVO);
    }
    return new BaseResponse(trees);
  }

  @Override
  public TableVO<List<FrameModule>> getTableList(int pid, int page, int limit) {
    List<FrameModule> modules;
    int count;
    if (pid == 0) {
      modules = frameModuleDAO.findList(page, limit);
      count = frameModuleDAO.findCount();
    } else {
      modules = frameModuleDAO.findListByPid(pid, page, limit);
      count = frameModuleDAO.findCountByPid(pid);
    }
    return new TableVO<>(0, "操作成功！", count, modules);
  }

  @Override
  public FrameModule findByID(int id) {
    return frameModuleDAO.findByID(id);
  }
}
