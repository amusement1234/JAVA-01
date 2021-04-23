-- mysql�˿ڣ�3316�����ݿ⣺myshop1����t_product_1��t_product_2
-- mysql�˿ڣ�3326�����ݿ⣺myshop2����t_product_1��t_product_2



create database myshop1;
use myshop1

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '����id',
  `order_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '�������',
  `order_status` int(0) NOT NULL COMMENT '����״̬',
  `order_total_amount` decimal(18, 2) NOT NULL COMMENT '�����ܽ��(ʵ��)',
  `order_total_qty` int(0) NULL DEFAULT NULL COMMENT '��������',
  `order_fee` int(0) NULL DEFAULT NULL COMMENT '�˷�',
  `product_total_amount` decimal(18, 2) NOT NULL COMMENT '��Ʒ�ܽ��',
  `is_delete` bit(1) NOT NULL COMMENT '�Ƿ���ɾ��',
  `create_time` datetime(0) NOT NULL COMMENT '����ʱ��',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `order_item_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '������ϸid',
  `order_item_qty` int(0) NULL DEFAULT NULL COMMENT '����',
  `product_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '��Ʒ����(�����ֶ�)',
  `product_id` int(0) NULL DEFAULT NULL COMMENT '��Ʒid',
  `order_id` int(0) NULL DEFAULT NULL COMMENT '����id',
  `is_delete` bit(1) NOT NULL COMMENT '�Ƿ���ɾ��',
  `create_time` datetime(0) NOT NULL COMMENT '����ʱ��',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`order_item_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '�û�id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '�û���',
  `user_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '����',
  `is_enable` bit(1) NOT NULL COMMENT '�Ƿ�����',
  `is_delete` bit(1) NOT NULL COMMENT '�Ƿ���ɾ��',
  `create_time` datetime(0) NOT NULL COMMENT '����ʱ��',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
