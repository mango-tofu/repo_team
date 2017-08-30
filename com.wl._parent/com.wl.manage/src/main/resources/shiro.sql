/*
Navicat MySQL Data Transfer

Source Server         : whyMySql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-05-25 16:08:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for items
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `detail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of items
-- ----------------------------
INSERT INTO `items` VALUES ('1', '西瓜', '15.00', '2.5/500g', '2017-05-21 18:02:22', null);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(128) NOT NULL COMMENT '资源名称',
  `type` varchar(32) NOT NULL COMMENT '资源类型：menu,button,',
  `url` varchar(128) DEFAULT NULL COMMENT '访问url地址',
  `state` varchar(255) DEFAULT NULL,
  `percode` varchar(128) DEFAULT NULL COMMENT '权限代码字符串',
  `parentid` bigint(20) DEFAULT NULL COMMENT '父结点id',
  `parentids` varchar(128) DEFAULT NULL COMMENT '父结点id列表串',
  `sortstring` varchar(128) DEFAULT NULL COMMENT '排序号',
  `available` char(1) DEFAULT NULL COMMENT '是否可用,1：可用，0不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '权限模块', '', '', '', '', '0', '0/', '0', '1');
INSERT INTO `sys_permission` VALUES ('11', '商品管理', 'menu', '/items/queryItems.action', 'closed', '', '1', '0/1/', '1.', '1');
INSERT INTO `sys_permission` VALUES ('12', '商品新增', 'permission', '/items/add.action', '', 'item:create', '11', '0/1/11/', '', '1');
INSERT INTO `sys_permission` VALUES ('13', '商品修改', 'permission', '/items/edit.action', '', 'item:update', '11', '0/1/11/', '', '1');
INSERT INTO `sys_permission` VALUES ('14', '商品删除', 'permission', '/items/delete.action', '', 'item:delete', '11', '0/1/11/', '', '1');
INSERT INTO `sys_permission` VALUES ('15', '商品查询', 'permission', '/items/queryItems.action', '', 'item:query', '11', '0/1/15/', '', '1');
INSERT INTO `sys_permission` VALUES ('21', '用户管理', 'menu', '/users/queryUsers.action', 'closed', '', '1', '0/1/', '2.', '1');
INSERT INTO `sys_permission` VALUES ('22', '用户新增', 'permission', '/users/add.action', '', 'user:create', '21', '0/1/21/', '', '1');
INSERT INTO `sys_permission` VALUES ('23', '用户修改', 'permission', '/users/update.action', '', 'user:update', '21', '0/1/21/', '', '1');
INSERT INTO `sys_permission` VALUES ('24', '用户删除', 'permission', '/users/delete.action', '', 'user:delete', '21', '0/1/21/', '', '1');
INSERT INTO `sys_permission` VALUES ('25', '用户查询', 'permission', '/users/query.action', '', 'user:query', '21', '0/1/21/', '', '1');
INSERT INTO `sys_permission` VALUES ('31', '角色管理', 'menu', '/role/queryRole.action', 'closed', '', '1', '0/1/', '3', '1');
INSERT INTO `sys_permission` VALUES ('32', '角色查询', 'permission', '/role/queryRole.action', '', 'role:query', '31', '0/1/32/', '', '1');
INSERT INTO `sys_permission` VALUES ('33', '角色新增', 'permission', '/role/adde.action', '', 'role:add', '31', '0/1/32/', '', '1');
INSERT INTO `sys_permission` VALUES ('34', '角色删除', 'permission', '/role/delete.action', '', 'role:delete', '31', '0/1/32/', '', '1');
INSERT INTO `sys_permission` VALUES ('35', '角色修改', 'permission', '/role/update.action', '', 'role:update', '31', '0/1/32/', '', '1');
INSERT INTO `sys_permission` VALUES ('41', '权限分配管理', 'menu', '/items/queryItems.action', 'closed', '', '1', '0/1/', '4', '1');
INSERT INTO `sys_permission` VALUES ('42', '权限分配查询', 'permission', '/permission/query.action', '', 'permission:query', '41', '0/1/41/', '', '1');
INSERT INTO `sys_permission` VALUES ('43', '权限分配新增', 'permission', '/permission/add.action', '', 'permission:add', '41', '0/1/41/', '', '1');
INSERT INTO `sys_permission` VALUES ('44', '权限分配删除', 'permission', '/permission/deleteaction', '', 'permission:delete', '41', '0/1/41/', '', '1');
INSERT INTO `sys_permission` VALUES ('45', '权限分配修改', 'permission', '/permission/update.action', '', 'permission:update', '41', '0/1/41/', '', '1');
INSERT INTO `sys_permission` VALUES ('51', '角色分配管理', 'menu', 'role_permission/query.action', 'closed', '', '1', '0/1/', '5', '1');
INSERT INTO `sys_permission` VALUES ('52', '角色分配查询', 'permission', 'role_permission/query.action', '', 'role_permission:query', '51', '0/1/51/', '', '1');
INSERT INTO `sys_permission` VALUES ('53', '角色分配新增', 'permission', 'role_permission/add.action', '', 'role_permission:add', '51', '0/1/51/', '', '1');
INSERT INTO `sys_permission` VALUES ('54', '角色分配删除', 'permission', 'role_permission/delete.action', '', 'role_permission:delete', '51', '0/1/51/', '', '1');
INSERT INTO `sys_permission` VALUES ('55', '角色分配修改', 'permission', 'role_permission/update.action', '', 'role_permission:update', '51', '0/1/51/', '', '1');
INSERT INTO `sys_permission` VALUES ('100', '商品模块', '', '', '', '', '2', '0/', '1', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `available` char(1) DEFAULT NULL COMMENT '是否可用,1：可用，0不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('10', '商品管理员', '1');
INSERT INTO `sys_role` VALUES ('11', '系统管理员', '1');
INSERT INTO `sys_role` VALUES ('12', '用户管理员', '1');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` varchar(36) NOT NULL,
  `sys_role_id` varchar(32) NOT NULL COMMENT '角色id',
  `sys_permission_id` varchar(32) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '10', '12');
INSERT INTO `sys_role_permission` VALUES ('10', '11', '41');
INSERT INTO `sys_role_permission` VALUES ('11', '11', '51');
INSERT INTO `sys_role_permission` VALUES ('2', '10', '11');
INSERT INTO `sys_role_permission` VALUES ('3', '12', '21');
INSERT INTO `sys_role_permission` VALUES ('4', '12', '15');
INSERT INTO `sys_role_permission` VALUES ('5', '12', '22');
INSERT INTO `sys_role_permission` VALUES ('6', '10', '15');
INSERT INTO `sys_role_permission` VALUES ('7', '11', '11');
INSERT INTO `sys_role_permission` VALUES ('8', '11', '21');
INSERT INTO `sys_role_permission` VALUES ('9', '11', '31');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `usercode` varchar(32) NOT NULL COMMENT '账号',
  `username` varchar(64) NOT NULL COMMENT '姓名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `salt` varchar(64) DEFAULT NULL COMMENT '盐',
  `locked` char(1) DEFAULT NULL COMMENT '账号是否锁定，1：锁定，0未锁定',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'lisi', '李四', '111111', 'uiwueylm', '0');
INSERT INTO `sys_user` VALUES ('2', 'why', '吴海勇', '111111', 'dfsfsfg', '0');
INSERT INTO `sys_user` VALUES ('3', 'zhangsan', 'zhangsan', '111111', 'eteokues', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_user_id` varchar(32) NOT NULL,
  `sys_role_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('10', '3', '10');
INSERT INTO `sys_user_role` VALUES ('12', '1', '12');
INSERT INTO `sys_user_role` VALUES ('23', '2', '11');
