-- 框架用户表 --
CREATE TABLE frame_user(
  uid integer(11) not null PRIMARY KEY auto_increment comment '用户唯一主键',
  username varchar(50) comment '用户昵称',
  loginid varchar(50) not null comment '用户登录名',
  password varchar(255) not null  comment '用户密码',
  gender integer(1) comment '用户性别',
  status integer(1) default 1 comment '用户是否启用,1启用,0不启用',
  create_time datetime comment '创建时间',
  update_time datetime comment '更新时间',
  delete_time datetime comment '删除时间'
)engine = InnoDB default charset = utf8;


-- 框架角色表 --
create table frame_role(
    rid integer(11) not null  primary key auto_increment comment '角色唯一主键',
    role_name varchar(50) not null comment '角色名称',
    create_time datetime comment '创建时间',
    update_time datetime comment '更新时间',
    delete_time datetime comment '删除时间'
)engine = InnoDB default charset = utf8;

-- 框架权限表 --
create table frame_permission(
    pid integer(11) not null primary key auto_increment comment '权限唯一主键',
    permission_name varchar(50) not null comment '权限名称',
    create_time datetime comment '创建时间',
    update_time datetime comment '更新时间',
    delete_time datetime comment '删除时间'
)engine = InnoDB default charset = utf8;

-- 用户角色表 --
create table frame_user_role(
  uid integer(11) not null comment '用户id',
  rid integer(11) not null comment '角色id',
  create_time datetime comment '创建时间',
  update_time datetime comment '更新时间',
  delete_time datetime comment '删除时间'
)engine = InnoDB default charset = utf8;

-- 角色权限表 --
create table frame_role_permission(
    rid integer(11) not null comment '角色id',
    pid integer(11) not null comment '权限id',
    url varchar(500) not null  comment '资源',
    create_time datetime comment '创建时间',
    update_time datetime comment '更新时间',
    delete_time datetime comment '删除时间'
)engine = InnoDB default charset = utf8;