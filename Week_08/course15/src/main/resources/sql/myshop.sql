-- mysql端口：3316；数据库：myshop1；表：t_product_1、t_product_2
-- mysql端口：3326；数据库：myshop2；表：t_product_1、t_product_2



create database myshop1;
use myshop1

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `order_status` int(0) NOT NULL COMMENT '订单状态',
  `order_total_amount` decimal(18, 2) NOT NULL COMMENT '订单总金额(实收)',
  `order_total_qty` int(0) NULL DEFAULT NULL COMMENT '订单总数',
  `order_fee` int(0) NULL DEFAULT NULL COMMENT '运费',
  `product_total_amount` decimal(18, 2) NOT NULL COMMENT '商品总金额',
  `is_delete` bit(1) NOT NULL COMMENT '是否已删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `order_item_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '订单明细id',
  `order_item_qty` int(0) NULL DEFAULT NULL COMMENT '数量',
  `product_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称(冗余字段)',
  `product_id` int(0) NULL DEFAULT NULL COMMENT '商品id',
  `order_id` int(0) NULL DEFAULT NULL COMMENT '订单id',
  `is_delete` bit(1) NOT NULL COMMENT '是否已删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`order_item_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `user_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `is_enable` bit(1) NOT NULL COMMENT '是否启用',
  `is_delete` bit(1) NOT NULL COMMENT '是否已删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
