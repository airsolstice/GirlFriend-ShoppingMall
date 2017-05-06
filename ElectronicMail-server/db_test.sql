/*
Navicat MySQL Data Transfer

Source Server         : admin
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : db_test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-04-07 18:11:34
*/

SET FOREIGN_KEY_CHECKS=0;//Mysql中如果表和表之间建立的外键约束，则无法删除表及修改表结构。此句是取消外键约束

-- ----------------------------
-- Table structure for `tb_cla`
-- ----------------------------
DROP TABLE IF EXISTS `tb_cla`;
CREATE TABLE `tb_cla` (
  `cid` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_cla
-- ----------------------------
INSERT INTO `tb_cla` VALUES ('1', '1');
INSERT INTO `tb_cla` VALUES ('1', '2');
INSERT INTO `tb_cla` VALUES ('1', '3');
INSERT INTO `tb_cla` VALUES ('2', '4');
INSERT INTO `tb_cla` VALUES ('2', '5');
INSERT INTO `tb_cla` VALUES ('2', '6');
INSERT INTO `tb_cla` VALUES ('2', '7');

-- ----------------------------
-- Table structure for `tb_goods`
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods`;
CREATE TABLE `tb_goods` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `catalog` int(2) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_goods
-- ----------------------------
INSERT INTO `tb_goods` VALUES ('1', 'goods1', '琛ｆ湇', '87.89', '1', 'http://image.baidu.com', '2017-04-06 15:07:23');
INSERT INTO `tb_goods` VALUES ('2', 'goods2', '澶忓ぉ鐨勮。鏈�', '56.78', '2', 'http://image.baidu.com', '2017-04-06 11:08:17');
INSERT INTO `tb_goods` VALUES ('3', 'goods3', '琛ｆ湇鏋�', '96.33', '3', 'http://image.baidu.com', '2017-04-06 15:06:23');
INSERT INTO `tb_goods` VALUES ('4', 'goods4', '澶忓ぉ鐨勯瀷瀛�', '96.73', '4', 'http://image.baidu.com', '2017-04-06 15:06:43');
INSERT INTO `tb_goods` VALUES ('5', 'goods5', '澶忓ぉ鐨勮￥瀛�', '96.23', '5', 'http://image.baidu.com', '2017-04-06 15:06:23');
INSERT INTO `tb_goods` VALUES ('6', 'goods6', '锠�', '93.23', '5', 'http://image.baidu.com', '2017-04-06 10:06:23');
INSERT INTO `tb_goods` VALUES ('7', 'goods7', '锠㈣揣', '96.23', '5', 'http://image.baidu.com', '2017-04-06 07:06:23');
INSERT INTO `tb_goods` VALUES ('9', 'goods9', '鍐', '96.23', '5', 'http://image.baidu.com', '2017-04-04 19:06:23');
INSERT INTO `tb_goods` VALUES ('10', 'goods10', '鍐鍔犵粧', '96.23', '5', 'http://image.baidu.com', '2017-04-01 15:06:23');
INSERT INTO `tb_goods` VALUES ('11', 'goods11', '鍐淇濇殩', '96.23', '5', 'http://image.baidu.com', '2017-04-08 15:06:23');
INSERT INTO `tb_goods` VALUES ('12', 'goods12', '杩欐槸涓�涓晢鍝�', '96.23', '5', 'http://image.baidu.com', '2017-04-09 15:06:23');
INSERT INTO `tb_goods` VALUES ('13', 'goods13', '杩欐槸涓�涓晢鍝�', '96.23', '5', 'http://image.baidu.com', '2017-03-26 15:06:23');
INSERT INTO `tb_goods` VALUES ('14', 'goods14', '杩欐槸涓�涓晢鍝�', '96.23', '5', 'http://image.baidu.com', '2017-04-21 15:06:23');
INSERT INTO `tb_goods` VALUES ('15', 'goods15', '杩欐槸涓�涓晢鍝�', '96.23', '5', 'http://image.baidu.com', '2017-04-29 15:06:23');
INSERT INTO `tb_goods` VALUES ('16', 'goods16', '杩欐槸涓�涓晢鍝�', '96.23', '5', 'http://image.baidu.com', '2017-04-30 15:06:23');
INSERT INTO `tb_goods` VALUES ('17', 'goods17', '杩欐槸涓�涓晢鍝�', '96.23', '5', 'http://image.baidu.com', '2017-04-01 15:06:23');
INSERT INTO `tb_goods` VALUES ('18', 'goods18', '杩欐槸涓�涓晢鍝�', '96.23', '5', 'http://image.baidu.com', '2017-04-18 10:06:23');

-- ----------------------------
-- Table structure for `tb_score`
-- ----------------------------
DROP TABLE IF EXISTS `tb_score`;
CREATE TABLE `tb_score` (
  `id` int(11) NOT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_score
-- ----------------------------
INSERT INTO `tb_score` VALUES ('1', '89');
INSERT INTO `tb_score` VALUES ('2', '78');
INSERT INTO `tb_score` VALUES ('3', '79');
INSERT INTO `tb_score` VALUES ('4', '96');
INSERT INTO `tb_score` VALUES ('5', '86');
INSERT INTO `tb_score` VALUES ('6', '87');
INSERT INTO `tb_score` VALUES ('7', '95');

-- ----------------------------
-- Table structure for `tb_score1`
-- ----------------------------
DROP TABLE IF EXISTS `tb_score1`;
CREATE TABLE `tb_score1` (
  `id` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_score1
-- ----------------------------
INSERT INTO `tb_score1` VALUES ('1', '1', '98');
INSERT INTO `tb_score1` VALUES ('2', '2', '87');
INSERT INTO `tb_score1` VALUES ('3', '1', '88');
INSERT INTO `tb_score1` VALUES ('4', '2', '96');
INSERT INTO `tb_score1` VALUES ('5', '1', '67');
INSERT INTO `tb_score1` VALUES ('6', '2', '79');
INSERT INTO `tb_score1` VALUES ('7', '1', '84');
INSERT INTO `tb_score1` VALUES ('8', '2', '69');
INSERT INTO `tb_score1` VALUES ('9', '1', '100');
INSERT INTO `tb_score1` VALUES ('10', '2', '92');

-- ----------------------------
-- Table structure for `tb_stu`
-- ----------------------------
DROP TABLE IF EXISTS `tb_stu`;
CREATE TABLE `tb_stu` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_stu
-- ----------------------------
INSERT INTO `tb_stu` VALUES ('1', 'a');
INSERT INTO `tb_stu` VALUES ('2', 'b');
INSERT INTO `tb_stu` VALUES ('3', 'c');
INSERT INTO `tb_stu` VALUES ('4', 'd');
INSERT INTO `tb_stu` VALUES ('5', 'e');
INSERT INTO `tb_stu` VALUES ('6', 'f');
INSERT INTO `tb_stu` VALUES ('7', 'g');

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` varchar(40) NOT NULL,
  `name` varchar(20) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `email` varchar(40) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `active_code` varchar(64) DEFAULT NULL,
  `head_url` varchar(255) DEFAULT NULL,
  `lat` double(10,7) DEFAULT NULL,
  `lng` double(10,7) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin1', '0192023a7bbd73250516f069df18b500', '234567890@qq.com', '18187654321', '1', '1', '101', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487926668057&di=2b4228ea5713470abdd1680b4f93d990&imgtype=0&src=http%3A%2F%2Fimg.nbdpx.com%2Fupload%2F5%2Fc1%2F5c18a3d12f10e7df219c3f0eb881ca40_thumb.png', '40.6984703', '-73.9514422');
