package com.deepblue777.frame.service;

import com.deepblue777.frame.common.BaseResponse;
import com.deepblue777.frame.domain.FrameModule;
import com.deepblue777.frame.vo.TableVO;

import java.util.List;

/**
 * 框架模块业务接口类
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/21 14:30
 * @since 1.0
 */
public interface FrameModuleService {

  BaseResponse getDtreeList();

  TableVO<List<FrameModule>> getTableList(int pid, int page, int limit);
  FrameModule findByID(int id);

  int add(FrameModule frameModule);

  void deleteById(int id);
}
