package com.deepblue777.frame.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "frame_attachinfo")
@Data
public class FrameAttachinfo {
  @Id
  private String id;
  private String attachname;
  private String filepath;
  private String filetype;
  private String contenttype;
  private Long filelength;
  private String clientid;
  private String clientinfo;
  private String clienttag;
  private Date createTime;
  private Date updateTime;
  private Date deleteTime;
  private String updater;

}
