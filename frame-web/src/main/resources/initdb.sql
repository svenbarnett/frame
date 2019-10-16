-- 框架用户表 --
CREATE TABLE frame_user(
  uid integer(11) not null PRIMARY KEY auto_increment comment '用户唯一主键',
  username varchar(50) comment '用户昵称',
  loginid varchar(50) not null unique comment '用户登录名',
  password varchar(255) not null  comment '用户密码',
  dept_id integer(11) not null comment '所属部门主键',
  gender integer(1) comment '用户性别',
  status integer(1) default 1 comment '用户是否启用,1启用,0不启用',
  create_time datetime comment '创建时间',
  update_time datetime comment '更新时间',
  delete_time datetime comment '删除时间',
  key idx_dept_id(dept_id)
)engine = InnoDB default charset = utf8;

insert into frame_user value (1,'管理员','admin','11111',0,1,1,null,null,null);
insert into frame_user value (2,'管理员2','demo','11111',0,1,1,null,null,null);
insert into frame_user value (3,'管理员3','admin3','11111',0,1,1,null,null,null);


-- 框架角色表 --
create table frame_role(
    rid integer(11) not null  primary key auto_increment comment '角色唯一主键',
    role_name varchar(50) not null comment '角色名称',
    create_time datetime comment '创建时间',
    update_time datetime comment '更新时间',
    delete_time datetime comment '删除时间'
)engine = InnoDB default charset = utf8;

insert into frame_role value (1, 'adminGroup',null,null,null);
insert into frame_role value (2, 'userGroup',null,null,null);


-- 框架权限表 --
create table frame_permission(
    pid integer(11) not null primary key auto_increment comment '权限唯一主键',
    permission_name varchar(50) not null comment '权限名称',
    url varchar(500) not null  comment '资源',
    create_time datetime comment '创建时间',
    update_time datetime comment '更新时间',
    delete_time datetime comment '删除时间'
)engine = InnoDB default charset = utf8;

insert into frame_permission value(1,'add','',null,null,null);
insert into frame_permission value(2,'delete','',null,null,null);
insert into frame_permission value(3,'edit','',null,null,null);
insert into frame_permission value(4,'query','',null,null,null);

-- 用户角色表 --
create table frame_user_role(
  uid integer(11) not null comment '用户id',
  rid integer(11) not null comment '角色id',
  create_time datetime comment '创建时间',
  update_time datetime comment '更新时间',
  delete_time datetime comment '删除时间',
  key idx_rid(rid),
  key idx_uid(uid)
)engine = InnoDB default charset = utf8;

insert into frame_user_role value (1,1,null,null,null);
insert into frame_user_role value (2,2,null,null,null);

-- 角色权限表 --
create table frame_permission_role(
    rid integer(11) not null comment '角色id',
    pid integer(11) not null comment '权限id',
    create_time datetime comment '创建时间',
    update_time datetime comment '更新时间',
    delete_time datetime comment '删除时间',
    key idx_rid(rid),
    key idx_pid(pid)
)engine = InnoDB default charset = utf8;

insert into frame_permission_role value (1,1,null,null,null);
insert into frame_permission_role value (1,2,null,null,null);
insert into frame_permission_role value (1,3,null,null,null);
insert into frame_permission_role value (1,4,null,null,null);
insert into frame_permission_role value (2,4,null,null,null);
insert into frame_permission_role value (2,1,null,null,null);

-- 框架部门表 --
create table frame_dept(
    id integer(11) not null primary key auto_increment comment '部门主键',
    pid integer(11) not null comment '上一级部门id',
    name varchar(50) not null comment '部门名称',
    status integer(1) not null default 1 comment '是否启用；1启用，0不启用',
    ordernum integer(11) default 0 comment '排序',
    create_time datetime comment '创建时间',
    update_time datetime comment '更新时间',
    delete_time datetime comment '删除时间',
    key idx_id(id),
    key idx_pid(pid)
)engine = InnoDB default charset =utf8;

insert into frame_dept value (0,0,'系统管理部',1,0,null,null,null);

-- 框架菜单表 --
create table frame_module(
    id integer(11) not null primary key auto_increment comment '模块主键',
    pid integer(11) not null comment '上一级模块id',
    name varchar(50) not null comment '名称',
    icon varchar(50) comment '图标',
    url varchar(255) comment '对应的url',
    percode varchar(50) comment '权限',
    ordernum integer(11) default 0 comment '排序',
    status integer(1) not null default 1 comment '是否启用；1启用，0不启用',
    create_time datetime comment '创建时间',
    update_time datetime comment '更新时间',
    delete_time datetime comment '删除时间',
    key idx_id(id),
    key idx_pid(pid)
)engine = InnoDB default charset =utf8;

insert into frame_module value (1,0,'系统','','',null,0,1,null,null,null)