INSERT INTO `tb_user` VALUES ('10', 'admin10', '0192023a7bbd73250516f069df18b500', '649599725@qq.com', '18112345678', '0', '1', '0557d2cc-8544-41df-96ce-839ff7cd6a44', null, null, null);
INSERT INTO `tb_user` VALUES ('11', 'admin11', '0192023a7bbd73250516f069df18b500', '123456789@qq.com', '18112345678', '0', '1', '2df13110-318f-4d58-8e2c-758df1575bda', null, null, null);
INSERT INTO `tb_user` VALUES ('12', 'fanxueqin', '2facd246a70419918f3a70158bf7c5f0', '674871323@qq.com', '18112345678', '0', '1', 'f868e2f8-1c05-4eb1-bf74-1332d45a23b0', null, null, null);
INSERT INTO `tb_user` VALUES ('13', 'admin13', '0192023a7bbd73250516f069df18b500', '1234566655@qq.com', '18112345678', '0', null, 'ce61bdea-79d5-4e2f-8e08-b69d674690c7', null, null, null);
INSERT INTO `tb_user` VALUES ('2', 'admin2', '0192023a7bbd73250516f069df18b500', '345678901@qq.com', '18187654322', '1', '1', '102', null, null, null);
INSERT INTO `tb_user` VALUES ('3', 'admin3', '0192023a7bbd73250516f069df18b500', '456789012@qq.com', '18187654323', '1', '1', '103', null, null, null);
INSERT INTO `tb_user` VALUES ('4', 'admin4', '0192023a7bbd73250516f069df18b500', '567890123@qq.com', '18187654324', '1', '1', '104', null, null, null);
INSERT INTO `tb_user` VALUES ('5', 'admin5', '0192023a7bbd73250516f069df18b500', '678901234@qq.com', '18187654325', '1', '1', '105', null, null, null);
INSERT INTO `tb_user` VALUES ('6', 'admin6', '0192023a7bbd73250516f069df18b500', '789012345@qq.com', '18187654326', '1', '1', '106', null, null, null);
INSERT INTO `tb_user` VALUES ('7', 'admin7', '0192023a7bbd73250516f069df18b500', '890123456@qq.com', '18112345678', '0', '1', 'a3c19427-81dd-47c8-9674-44372b86c95f', null, null, null);
INSERT INTO `tb_user` VALUES ('8', 'admin8', '0192023a7bbd73250516f069df18b500', '901234567@qq.com', '18112345678', '0', '1', '4eb9c936-1d15-4518-af9f-cec9be69b778', null, null, null);
INSERT INTO `tb_user` VALUES ('9', 'admin9', '0192023a7bbd73250516f069df18b500', '012345678@qq.com', '18112345678', '0', null, '00f0c05d-44d2-422c-ba96-25bac0234ab5', null, null, null);

