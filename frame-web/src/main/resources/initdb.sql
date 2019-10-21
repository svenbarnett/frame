-- 框架用户表 --
create table `frame_user`
(
    `id`          bigint(20) not null auto_increment comment '主键',
    `loginid`     varchar(32)  default '' comment '登录名',
    `username`    varchar(32)  default '' comment '用户名',
    `password`    varchar(255) default '' comment '密码',
    `contacts`    varchar(32)  default null comment '联系人',
    `mobile`      varchar(11)  default '' comment '手机号',
    `gender`      tinyint(1)   default 0 comment '性别，0：保密 1：男 2：女',
    `email`       varchar(64)  default '' comment '邮箱',
    `status`      tinyint(255) default 1 comment '状态，0：禁用 1：启用',
    `create_time` datetime     default current_timestamp comment '创建时间',
    `update_time` datetime comment '更新时间',
    `delete_time` datetime comment '删除时间',
    `updater`     varchar(32)  default '' comment '操作用户',
    primary key (`id`) using btree
) engine = innodb
  default charset = utf8mb4 comment ='用户表';


-- 框架角色表 --
create table `frame_role`
(
    `id`         bigint(20)  not null primary key auto_increment comment '角色唯一主键',
    `role_name`   varchar(50) not null comment '角色名称',
    `status`      tinyint(1)  default 1 comment '角色状态，0：禁用 1：启用',
    `creator`     varchar(32) default '',
    `updater`     varchar(32) default '',
    `create_time` datetime    default current_timestamp comment '创建时间',
    `update_time` datetime comment '更新时间',
    `delete_time` datetime comment '删除时间'
) engine = innodb
  default charset = utf8mb4 comment ='角色表';


-- 框架权限表 --
create table `frame_permission`
(
    `id`              bigint(20)   not null primary key auto_increment comment '权限唯一主键',
    `permission_name` varchar(50)  not null comment '权限名称',
    `uri`             varchar(500) not null comment '资源',
    `pid`             bigint(20)  default null comment '父权限id',
    `type`            varchar(16) default 'button' comment '权限类型',
    `creator`         varchar(32) default '',
    `updater`         varchar(32) default '',
    `create_time`     datetime    default current_timestamp comment '创建时间',
    `update_time`     datetime comment '更新时间',
    `delete_time`     datetime comment '删除时间'
) engine = innodb
  default charset = utf8mb4;

-- 用户-角色表 --
create table frame_user_role
(
    `uid`         bigint(20) not null comment '用户id',
    `rid`         bigint(20) not null comment '角色id',
    `create_time` datetime default current_timestamp comment '创建时间',
    `update_time` datetime comment '更新时间',
    `delete_time` datetime comment '删除时间',
    key idx_rid (`rid`),
    key idx_uid (`uid`)
) engine = innodb
  default charset = utf8mb4 comment ='用户-角色表';


-- 角色-权限表 --
create table `frame_role_permission`
(
    `rid`         bigint(20) not null comment '角色id',
    `pid`         bigint(20) not null comment '权限id',
    `create_time` datetime default current_timestamp comment '创建时间',
    `update_time` datetime comment '更新时间',
    `delete_time` datetime comment '删除时间',
    key idx_rid (`rid`),
    key idx_pid (`pid`)
) engine = innodb
  default charset = utf8mb4 comment ='角色-权限表';

-- 框架部门表 --
create table `frame_dept`
(
    `id`          bigint(20)  not null primary key auto_increment comment '部门主键',
    `pid`         bigint(20)  not null comment '上一级部门id',
    `name`        varchar(50) not null comment '部门名称',
    `status`      tinyint(1)  not null default 1 comment '是否启用；1启用，0不启用',
    `ordernum`    integer(11)          default 0 comment '排序',
    `create_time` datetime             default current_timestamp comment '创建时间',
    `update_time` datetime comment '更新时间',
    `delete_time` datetime comment '删除时间',
    key idx_id (`id`),
    key idx_pid (`pid`)
) engine = innodb
  default charset = utf8mb4 comment ='框架部门表';


-- 框架模块表 --
create table `frame_module`
(
    `id`          bigint(20)  not null primary key auto_increment comment '模块主键',
    `pid`         bigint(20)  not null comment '上一级模块id',
    `name`        varchar(50) not null comment '名称',
    `icon`        varchar(50) comment '图标',
    `url`         varchar(255) comment '对应的url',
    `percode`     varchar(50) comment '权限',
    `ordernum`    integer(11)          default 0 comment '排序',
    `status`      tinyint(1)  not null default 1 comment '是否启用；1启用，0不启用',
    `create_time` datetime             default current_timestamp comment '创建时间',
    `update_time` datetime comment '更新时间',
    `delete_time` datetime comment '删除时间',
    key idx_pid (`pid`)
) engine = innodb
  default charset = utf8mb4 comment ='框架模块表';

