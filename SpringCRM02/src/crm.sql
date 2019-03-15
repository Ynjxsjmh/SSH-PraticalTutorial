/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : strutscrm

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2018-06-14 09:49:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cst_customer
-- ----------------------------
DROP TABLE IF EXISTS `cst_customer`;
CREATE TABLE `cst_customer` (
  `cust_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cust_name` varchar(255) DEFAULT NULL,
  `cust_user_id` bigint(20) DEFAULT NULL,
  `cust_create_id` bigint(20) DEFAULT NULL,
  `cust_source` varchar(255) DEFAULT NULL,
  `cust_industry` varchar(255) DEFAULT NULL,
  `cust_level` varchar(255) DEFAULT NULL,
  `cust_linkman` varchar(255) DEFAULT NULL,
  `cust_phone` varchar(255) DEFAULT NULL,
  `cust_mobile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cst_customer
-- ----------------------------
INSERT INTO `cst_customer` VALUES ('19', '天使', null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('20', 'cat', null, null, '', null, '', '', '', '');
INSERT INTO `cst_customer` VALUES ('21', '小孙', null, null, '11', null, '', '', '22', '');
INSERT INTO `cst_customer` VALUES ('22', '陈家祠', null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('23', '小花', null, null, '', null, '', '', '', '');
INSERT INTO `cst_customer` VALUES ('24', '李白', null, null, '网络', null, 'vip', '', '11223333', '');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `uid` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'la', 'la', null);
