/*
Navicat MySQL Data Transfer

Source Server         : 192.168.100.185
Source Server Version : 50173
Source Host           : 192.168.100.185:3306
Source Database       : openplatform

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-06-15 16:52:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for application
-- ----------------------------
DROP TABLE IF EXISTS `application`;
CREATE TABLE `application` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appname` varchar(255) NOT NULL COMMENT '应用名',
  `appkey` varchar(255) NOT NULL COMMENT '应用key',
  `secret` varchar(255) NOT NULL COMMENT '应用secret',
  `redirect_url` varchar(255) NOT NULL COMMENT '重定向地址',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of application
-- ----------------------------
INSERT INTO `application` VALUES ('4', '积流网', '874146317135', 'k7RSQH46AKp0bfmJS78oRqaCnW0H', 'http://127.0.0.1:8081/openplatform-client/oauth/access-token', 'dylan');

-- ----------------------------
-- Table structure for platform_user
-- ----------------------------
DROP TABLE IF EXISTS `platform_user`;
CREATE TABLE `platform_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `salt` blob COMMENT '加密盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱地址',
  `mobile_phone` varchar(20) DEFAULT NULL COMMENT '移动电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3776 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of platform_user
-- ----------------------------
INSERT INTO `platform_user` VALUES ('3775', 'dylan', '102433eed6f3e716f90428f10bc36f55f3a128a70fb83a0ed971', 0x33EED6F3E716F904, '12345678@qq.com', '13245634578');
