-- 模拟数据 --
insert into `frame_user`(`id`, `loginid`, `username`, `password`)
values (1, 'admin', '超级管理员', md5('11111'));
insert into `frame_user`(`id`, `loginid`, `username`, `password`)
values (2, 'guest', '普通用户', md5('11111'));


insert into `frame_role`(`id`, `role_name`, `status`)
values (1, 'admin', 1);
insert into `frame_role`(`id`, `role_name`, `status`)
values (2, '普通用户', 1);

delete from frame_permission where 1=1;

insert into `frame_permission`(`id`, `permission_name`, `uri`, `pid`, `type`)
values (1, '用户管理', '', null, '');
insert into `frame_permission`(`id`, `permission_name`, `uri`, `pid`, `type`)
values (2, '角色管理', '', null, '');
insert into `frame_permission`(`id`, `permission_name`, `uri`, `pid`, `type`)
values (3, '查询（分页）', 'role/page', 2, null);
insert into `frame_permission`(`id`, `permission_name`, `uri`, `pid`, `type`)
values (4, '新增', 'user/add', 1, null);
insert into `frame_permission`(`id`, `permission_name`, `uri`, `pid`, `type`)
values (5, '删除', 'user/delete', 1, null);
insert into `frame_permission`(`id`, `permission_name`, `uri`, `pid`, `type`)
values (6, '修改', 'user/update', 1, null);
insert into `frame_permission`(`id`, `permission_name`, `uri`, `pid`, `type`)
values (7, '查询', 'user/page', 1, null);
insert into `frame_permission`(`id`, `permission_name`, `uri`, `pid`, `type`)
values (8, '查询', 'role/list', 2, null);
insert into `frame_permission`(`id`, `permission_name`, `uri`, `pid`, `type`)
values (9, '权限列表', 'permission/list', 2, '');
insert into `frame_permission`(`id`, `permission_name`, `uri`, `pid`, `type`)
values (10, '新增', 'role/add', 2, 'button');
insert into `frame_permission`(`id`, `permission_name`, `uri`, `pid`, `type`)
values (11, '启用/禁用', 'role/updatestatus', 2, 'button');
insert into `frame_permission`(`id`, `permission_name`, `uri`, `pid`, `type`)
values (12, '删除', 'role/delete', 2, 'button');
insert into `frame_permission`(`id`, `permission_name`, `uri`, `pid`, `type`)
values (13, '详情', 'role/detail', 2, 'button');
insert into `frame_permission`(`id`, `permission_name`, `uri`, `pid`, `type`)
values (14, '修改', 'role/update', 2, 'button');
insert into `frame_permission`(`id`, `permission_name`, `uri`, `pid`, `type`)
values (15, '启用/禁用', 'user/updatestatus', 1, 'button');
insert into `frame_permission`(`id`, `permission_name`, `uri`, `pid`, `type`)
values (16, '详情', 'user/detail', 1, 'button');


insert into `frame_role_permission`(`rid`, `pid`) values (1, 3);
insert into `frame_role_permission`(`rid`, `pid`) values (1, 4);
insert into `frame_role_permission`(`rid`, `pid`) values (1, 5);
insert into `frame_role_permission`(`rid`, `pid`) values (1, 6);
insert into `frame_role_permission`(`rid`, `pid`) values (1, 7);
insert into `frame_role_permission`(`rid`, `pid`) values (1, 8);
insert into `frame_role_permission`(`rid`, `pid`) values (1, 9);
insert into `frame_role_permission`(`rid`, `pid`) values (1, 10);
insert into `frame_role_permission`(`rid`, `pid`) values (1, 11);
insert into `frame_role_permission`(`rid`, `pid`) values (1, 12);
insert into `frame_role_permission`(`rid`, `pid`) values (1, 13);
insert into `frame_role_permission`(`rid`, `pid`) values (1, 14);
insert into `frame_role_permission`(`rid`, `pid`) values (1, 15);
insert into `frame_role_permission`(`rid`, `pid`) values (1, 16);
insert into `frame_role_permission`(`rid`, `pid`) values (2, 3);
insert into `frame_role_permission`(`rid`, `pid`) values (2, 7);
insert into `frame_role_permission`(`rid`, `pid`) values (2, 8);

insert into `frame_user_role`(`uid`,`rid`) values (1,1);
insert into `frame_user_role`(`uid`,`rid`) values (2,2);

insert into `frame_module`(`id`,`pid`,`name`)values (1,0,'后台管理');
insert into `frame_module`(`id`,`pid`,`name`)values (2,1,'模块管理');
insert into `frame_module`(`id`,`pid`,`name`)values (3,1,'用户管理');
insert into `frame_module`(`id`,`pid`,`name`)values (4,1,'角色权限管理');

insert into `frame_module`(`id`,`pid`,`name`)values (5,0,'系统设置');
insert into `frame_module`(`id`,`pid`,`name`)values (6,5,'A设置');
insert into `frame_module`(`id`,`pid`,`name`)values (7,5,'B设置');
insert into `frame_module`(`id`,`pid`,`name`)values (8,5,'C设置');