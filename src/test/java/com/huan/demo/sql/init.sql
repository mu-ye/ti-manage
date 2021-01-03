### 创建并切换数据库
CREATE DATABASE study;
USE study;

### 创建数据库表

# 1. 创建角色表
CREATE TABLE IF NOT EXISTS role
(
    id          INT UNSIGNED AUTO_INCREMENT COMMENT '自增ID',
    role_name   VARCHAR(20)  NOT NULL COMMENT '权限名称',
    alive       INT UNSIGNED NOT NULL COMMENT '是否生效 0： false  1: true',
    creat_time  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4;

# 2. 创建用户表
CREATE TABLE IF NOT EXISTS user
(
    id          INT UNSIGNED AUTO_INCREMENT COMMENT '自增ID',
    username    VARCHAR(20)  NOT NULL COMMENT '用户名',
    password    VARCHAR(100) NOT NULL COMMENT '密码',
    alive       INT UNSIGNED NOT NULL COMMENT '是否生效 0： false  1: true',
    creat_time  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4;

# 3.  创建menu 表

CREATE TABLE IF NOT EXISTS menu
(
    id          INT UNSIGNED AUTO_INCREMENT COMMENT '自增ID',
    parent_id   INT UNSIGNED NOT NULL COMMENT '父ID',
    name        VARCHAR(50)  NOT NULL COMMENT '页面名称',
    path        VARCHAR(50)  NOT NULL COMMENT '路径',
    redirect    VARCHAR(50)  NOT NULL COMMENT '依赖',
    component   VARCHAR(50)  NOT NULL COMMENT '成分',
    icon        VARCHAR(50)  NOT NULL COMMENT '图标',
    title       VARCHAR(50)  NOT NULL COMMENT '目录名称',
    creat_time  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4;

# 4. 创建 tree 表( 前端动态加载数据 )
CREATE TABLE IF NOT EXISTS tree
(
    id          INT UNSIGNED AUTO_INCREMENT COMMENT '自增ID',
    parent_id   INT UNSIGNED NOT NULL COMMENT '父ID',
    title       VARCHAR(100) NOT NULL COMMENT '节点名称',
    is_leaf     INT UNSIGNED NOT NULL COMMENT '是否为叶子节点 1： true  0: false',
    alive       INT UNSIGNED NOT NULL COMMENT '是否生效 0： false  1: true',
    creat_time  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4;

# 5. 创建 定时任务表
CREATE TABLE IF NOT EXISTS cron
(
    id          INT UNSIGNED AUTO_INCREMENT COMMENT '自增ID',
    cron        VARCHAR(20)  NOT NULL COMMENT '定时任务',
    alive       INT UNSIGNED NOT NULL COMMENT '是否生效 0： false  1: true',
    creat_time  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4;

# 6 excel  excelDemo 包括 int string dataTime

CREATE TABLE IF NOT EXISTS excel
(
    id          INT UNSIGNED AUTO_INCREMENT COMMENT '自增ID',
    age         INT UNSIGNED NOT NULL COMMENT '年龄',
    job_number  VARCHAR(10)  NOT NULL COMMENT '工号',
    birthday    DATETIME     NOT NULL COMMENT '生日',
    alive       INT UNSIGNED NOT NULL COMMENT '是否生效 0： false  1: true',
    creat_time  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4;


# 7 创建用户角色表 user_role  (用户的唯一值暂不用id来表示， 使用 username)
CREATE TABLE IF NOT EXISTS user_role
(
    id          INT UNSIGNED AUTO_INCREMENT COMMENT '自增ID',
    job_number  VARCHAR(10)  NOT NULL COMMENT '用户工号（唯一标识）',
    role_id     INT UNSIGNED NOT NULL COMMENT '角色ID',
    alive       INT UNSIGNED NOT NULL COMMENT '是否生效 0： false  1: true',
    creat_time  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4;

### 插入数据
# 1. 插入角色信息
INSERT INTO role(role_name, alive)
VALUES ('ROLE_ADMIN', 1),
       ('ROLE_USER', 1);


# 2. 插入角色信息
INSERT INTO user(username, password, alive)
VALUES ('117042', '2mQXBkbopVcbL+x7pJpyAw==', 1);


# 3. 插入目录结构
### 初始化目录
INSERT INTO menu (id, parent_id, name, path, redirect, component, icon, title)
VALUES (1, 0, 'index', '/', '/home', 'BasicLayout', ' ', '首页'),
       (2, 1, 'home', '/home', '', 'home', 'home', '首页'),
       (3, 1, 'system', '/system', '/system/student', 'RouteView', 'robot', '基础信息管理'),
       (4, 1, 'competition', '/competition', '', 'RouteView', 'solution', '签到管理'),
       (5, 1, 'drawManage', '/drawManage', '', 'RouteView', 'table', '抽签管理'),
       (6, 1, 'chairUmpire', '/chairUmpire', '', 'RouteView', 'audit', '主裁管理页面'),

       (7, 3, 'student', '/system/student', '', 'system/student', '', '考生管理'),
       (8, 3, 'judge', '/system/judge', '', 'system/judge', '', '裁判管理'),
       (9, 3, 'test', '/system/test', '', 'system/test', '', '试题管理'),
       (10, 3, 'company', '/system/company', '', 'system/company', '', '参赛单位管理'),
       (11, 3, 'syncComputerTestResult', '/chairUmpire/syncComputerTestResult', '',
        'chairUmpire/syncComputerTestResult', '', '上传机考成绩'),


       (12, 4, 'studentSign', '/competition/studentSign', '', 'competition/studentSign', '', '考生签到（报到）'),
       (13, 4, 'judgeSign', '/competition/judgeSign', '', 'competition/judgeSign', '', '裁判签到（报到）'),
       (14, 4, 'studentSignOne', '/competition/studentSignOne', '', 'competition/studentSignOne', '', '候考签到'),
       (15, 4, 'studentSignTwo', '/competition/studentSignTwo', '', 'competition/studentSignTwo', '', '备考签到'),
       (16, 4, 'studentSignAway', '/competition/studentSignAway', '', 'competition/studentSignAway', '', '离场签到'),
       (17, 4, 'qrSign', '/competition/qrSign', '', 'competition/qrSign', '', '二维码签到'),

       (18, 5, 'drawStateManage', '/drawManage/drawStateManage', '', 'drawManage/drawStateManage', '', '抽签状态管理'),
       (19, 5, 'drawCompany', '/drawManage/drawCompany', '', 'drawManage/drawCompany', '', '代表队抽签'),
       (20, 5, 'chairDraw', '/drawManage/chairDraw', '', 'drawManage/chairDraw', '', '赛位抽签'),
       (21, 5, 'judgeDraw', '/drawManage/judgeDraw', '', 'drawManage/judgeDraw', '', '裁判抽签'),
       (22, 5, 'judgeDrawResult', '/drawManage/judgeDrawResult', '', 'drawManage/judgeDrawResult', '', '裁判抽签结果'),

       (23, 6, 'roundManage', '/chairUmpire/roundManage', '', 'chairUmpire/roundManage', '', '比赛管理'),
       (24, 6, 'checkResult', '/chairUmpire/checkResult', '', 'chairUmpire/checkResult', '', '成绩核验'),
       (25, 6, 'collectScore', '/chairUmpire/collectScore', '', 'chairUmpire/collectScore', '', '打分汇总'),
       (26, 6, 'companyResult', '/chairUmpire/companyResult', '', 'chairUmpire/companyResult', '', '团体总分汇总'),
       (27, 6, 'judgeChange', '/chairUmpire/judgeChange', '', 'chairUmpire/judgeChange', '', '裁判调整'),
       (28, 6, 'pauseAdjust', '/chairUmpire/pauseAdjust', '', 'chairUmpire/pauseAdjust', '', '暂停调整'),
       (29, 6, 'writeResult', '/chairUmpire/writeResult', '', 'chairUmpire/writeResult', '', '成绩补录');


# 4. 插入树形文件目录
INSERT INTO tree(parent_id, title, is_leaf, alive)
VALUES (0, '0-1', 0, 1),
       (0, '0-2', 0, 1),
       (1, '1-1-1', 0, 1),
       (1, '1-1-2', 1, 1),
       (3, '1-1-1-1', 0, 1),
       (3, '1-1-1-2', 1, 1);


# 5. 插入定时任务
INSERT INTO cron(cron, alive)
VALUES ('0/5 * * * * ?', 1);


# 6. 插入用户角色表
INSERT INTO user_role(job_number, role_id, alive)
VALUES ('117042', 1, 1),
       ('117042', 2, 1);


SELECT role_name
FROM user_role,role
WHERE user_role.job_number = '117042' and user_role.role_id = role.id;


