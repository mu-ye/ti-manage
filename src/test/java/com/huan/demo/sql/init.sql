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
    password    VARCHAR(100)  NOT NULL COMMENT '密码',
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


# 1. 插入角色信息
INSERT INTO user(username, password,alive)
VALUES ('117042', '$2a$10$BQWN5NyogF/f944bfwevNO4kP5b8yR9haP3UoNZfrsWXT1syAEa8O',1);

