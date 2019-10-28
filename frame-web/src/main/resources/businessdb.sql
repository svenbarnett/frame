-- 学生表 --
drop table `nd_student`;
create table `nd_student`
(
    `id`          bigint(20)  not null auto_increment primary key comment '主键',
    `name`        varchar(32) not null comment '学生姓名',
    `year`        varchar(32)  default '' comment '学生年级',
    `major`       varchar(32)  default '' comment '学生专业',
    `classname`   varchar(32)  default '' comment '班级',
    `number`      varchar(32) not null comment '学号',
    `idcard`      varchar(18) not null comment '身份证',
    `gender`      tinyint(1)   default 0 comment '性别，0：保密 1：男 2：女',
    `email`       varchar(64)  default '' comment '邮箱',
    `status`      tinyint(255) default 1 comment '状态，0：禁用 1：启用',
    `create_time` datetime     default current_timestamp comment '创建时间',
    `update_time` datetime comment '更新时间',
    `delete_time` datetime comment '删除时间',
    unique key (`number`),
    unique key (`idcard`)
) engine = innodb
  default charset = utf8mb4 comment ='学生表';