-- ----------------------------
-- Table structure for `tb_user_group`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_group`;
CREATE TABLE `tb_user_group` (
  `ugId` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `gName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ugId`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_group
-- ----------------------------
INSERT INTO `tb_user_group` VALUES ('1', '2', '濂藉弸');
INSERT INTO `tb_user_group` VALUES ('2', '1', '鎹熷弸');
INSERT INTO `tb_user_group` VALUES ('3', '1', '鎹熷弸');
INSERT INTO `tb_user_group` VALUES ('3', '2', '濂藉弸');
INSERT INTO `tb_user_group` VALUES ('4', '1', '瀹朵汉');
INSERT INTO `tb_user_group` VALUES ('4', '2', '濂藉弸');
INSERT INTO `tb_user_group` VALUES ('5', '1', '鎹熷弸');
INSERT INTO `tb_user_group` VALUES ('6', '1', '瀹朵汉');
INSERT INTO `tb_user_group` VALUES ('6', '2', '濂藉弸');
INSERT INTO `tb_user_group` VALUES ('7', '2', '濂藉弸');
INSERT INTO `tb_user_group` VALUES ('8', '2', 'new group');


-- ----------------------------
-- Table structure for `shop_order`  璁㈠崟琛�
-- ----------------------------
DROP TABLE IF EXISTS `shop_order`;
CREATE TABLE `shop_order` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '璁㈠崟id',
  `user_id` varchar(200) NOT NULL COMMENT '鐢ㄦ埛id ',
  `goods_num` int(11) NOT NULL COMMENT '鍟嗗搧鏁伴噺',
  `total_price` double(15,2) NOT NULL COMMENT '鎬讳环鏍�',
  `consignee_name` varchar(200) DEFAULT NULL COMMENT '鏀惰揣浜哄鍚�',
  `consignee_address` varchar(200) DEFAULT NULL COMMENT '鏀惰揣浜哄湴鍧�',
  `consignee_number` bigint(15) NOT NULL COMMENT '收货人电话',
  `create_time` datetime DEFAULT NULL COMMENT '鍒涘缓鏃ユ湡',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for `shop_order_detail`  璁㈠崟璇︽儏琛�
-- ----------------------------
DROP TABLE IF EXISTS `shop_order_detail`;
CREATE TABLE `shop_order_detail` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `goods_id` bigint(11) NOT NULL  COMMENT '鍟嗗搧id',
  `order_id` bigint(11) NOT NULL  COMMENT '璁㈠崟id',
  `user_id` varchar(200) NOT NULL COMMENT '鐢ㄦ埛id ',
  `num` int(11) NOT NULL COMMENT '鍟嗗搧鏁伴噺',
  `total_price` double(15,2) NOT NULL COMMENT '鎬讳环鏍�',
  `create_time` datetime DEFAULT NULL COMMENT '鍒涘缓鏃ユ湡',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `shopping_car`  购物车
-- ----------------------------
CREATE TABLE `shopping_car` (
	`id` BIGINT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`goods_id` BIGINT (11) NOT NULL COMMENT '商品id',
    `num` int(10) NOT NULL COMMENT '商品数量', 
	`user_id` VARCHAR (200) NOT NULL COMMENT '用户id ',
	`create_time` datetime DEFAULT NULL COMMENT '创建日期',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8;


CREATE TABLE `admin_user` (
  `id` varchar(40) NOT NULL,
  `name` varchar(20) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `email` varchar(40) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


