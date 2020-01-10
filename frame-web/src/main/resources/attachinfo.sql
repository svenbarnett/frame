-- 框架附件表 --
use `frame`;
create table `frame_attachinfo`
(
    `id`          varchar(50) not null comment '主键',
    `attachname`     varchar(32)  default '' comment '附件名',
    `filepath`    varchar(500)  default '' comment '附件文件位置',
    `filetype`    varchar(32)  default '' comment '附件后缀',
    `contenttype`    varchar(255) default '' comment '附件类型',
    `filelength`     bigint(20)  default 0 comment '附件大小',
    `clientid`      varchar(50) default '' comment '客户端id',
    `clientinfo`      varchar(50)   default '' comment '附件信息',
    `clienttag`       varchar(50)  default '' comment '附件标签',
    `create_time` datetime     default current_timestamp comment '创建时间',
    `update_time` datetime comment '更新时间',
    `delete_time` datetime comment '删除时间',
    `updater`     varchar(32)  default '' comment '操作用户',
    primary key (`id`) using btree
) engine = innodb
  default charset = utf8mb4 comment ='附件表';