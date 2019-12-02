package com.deepblue777.frame.dao;

import com.deepblue777.frame.domain.FrameModule;

import java.util.List;

/**
 * 框架模块dao
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/21 14:21
 * @since 1.0
 */
public interface FrameModuleDAO {

  List<FrameModule> findAll();

  List<FrameModule> findListByPid(int pid);

  List<FrameModule> findListByPid(int pid, int page, int limit);

  List<FrameModule> findList(int page, int limit);

  int findCountByPid(int pid);

  int findCount();

  FrameModule findByID(int id);

  int add(FrameModule frameModule);

  void deleteById(int id,boolean softdelete);

  void deleteById(int id);
}
