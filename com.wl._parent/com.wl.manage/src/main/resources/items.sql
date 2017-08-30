/*
Navicat MySQL Data Transfer

Source Server         : whyMySql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-08-18 12:37:59
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
-- Table structure for leave_bill
-- ----------------------------
DROP TABLE IF EXISTS `leave_bill`;
CREATE TABLE `leave_bill` (
  `id` varchar(255) NOT NULL,
  `days` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `leavedate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `state` int(11) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leave_bill
-- ----------------------------
INSERT INTO `leave_bill` VALUES ('1', '4', '回家探亲', '顺便带点吃的', '2017-05-29 19:10:42', '0', '10');

-- ----------------------------
-- Table structure for sequence
-- ----------------------------
DROP TABLE IF EXISTS `sequence`;
CREATE TABLE `sequence` (
  `name` varchar(50) NOT NULL,
  `current_value` int(11) NOT NULL,
  `increment` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sequence
-- ----------------------------
INSERT INTO `sequence` VALUES ('sysSeq', '126', '1');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `name` varchar(128) NOT NULL COMMENT '资源名称',
  `type` varchar(32) NOT NULL COMMENT '资源类型：menu,button,',
  `url` varchar(128) DEFAULT NULL COMMENT '访问url地址',
  `percode` varchar(128) DEFAULT NULL COMMENT '权限代码字符串',
  `icon` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `parentid` bigint(20) DEFAULT NULL COMMENT '父结点id',
  `parentids` varchar(128) DEFAULT NULL COMMENT '父结点id列表串',
  `sortstring` varchar(128) DEFAULT NULL COMMENT '排序号',
  `available` char(1) DEFAULT NULL COMMENT '是否可用,1：可用，0不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '权限模块', '', '', '', 'icon-sys', '', '0', '0/', '0', '1');
INSERT INTO `sys_permission` VALUES ('100', '商品模块', '', '', '', 'icon-sys', '', '0', '0/', '1', '1');
INSERT INTO `sys_permission` VALUES ('11', '商品管理', 'menu', '/items/queryItems.action', '', 'icon-nav', 'closed', '100', '0/1/', '1.', '1');
INSERT INTO `sys_permission` VALUES ('12', '商品新增', 'permission', '/items/add.action', 'item:create', 'icon-add', '', '11', '0/1/11/', '', '1');
INSERT INTO `sys_permission` VALUES ('13', '商品修改', 'permission', '/items/edit.action', 'item:update', null, '', '11', '0/1/11/', '', '1');
INSERT INTO `sys_permission` VALUES ('14', '商品删除', 'permission', '/items/delete.action', 'item:delete', null, '', '11', '0/1/11/', '', '1');
INSERT INTO `sys_permission` VALUES ('15', '商品查询', 'permission', '/items/queryItems.action', 'item:query', null, '', '11', '0/1/15/', '', '1');
INSERT INTO `sys_permission` VALUES ('200', '我的请假', '', '', '', 'icon-sys', '', '0', '0/', '2', '1');
INSERT INTO `sys_permission` VALUES ('201', '我要请假', 'menu', '/leave/query.action', '', 'icon-nav', 'closed', '200', '0/200/', '4', '1');
INSERT INTO `sys_permission` VALUES ('202', '请假查询', 'permission', '/leave/query.action', 'leave:query', null, '', '201', '0/200/201/', '', '1');
INSERT INTO `sys_permission` VALUES ('203', '请假添加', 'permission', '/leave/add.action', 'leave:add', null, '', '201', '0/200/201/', '', '1');
INSERT INTO `sys_permission` VALUES ('204', '请假修改', 'permission', '/leave/update.action', 'leave:update', null, '', '201', '0/200/201/', '', '1');
INSERT INTO `sys_permission` VALUES ('205', '请假删除', 'permission', '/leave/delete.action', 'leave:delete', null, '', '201', '0/200/201/', '', '1');
INSERT INTO `sys_permission` VALUES ('21', '用户管理', 'menu', '/users/queryUsers.action', '', 'icon-users', 'closed', '1', '0/1/', '2.', '1');
INSERT INTO `sys_permission` VALUES ('210', '我是经理', '', '', '', 'icon-sys', '', '0', '0/', '5', '1');
INSERT INTO `sys_permission` VALUES ('211', '假期审批', 'menu', '', '', 'icon-nav', 'closed', '210', '0/200/210/', '', '1');
INSERT INTO `sys_permission` VALUES ('212', '假期查询', 'permission', '/verify/query.action', 'verify:query', null, '', '211', '0/200/210/', '', '1');
INSERT INTO `sys_permission` VALUES ('213', '假期审批', 'permission', '/verify/update.action', 'verify:update', null, '', '211', '0/200/210/', '', '1');
INSERT INTO `sys_permission` VALUES ('22', '用户新增', 'permission', '/users/add.action', 'user:create', null, '', '21', '0/1/21/', '', '1');
INSERT INTO `sys_permission` VALUES ('23', '用户修改', 'permission', '/users/update.action', 'user:update', null, '', '21', '0/1/21/', '', '1');
INSERT INTO `sys_permission` VALUES ('230', '部署模块', '', '', '', 'icon-sys', '', '0', '0/', '0', '1');
INSERT INTO `sys_permission` VALUES ('231', '部署管理', 'menu', '/workflowAction/deployHome.action', '', 'icon-nav', 'closed', '230', '0/1/', '1.', '1');
INSERT INTO `sys_permission` VALUES ('24', '用户删除', 'permission', '/users/delete.action', 'user:delete', null, '', '21', '0/1/21/', '', '1');
INSERT INTO `sys_permission` VALUES ('25', '用户查询', 'permission', '/users/query.action', 'user:query', null, '', '21', '0/1/21/', '', '1');
INSERT INTO `sys_permission` VALUES ('26', '用户授权', 'permission', '/users/findRoleByidJson.action', 'user:setrole', null, '', '21', '0/1/21/', '', '1');
INSERT INTO `sys_permission` VALUES ('31', '角色管理', 'menu', '/role/queryRole.action', '', 'icon-role', 'closed', '1', '0/1/', '3', '1');
INSERT INTO `sys_permission` VALUES ('32', '角色查询', 'permission', '/role/queryRole.action', 'role:query', null, '', '31', '0/1/32/', '', '1');
INSERT INTO `sys_permission` VALUES ('33', '角色新增', 'permission', '/role/adde.action', 'role:add', null, '', '31', '0/1/32/', '', '1');
INSERT INTO `sys_permission` VALUES ('34', '角色删除', 'permission', '/role/delete.action', 'role:delete', null, '', '31', '0/1/32/', '', '1');
INSERT INTO `sys_permission` VALUES ('35', '角色修改', 'permission', '/role/update.action', 'role:update', null, '', '31', '0/1/32/', '', '1');
INSERT INTO `sys_permission` VALUES ('36', '角色授权', 'permission', '/role/updateRolePermission.action', 'role:setpermission', null, '', '31', '0/1/32/', '', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(11) NOT NULL,
  `name` varchar(128) NOT NULL,
  `available` char(1) DEFAULT NULL COMMENT '是否可用,1：可用，0不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('10', '商品管理员', '1');
INSERT INTO `sys_role` VALUES ('11', '系统管理员', '1');
INSERT INTO `sys_role` VALUES ('12', '用户管理员', '1');
INSERT INTO `sys_role` VALUES ('13', 'super administrator', '1');
INSERT INTO `sys_role` VALUES ('20', '员工', '1');
INSERT INTO `sys_role` VALUES ('21', '角色 - 一级经理', '1');
INSERT INTO `sys_role` VALUES ('22', '角色 - 二级经理', '1');
INSERT INTO `sys_role` VALUES ('23', '测试', null);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` varchar(11) NOT NULL,
  `sys_role_id` varchar(32) NOT NULL COMMENT '角色id',
  `sys_permission_id` varchar(32) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '10', '12');
INSERT INTO `sys_role_permission` VALUES ('12', '10', '11');
INSERT INTO `sys_role_permission` VALUES ('122', '23', '100');
INSERT INTO `sys_role_permission` VALUES ('123', '23', '11');
INSERT INTO `sys_role_permission` VALUES ('124', '23', '12');
INSERT INTO `sys_role_permission` VALUES ('125', '23', '13');
INSERT INTO `sys_role_permission` VALUES ('126', '23', '14');
INSERT INTO `sys_role_permission` VALUES ('13', '12', '1');
INSERT INTO `sys_role_permission` VALUES ('14', '11', '1');
INSERT INTO `sys_role_permission` VALUES ('15', '11', '100');
INSERT INTO `sys_role_permission` VALUES ('16', '11', '15');
INSERT INTO `sys_role_permission` VALUES ('17', '11', '25');
INSERT INTO `sys_role_permission` VALUES ('18', '11', '32');
INSERT INTO `sys_role_permission` VALUES ('19', '11', '36');
INSERT INTO `sys_role_permission` VALUES ('2', '10', '100');
INSERT INTO `sys_role_permission` VALUES ('20', '11', '26');
INSERT INTO `sys_role_permission` VALUES ('21', '20', '200');
INSERT INTO `sys_role_permission` VALUES ('22', '20', '201');
INSERT INTO `sys_role_permission` VALUES ('23', '20', '202');
INSERT INTO `sys_role_permission` VALUES ('24', '20', '203');
INSERT INTO `sys_role_permission` VALUES ('25', '20', '204');
INSERT INTO `sys_role_permission` VALUES ('26', '21', '210');
INSERT INTO `sys_role_permission` VALUES ('27', '21', '211');
INSERT INTO `sys_role_permission` VALUES ('28', '21', '212');
INSERT INTO `sys_role_permission` VALUES ('29', '21', '213');
INSERT INTO `sys_role_permission` VALUES ('3', '12', '21');
INSERT INTO `sys_role_permission` VALUES ('30', '22', '210');
INSERT INTO `sys_role_permission` VALUES ('31', '22', '211');
INSERT INTO `sys_role_permission` VALUES ('32', '22', '212');
INSERT INTO `sys_role_permission` VALUES ('33', '22', '213');
INSERT INTO `sys_role_permission` VALUES ('34', '11', '230');
INSERT INTO `sys_role_permission` VALUES ('35', '11', '231');
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
  `id` varchar(11) NOT NULL COMMENT '主键',
  `usercode` varchar(32) NOT NULL COMMENT '账号',
  `username` varchar(64) NOT NULL COMMENT '姓名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `email` varchar(255) DEFAULT NULL,
  `salt` varchar(64) DEFAULT NULL COMMENT '盐',
  `locked` char(1) DEFAULT NULL COMMENT '账号是否锁定，1：锁定，0未锁定',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'lisi', '李四', 'a570ee238e445a4b7c9392c946ecf24a', null, 'qwioks', '0');
INSERT INTO `sys_user` VALUES ('10', 'bill', '员工1', 'a570ee238e445a4b7c9392c946ecf24a', null, 'qwioks', '0');
INSERT INTO `sys_user` VALUES ('11', 'one', '一级经理', 'a570ee238e445a4b7c9392c946ecf24a', null, 'qwioks', '0');
INSERT INTO `sys_user` VALUES ('12', 'two', '二级经理', 'a570ee238e445a4b7c9392c946ecf24a', null, 'qwioks', '0');
INSERT INTO `sys_user` VALUES ('2', 'why', '吴海勇', 'a570ee238e445a4b7c9392c946ecf24a', null, 'qwioks', '0');
INSERT INTO `sys_user` VALUES ('3', 'zhangsan', 'zhangsan', 'a570ee238e445a4b7c9392c946ecf24a', null, 'qwioks', '0');
INSERT INTO `sys_user` VALUES ('4', 'admin', 'admin', 'a570ee238e445a4b7c9392c946ecf24a', null, 'qwioks', '0');
INSERT INTO `sys_user` VALUES ('test', 'test', '测试', 'a570ee238e445a4b7c9392c946ecf24a', null, 'qwioks', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(11) NOT NULL,
  `sys_user_id` varchar(32) NOT NULL,
  `sys_role_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('10', '3', '10');
INSERT INTO `sys_user_role` VALUES ('12', '1', '12');
INSERT INTO `sys_user_role` VALUES ('20', '10', '20');
INSERT INTO `sys_user_role` VALUES ('23', '2', '11');
INSERT INTO `sys_user_role` VALUES ('24', '11', '21');
INSERT INTO `sys_user_role` VALUES ('25', '12', '22');
INSERT INTO `sys_user_role` VALUES ('26', 'test', '23');
