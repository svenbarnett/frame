
-- 成绩科目表 --
drop table if exists `nd_exam_course`;
drop table if exists `nd_exam_info`;
drop table if exists `nd_student`;

-- 学生表 --
create table `nd_student`
(
    `id`          varchar(50)  not null primary key comment '主键',
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


-- 成绩信息表 --
create table `nd_exam_info`
(
    `id`             varchar(50)  not null primary key comment '成绩主键',
    `year`           varchar(20) not null comment '成绩学年',
    `term`           tinyint(1) comment '学期，1：第一学期，2：第二学期',
    `student_name`   varchar(32) comment '学生姓名',
    `student_number` varchar(32) comment '学生学号',
    `allscore`       float(5, 2) comment '总学分',
    `getscore`       float(5, 2) comment '获得学分',
    `gpa`            float(4, 2) default 0.00 comment '学生绩点',
    `rank`           int(11) comment '排名',
    `create_time`    datetime    default current_timestamp comment '创建时间',
    `update_time`    datetime comment '更新时间',
    `delete_time`    datetime comment '删除时间',
    index create_time_index (create_time desc)
) engine = innodb
  default charset = utf8mb4 comment ='学生成绩表';

create table `nd_exam_course`
(
    `id`           varchar(50) not null primary key comment '成绩科目主键',
    `exam_info_id` varchar(50) comment '成绩信息表id',
    `name`         varchar(32) comment '科目名称',
    `weight`       float(4, 2) default 0.00 comment '课程学分',
    `score`        varchar(32) comment '课程得分',
    `create_time`  datetime    default current_timestamp comment '创建时间',
    `update_time`  datetime comment '更新时间',
    `delete_time`  datetime comment '删除时间',
    index create_time_index (create_time desc)
) engine = innodb
  default charset = utf8mb4 comment ='学生成绩科目表';
