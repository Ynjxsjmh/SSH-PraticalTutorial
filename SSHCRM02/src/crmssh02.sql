/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : crmssh02

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2017-07-01 21:03:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_dict
-- ----------------------------
DROP TABLE IF EXISTS `base_dict`;
CREATE TABLE `base_dict` (
  `dict_id` varchar(255) NOT NULL,
  `dict_type_code` varchar(255) DEFAULT NULL,
  `dict_type_name` varchar(255) DEFAULT NULL,
  `dict_item_name` varchar(255) DEFAULT NULL,
  `dict_item_code` varchar(255) DEFAULT NULL,
  `dict_sort` int(11) DEFAULT NULL,
  `dict_enable` varchar(255) DEFAULT NULL,
  `dict_memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_dict
-- ----------------------------
INSERT INTO `base_dict` VALUES ('1', '001', '客户行业', '教育培训 ', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('10', '003', '公司性质', '民企', null, '3', '1', null);
INSERT INTO `base_dict` VALUES ('12', '004', '年营业额', '1-10万', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('13', '004', '年营业额', '10-20万', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('14', '004', '年营业额', '20-50万', null, '3', '1', null);
INSERT INTO `base_dict` VALUES ('15', '004', '年营业额', '50-100万', null, '4', '1', null);
INSERT INTO `base_dict` VALUES ('16', '004', '年营业额', '100-500万', null, '5', '1', null);
INSERT INTO `base_dict` VALUES ('17', '004', '年营业额', '500-1000万', null, '6', '1', null);
INSERT INTO `base_dict` VALUES ('18', '005', '客户状态', '基础客户', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('19', '005', '客户状态', '潜在客户', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('2', '001', '客户行业', '电子商务', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('20', '005', '客户状态', '成功客户', null, '3', '1', null);
INSERT INTO `base_dict` VALUES ('21', '005', '客户状态', '无效客户', null, '4', '1', null);
INSERT INTO `base_dict` VALUES ('22', '006', '客户级别', '普通客户', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('23', '006', '客户级别', 'VIP客户', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('24', '007', '商机状态', '意向客户', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('25', '007', '商机状态', '初步沟通', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('26', '007', '商机状态', '深度沟通', null, '3', '1', null);
INSERT INTO `base_dict` VALUES ('27', '007', '商机状态', '签订合同', null, '4', '1', null);
INSERT INTO `base_dict` VALUES ('3', '001', '客户行业', '对外贸易', null, '3', '1', null);
INSERT INTO `base_dict` VALUES ('30', '008', '商机类型', '新业务', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('31', '008', '商机类型', '现有业务', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('32', '009', '商机来源', '电话营销', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('33', '009', '商机来源', '网络营销', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('34', '009', '商机来源', '推广活动', null, '3', '1', null);
INSERT INTO `base_dict` VALUES ('4', '001', '客户行业', '酒店旅游', null, '4', '1', null);
INSERT INTO `base_dict` VALUES ('5', '001', '客户行业', '房地产', null, '5', '1', null);
INSERT INTO `base_dict` VALUES ('6', '002', '客户信息来源', '电话营销', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('7', '002', '客户信息来源', '网络营销', null, '2', '1', null);
INSERT INTO `base_dict` VALUES ('8', '003', '公司性质', '合资', null, '1', '1', null);
INSERT INTO `base_dict` VALUES ('9', '003', '公司性质', '国企', null, '2', '1', null);

-- ----------------------------
-- Table structure for cst_customer
-- ----------------------------
DROP TABLE IF EXISTS `cst_customer`;
CREATE TABLE `cst_customer` (
  `cust_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cust_name` varchar(255) DEFAULT NULL,
  `cust_user_id` bigint(20) DEFAULT NULL,
  `cust_create_id` bigint(20) DEFAULT NULL,
  `cust_linkman` varchar(255) DEFAULT NULL,
  `cust_phone` varchar(255) DEFAULT NULL,
  `cust_mobile` varchar(255) DEFAULT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  `cust_source` varchar(255) DEFAULT NULL,
  `cust_industry` varchar(255) DEFAULT NULL,
  `cust_level` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cust_id`),
  KEY `FKeh5g36duab8g1h051pdjfwcgd` (`cust_source`),
  KEY `FK2xhr3arwp3tkuae1da4lqv352` (`cust_industry`),
  KEY `FKrty52nvbjg1echf0se39eng49` (`cust_level`),
  CONSTRAINT `FKrty52nvbjg1echf0se39eng49` FOREIGN KEY (`cust_level`) REFERENCES `base_dict` (`dict_id`),
  CONSTRAINT `FK2xhr3arwp3tkuae1da4lqv352` FOREIGN KEY (`cust_industry`) REFERENCES `base_dict` (`dict_id`),
  CONSTRAINT `FKeh5g36duab8g1h051pdjfwcgd` FOREIGN KEY (`cust_source`) REFERENCES `base_dict` (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cst_customer
-- ----------------------------
INSERT INTO `cst_customer` VALUES ('2', '小军', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('3', '小军', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('27', '小红', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('28', '北', null, null, '', '', '', null, null, null, null);
INSERT INTO `cst_customer` VALUES ('63', '小', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('64', '小', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('68', '小', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('69', '小', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('70', '小', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('73', '小', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('74', '小', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('75', '小', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('76', '小', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('77', '小', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('78', '小', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('79', '小', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('80', '小', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('82', '小红', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('83', '小红', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('84', '小红', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('85', '小军', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('86', '小军', null, null, null, null, null, null, null, null, null);
INSERT INTO `cst_customer` VALUES ('93', '小军', null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for cst_linkman
-- ----------------------------
DROP TABLE IF EXISTS `cst_linkman`;
CREATE TABLE `cst_linkman` (
  `lkm_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lkm_name` varchar(255) DEFAULT NULL,
  `lkm_gender` varchar(255) DEFAULT NULL,
  `lkm_phone` varchar(255) DEFAULT NULL,
  `lkm_mobile` varchar(255) DEFAULT NULL,
  `lkm_email` varchar(255) DEFAULT NULL,
  `lkm_qq` varchar(255) DEFAULT NULL,
  `lkm_position` varchar(255) DEFAULT NULL,
  `lkm_memo` varchar(255) DEFAULT NULL,
  `lkm_cust_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`lkm_id`),
  KEY `FKh9yp1nql5227xxcopuxqx2e7q` (`lkm_cust_id`),
  CONSTRAINT `FKh9yp1nql5227xxcopuxqx2e7q` FOREIGN KEY (`lkm_cust_id`) REFERENCES `cst_customer` (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cst_linkman
-- ----------------------------
INSERT INTO `cst_linkman` VALUES ('2', '王总', null, '4354353', null, null, null, null, null, '27');

-- ----------------------------
-- Table structure for sale_visit
-- ----------------------------
DROP TABLE IF EXISTS `sale_visit`;
CREATE TABLE `sale_visit` (
  `visit_id` varchar(255) NOT NULL,
  `visit_time` varchar(255) DEFAULT NULL,
  `visit_interviewee` varchar(255) DEFAULT NULL,
  `visit_addr` varchar(255) DEFAULT NULL,
  `visit_detail` varchar(255) DEFAULT NULL,
  `visit_nexttime` varchar(255) DEFAULT NULL,
  `visit_cust_id` bigint(20) DEFAULT NULL,
  `visit_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`visit_id`),
  KEY `FKgr4aivocixwcvkwxcmc0b4css` (`visit_cust_id`),
  KEY `FKc92iepd26mixxfiris92hccjx` (`visit_user_id`),
  CONSTRAINT `FKc92iepd26mixxfiris92hccjx` FOREIGN KEY (`visit_user_id`) REFERENCES `sys_user` (`user_id`),
  CONSTRAINT `FKgr4aivocixwcvkwxcmc0b4css` FOREIGN KEY (`visit_cust_id`) REFERENCES `cst_customer` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_visit
-- ----------------------------
INSERT INTO `sale_visit` VALUES ('2c909190530346f001530346f3680000', null, null, null, null, null, '27', '5');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('5', 'cjc', 'cjc', '4b1be29509fed7a6b170933b12d49e40', '1');
INSERT INTO `sys_user` VALUES ('6', 'm0001', '小红', '123', '1');
INSERT INTO `sys_user` VALUES ('7', 'm0001', '小明', '123', '1');
INSERT INTO `sys_user` VALUES ('8', 'm0001', '小红', '123', '1');
