package com.deepblue777.frame.dao;

import com.deepblue777.frame.domain.FrameAttachinfo;

import java.util.List;

/**
 * [简单描述]
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2020-01-09 20:31]
 */
public interface FrameAttachinfoDAO {

    void insert(FrameAttachinfo attachinfo);

    void update(FrameAttachinfo attachinfo);

    void delete(FrameAttachinfo attachinfo);

    FrameAttachinfo findById(String attachguid);

    FrameAttachinfo findByClientId(String clientid);

    List<FrameAttachinfo> findListByClientId(String clientid);

    List<FrameAttachinfo> findListByClientIdAndClientinfo(String clientid, String clientinfo);

    List<FrameAttachinfo> findListByClientIdAndInfoAndTag(String clientid, String clientinfo, String clienttag);
}
