-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lylloan
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `lylloan`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `lylloan` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `lylloan`;

--
-- Table structure for table `gen_table`
--

DROP TABLE IF EXISTS `gen_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gen_table` (
  `table_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `tpl_web_type` varchar(30) DEFAULT '' COMMENT '前端模板类型（element-ui模版 element-plus模版）',
  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='代码生成业务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_table`
--

INSERT INTO `gen_table` VALUES (3,'sys_cache_info','缓存信息',NULL,NULL,'SysCacheInfoo','crud','element-plus','com.ruoyi.system','system','cacheInfo','缓存信息','ruoyi','1','H:\\Project\\lylloan\\module-generator\\src','{\"treeCode\":null,\"treeName\":null,\"treeParentCode\":null,\"parentMenuId\":\"1891530775170527233\"}',NULL,'2025-04-01 01:58:48','2025-04-06 04:38:11','',_binary '\0');
INSERT INTO `gen_table` VALUES (4,'sys_menu','菜单表','','','SysMenuu','tree','element-plus','com.ruoyi.system','system','menu','菜单','ruoyi','1','H:\\Project\\lylloan\\module-generator\\src','{\"treeCode\":\"id\",\"treeName\":\"name\",\"treeParentCode\":\"pid\",\"parentMenuId\":\"1891530775170527233\"}',NULL,'2025-04-04 23:52:31','2025-04-06 04:37:50','',_binary '\0');
INSERT INTO `gen_table` VALUES (5,'sys_user','用户表',NULL,NULL,'SysUserr','crud','element-plus','com.ruoyi.system','system','userr','用户','ruoyi','1','H:\\Project\\lylloan\\module-generator\\src','{\"treeCode\":null,\"treeName\":null,\"treeParentCode\":null,\"parentMenuId\":\"1891530775170527233\"}',NULL,'2025-04-06 04:40:57','2025-04-06 04:45:18','',_binary '\0');

--
-- Table structure for table `gen_table_column`
--

DROP TABLE IF EXISTS `gen_table_column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gen_table_column` (
  `column_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` bigint DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) DEFAULT '' COMMENT '字典类型',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='代码生成业务表字段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_table_column`
--

INSERT INTO `gen_table_column` VALUES (19,3,'id','主键','bigint','Long','id','1','1','0','1',NULL,NULL,NULL,'EQ','input','',1,'2025-04-01 01:58:48','2025-04-06 04:38:11','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (20,3,'name','键值','varchar(64)','String','name','0','0','1','1','1','1','1','LIKE','input','',2,'2025-04-01 01:58:48','2025-04-06 04:38:11','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (21,3,'remote_expire_time','远程存储库有效期','int','Long','remoteExpireTime','0','0','1','1','1','1','1','EQ','input','',3,'2025-04-01 01:58:48','2025-04-06 04:38:11','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (22,3,'local_expire_time','本地存储库有效期','int','Long','localExpireTime','0','0','0','1','1','1','1','EQ','input','',4,'2025-04-01 01:58:48','2025-04-06 04:38:11','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (23,3,'remark','','varchar(255)','String','remark','0','0','0','1','1','1',NULL,'EQ','input','',5,'2025-04-01 01:58:48','2025-04-06 04:38:11','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (24,3,'create_time','','datetime','LocalDateTime','createTime','0','0','0','0',NULL,NULL,NULL,'EQ','datetime','',6,'2025-04-01 01:58:48','2025-04-06 04:38:11','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (25,3,'update_time','','datetime','LocalDateTime','updateTime','0','0','0','0','0',NULL,NULL,'EQ','datetime','',7,'2025-04-01 01:58:48','2025-04-06 04:38:11','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (26,3,'updater','更新者','varchar(255)','String','updater','0','0','0','0','0','0','0','EQ','input','',8,'2025-04-01 01:58:48','2025-04-06 04:38:11','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (27,3,'deleted','是否删除','bit(1)','Boolean','deleted','0','0','0','0','0','0','0','EQ','input','',9,'2025-04-01 01:58:48','2025-04-06 04:38:11','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (28,4,'id','','bigint','Long','id','1','1','0','1',NULL,NULL,NULL,'EQ','input','',1,'2025-04-04 23:52:31','2025-04-06 04:37:50','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (29,4,'pid','父节点','bigint','Long','pid','0','0','0','1','1','1','1','EQ','input','',2,'2025-04-04 23:52:31','2025-04-06 04:37:50','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (30,4,'root_id','','bigint','Long','rootId','0','0','0','1','1','1','1','EQ','input','',3,'2025-04-04 23:52:31','2025-04-06 04:37:50','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (31,4,'name','菜单名','varchar(64)','String','name','0','0','1','1','1','1','1','LIKE','input','',4,'2025-04-04 23:52:31','2025-04-06 04:37:50','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (32,4,'router_path','路由','varchar(255)','String','routerPath','0','0','0','1','1','1','1','EQ','input','',5,'2025-04-04 23:52:31','2025-04-06 04:37:50','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (33,4,'component_path','组件路径','varchar(255)','String','componentPath','0','0','0','1','1','1','1','EQ','input','',6,'2025-04-04 23:52:31','2025-04-06 04:37:50','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (34,4,'resource_ids','权限路径','varchar(255)','String','resourceIds','0','0','0','1','1','1','1','EQ','input','',7,'2025-04-04 23:52:31','2025-04-06 04:37:50','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (35,4,'icon','菜单图标','varchar(100)','String','icon','0','0','0','1','1','1','1','EQ','input','',8,'2025-04-04 23:52:31','2025-04-06 04:37:50','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (36,4,'keep_alive','是否缓存','bit(1)','Integer','keepAlive','0','0','1','1','1','1','1','EQ','input','',9,'2025-04-04 23:52:31','2025-04-06 04:37:50','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (37,4,'status','菜单状态（0正常  1隐藏）','char(1)','String','status','0','0','0','1','1','1','1','EQ','radio','',10,'2025-04-04 23:52:31','2025-04-06 04:37:50','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (38,4,'type','组件类型[0:目录,1:菜单,2:按钮]','int','Long','type','0','0','0','1','1','1','1','EQ','select','',11,'2025-04-04 23:52:31','2025-04-06 04:37:50','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (39,4,'sort','排序','int','Long','sort','0','0','1','1','1','1','1','EQ','input','',12,'2025-04-04 23:52:31','2025-04-06 04:37:50','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (40,4,'remark','备注','varchar(500)','String','remark','0','0','0','1','1','1',NULL,'EQ','textarea','',13,'2025-04-04 23:52:31','2025-04-06 04:37:50','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (42,4,'create_time','','datetime','LocalDateTime','createTime','0','0','0','0',NULL,NULL,NULL,'EQ','datetime','',14,'2025-04-04 23:52:31','2025-04-06 04:37:50','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (43,4,'update_time','','datetime','LocalDateTime','updateTime','0','0','0','0','0',NULL,NULL,'EQ','datetime','',15,'2025-04-04 23:52:31','2025-04-06 04:37:50','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (44,4,'updater','更新者','varchar(255)','String','updater','0','0','0','0','0','0','0','EQ','input','',16,'2025-04-04 23:52:31','2025-04-06 04:37:50','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (45,4,'deleted','是否删除','bit(1)','Boolean','deleted','0','0','1','0','0','0','0','EQ','input','',17,'2025-04-04 23:52:31','2025-04-06 04:37:50','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (46,5,'id','主键','bigint','Long','id','1','0','0','1',NULL,NULL,NULL,'EQ','input','',1,'2025-04-06 04:40:57','2025-04-06 04:45:18','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (47,5,'user_name','用户名','varchar(64)','String','userName','0','0','1','1','1','1','1','LIKE','input','',2,'2025-04-06 04:40:57','2025-04-06 04:45:18','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (48,5,'password','密码','varchar(64)','String','password','0','0','1','0','0','1','0','EQ','input','',3,'2025-04-06 04:40:57','2025-04-06 04:45:18','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (49,5,'nick_name','昵称','varchar(64)','String','nickName','0','0','1','1','1','1','1','LIKE','input','',4,'2025-04-06 04:40:57','2025-04-06 04:45:18','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (50,5,'dept_id','部门id','bigint','Long','deptId','0','0','0','1','1','1','1','EQ','input','',5,'2025-04-06 04:40:57','2025-04-06 04:45:18','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (51,5,'email','邮箱','varchar(64)','String','email','0','0','0','1','1','1','1','EQ','input','',6,'2025-04-06 04:40:57','2025-04-06 04:45:18','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (52,5,'phone_number','手机号','varchar(32)','String','phoneNumber','0','0','0','1','1','1','1','EQ','input','',7,'2025-04-06 04:40:57','2025-04-06 04:45:18','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (53,5,'sex','用户性别（0男，1女，2未知）','char(1)','String','sex','0','0','1','1','1','1','1','EQ','select','',8,'2025-04-06 04:40:57','2025-04-06 04:45:18','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (54,5,'avatar','头像','varchar(128)','String','avatar','0','0','0','1','1','1','1','EQ','input','',9,'2025-04-06 04:40:57','2025-04-06 04:45:18','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (55,5,'remark','备注','varchar(255)','String','remark','0','0','0','1','1','1',NULL,'EQ','input','',10,'2025-04-06 04:40:57','2025-04-06 04:45:18','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (56,5,'status','账号状态（0正常 1停用）','char(1)','String','status','0','0','0','1','1','1','1','EQ','radio','',11,'2025-04-06 04:40:57','2025-04-06 04:45:18','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (57,5,'login_ip',NULL,'varchar(15)','String','loginIp','0','0','0','1','1','1','1','EQ','input','',12,'2025-04-06 04:40:57','2025-04-06 04:45:18','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (58,5,'login_date',NULL,'datetime','LocalDateTime','loginDate','0','0','0','1','1','1','1','EQ','datetime','',13,'2025-04-06 04:40:57','2025-04-06 04:45:18','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (59,5,'create_time',NULL,'datetime','LocalDateTime','createTime','0','0','0','0',NULL,NULL,NULL,'EQ','datetime','',14,'2025-04-06 04:40:57','2025-04-06 04:45:18','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (60,5,'update_time',NULL,'datetime','LocalDateTime','updateTime','0','0','0','0','0',NULL,NULL,'EQ','datetime','',15,'2025-04-06 04:40:57','2025-04-06 04:45:18','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (61,5,'updater','更新者','varchar(255)','String','updater','0','0','0','0','0','0','0','EQ','input','',16,'2025-04-06 04:40:57','2025-04-06 04:45:18','',_binary '\0');
INSERT INTO `gen_table_column` VALUES (62,5,'deleted','是否删除','bit(1)','Boolean','deleted','0','0','0','0','0','0','0','EQ','input','',17,'2025-04-06 04:40:57','2025-04-06 04:45:18','',_binary '\0');

--
-- Table structure for table `sys_cache_info`
--

DROP TABLE IF EXISTS `sys_cache_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_cache_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '键值',
  `remote_expire_time` int NOT NULL COMMENT '远程存储库有效期',
  `local_expire_time` int DEFAULT '-1' COMMENT '本地存储库有效期',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `sys_cache_pk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COMMENT='缓存信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_cache_info`
--

INSERT INTO `sys_cache_info` VALUES (1,'auth_user',1024000,-1,'登录过期时间','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');

--
-- Table structure for table `sys_data_permission_function_meta`
--

DROP TABLE IF EXISTS `sys_data_permission_function_meta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_data_permission_function_meta` (
  `id` bigint NOT NULL COMMENT 'Metaid',
  `category` varchar(255) DEFAULT '' COMMENT '分类',
  `table_name` varchar(255) DEFAULT NULL COMMENT '表名',
  `column_name` varchar(255) DEFAULT '0' COMMENT '字段名',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据权限-元数据';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_data_permission_function_meta`
--

INSERT INTO `sys_data_permission_function_meta` VALUES (0,'DEPT','sys_user','dept_id','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_data_permission_function_meta` VALUES (1870747710253158402,'DEPT','qq','qq','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_data_permission_function_meta` VALUES (1870753025950470146,'DEPT','aaaa','aaaaa','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');

--
-- Table structure for table `sys_data_permission_user_dept`
--

DROP TABLE IF EXISTS `sys_data_permission_user_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_data_permission_user_dept` (
  `id` bigint NOT NULL COMMENT '映射id',
  `user_id` bigint DEFAULT NULL COMMENT 'userId',
  `match_type` bigint DEFAULT NULL COMMENT '块级匹配类型',
  `target` bigint DEFAULT NULL COMMENT '元素级匹配',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据权限-User-Dept映射';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_data_permission_user_dept`
--

INSERT INTO `sys_data_permission_user_dept` VALUES (1871972118569320449,1832655526807842818,NULL,257,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_data_permission_user_dept` VALUES (1871972118607069185,1832655526807842818,1,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_data_permission_user_dept` VALUES (1875959874639360001,1,1,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');

--
-- Table structure for table `sys_demo_file`
--

DROP TABLE IF EXISTS `sys_demo_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_demo_file` (
  `object_name` varchar(255) NOT NULL,
  `data` longblob,
  PRIMARY KEY (`object_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='演示环境_文件系统';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_demo_file`
--


--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dept` (
  `id` bigint NOT NULL COMMENT '部门id',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `leader_id` bigint DEFAULT NULL COMMENT '负责人',
  `sort` int DEFAULT '0' COMMENT '显示顺序',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

INSERT INTO `sys_dept` VALUES (1,'ChainXi',1,0,'0','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dept` VALUES (257,'ChainXi_child',1832655526807842818,0,'0','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dept` VALUES (513,'ChainXi_child',1,0,'0','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dept` VALUES (769,'ChainXi_child',1,0,'0','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dept` VALUES (1025,'ChainXi_child',1,0,'0','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');

--
-- Table structure for table `sys_dict_data`
--

DROP TABLE IF EXISTS `sys_dict_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict_data` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `sort` int NOT NULL DEFAULT '0' COMMENT '字典排序',
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典标签',
  `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典类型',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `color_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '颜色类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'css 样式',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1353 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='字典数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_data`
--

INSERT INTO `sys_dict_data` VALUES (1,1,'男','1','system_user_sex',0,'primary','A','性别男','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (2,2,'女','2','system_user_sex',0,'success','','性别女','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (8,1,'正常','1','infra_job_status',0,'success','','正常状态','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (9,2,'暂停','2','infra_job_status',0,'danger','','停用状态','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (12,1,'系统内置','1','infra_config_type',0,'danger','','参数类型 - 系统内置','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (13,2,'自定义','2','infra_config_type',0,'primary','','参数类型 - 自定义','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (14,1,'通知','1','system_notice_type',0,'success','','通知','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (15,2,'公告','2','system_notice_type',0,'info','','公告','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (16,0,'其它','0','system_operate_type',0,'primary','','其它操作','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (17,1,'查询','1','system_operate_type',0,'info','','查询操作','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (18,2,'新增','2','system_operate_type',0,'primary','','新增操作','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (19,3,'修改','3','system_operate_type',0,'warning','','修改操作','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (20,4,'删除','4','system_operate_type',0,'danger','','删除操作','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (22,5,'导出','5','system_operate_type',0,'primary','','导出操作','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (23,6,'导入','6','system_operate_type',0,'primary','','导入操作','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (27,1,'启用','0','common_status',0,'primary','','开启状态','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (28,2,'禁用','1','common_status',0,'info','','关闭状态','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (29,1,'目录','0','system_menu_type',0,'','','目录','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (30,2,'菜单','1','system_menu_type',0,'success','','菜单','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (31,3,'按钮','2','system_menu_type',0,'info','','按钮','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (32,1,'内置','1','system_role_type',0,'danger','','内置角色','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (33,2,'自定义','2','system_role_type',0,'primary','','自定义角色','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (34,1,'全部数据权限','1','system_data_scope',0,'','','全部数据权限','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (35,2,'指定部门数据权限','2','system_data_scope',0,'','','指定部门数据权限','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (36,3,'本部门数据权限','3','system_data_scope',0,'','','本部门数据权限','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (37,4,'本部门及以下数据权限','4','system_data_scope',0,'','','本部门及以下数据权限','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (38,5,'仅本人数据权限','5','system_data_scope',0,'','','仅本人数据权限','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (39,0,'成功','0','system_login_result',0,'success','','登陆结果 - 成功','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (40,10,'账号或密码不正确','10','system_login_result',0,'primary','','登陆结果 - 账号或密码不正确','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (41,20,'用户被禁用','20','system_login_result',0,'warning','','登陆结果 - 用户被禁用','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (42,30,'验证码不存在','30','system_login_result',0,'info','','登陆结果 - 验证码不存在','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (43,31,'验证码不正确','31','system_login_result',0,'info','','登陆结果 - 验证码不正确','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (44,100,'未知异常','100','system_login_result',0,'danger','','登陆结果 - 未知异常','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (45,1,'是','true','infra_boolean_string',0,'danger','','Boolean 是否类型 - 是','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (46,1,'否','false','infra_boolean_string',0,'info','','Boolean 是否类型 - 否','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (50,1,'单表（增删改查）','1','infra_codegen_template_type',0,'','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (51,2,'树表（增删改查）','2','infra_codegen_template_type',0,'','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (53,0,'初始化中','0','infra_job_status',0,'primary','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (57,0,'运行中','0','infra_job_log_status',0,'primary','','RUNNING','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (58,1,'成功','1','infra_job_log_status',0,'success','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (59,2,'失败','2','infra_job_log_status',0,'warning','','失败','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (60,1,'会员','1','user_type',0,'primary','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (61,2,'管理员','2','user_type',0,'success','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (62,0,'未处理','0','infra_api_error_log_process_status',0,'primary','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (63,1,'已处理','1','infra_api_error_log_process_status',0,'success','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (64,2,'已忽略','2','infra_api_error_log_process_status',0,'danger','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (66,2,'阿里云','ALIYUN','system_sms_channel_code',0,'primary','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (67,1,'验证码','1','system_sms_template_type',0,'warning','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (68,2,'通知','2','system_sms_template_type',0,'primary','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (69,0,'营销','3','system_sms_template_type',0,'danger','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (70,0,'初始化','0','system_sms_send_status',0,'primary','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (71,1,'发送成功','10','system_sms_send_status',0,'success','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (72,2,'发送失败','20','system_sms_send_status',0,'danger','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (73,3,'不发送','30','system_sms_send_status',0,'info','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (74,0,'等待结果','0','system_sms_receive_status',0,'primary','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (75,1,'接收成功','10','system_sms_receive_status',0,'success','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (76,2,'接收失败','20','system_sms_receive_status',0,'danger','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (77,0,'调试(钉钉)','DEBUG_DING_TALK','system_sms_channel_code',0,'info','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (78,1,'自动生成','1','system_error_code_type',0,'warning','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (79,2,'手动编辑','2','system_error_code_type',0,'primary','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (80,100,'账号登录','100','system_login_type',0,'primary','','账号登录','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (81,101,'社交登录','101','system_login_type',0,'info','','社交登录','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (83,200,'主动登出','200','system_login_type',0,'primary','','主动登出','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (85,202,'强制登出','202','system_login_type',0,'danger','','强制退出','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (86,0,'病假','1','bpm_oa_leave_type',0,'primary','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (87,1,'事假','2','bpm_oa_leave_type',0,'info','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (88,2,'婚假','3','bpm_oa_leave_type',0,'warning','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (113,1,'微信公众号支付','wx_pub','pay_channel_code',0,'success','','微信公众号支付','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (114,2,'微信小程序支付','wx_lite','pay_channel_code',0,'success','','微信小程序支付','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (115,3,'微信 App 支付','wx_app','pay_channel_code',0,'success','','微信 App 支付','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (116,10,'支付宝 PC 网站支付','alipay_pc','pay_channel_code',0,'primary','','支付宝 PC 网站支付','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (117,11,'支付宝 Wap 网站支付','alipay_wap','pay_channel_code',0,'primary','','支付宝 Wap 网站支付','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (118,12,'支付宝 App 支付','alipay_app','pay_channel_code',0,'primary','','支付宝 App 支付','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (119,14,'支付宝扫码支付','alipay_qr','pay_channel_code',0,'primary','','支付宝扫码支付','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (120,10,'通知成功','10','pay_notify_status',0,'success','','通知成功','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (121,20,'通知失败','20','pay_notify_status',0,'danger','','通知失败','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (122,0,'等待通知','0','pay_notify_status',0,'info','','未通知','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (123,10,'支付成功','10','pay_order_status',0,'success','','支付成功','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (124,30,'支付关闭','30','pay_order_status',0,'info','','支付关闭','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (125,0,'等待支付','0','pay_order_status',0,'info','','未支付','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1118,0,'等待退款','0','pay_refund_status',0,'info','','等待退款','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1119,20,'退款失败','20','pay_refund_status',0,'danger','','退款失败','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1124,10,'退款成功','10','pay_refund_status',0,'success','','退款成功','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1125,0,'默认','1','bpm_model_category',0,'primary','','流程分类 - 默认','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1126,0,'OA','2','bpm_model_category',0,'success','','流程分类 - OA','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1127,0,'进行中','1','bpm_process_instance_status',0,'primary','','流程实例的状态 - 进行中','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1128,2,'已完成','2','bpm_process_instance_status',0,'success','','流程实例的状态 - 已完成','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1129,1,'处理中','1','bpm_process_instance_result',0,'primary','','流程实例的结果 - 处理中','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1130,2,'通过','2','bpm_process_instance_result',0,'success','','流程实例的结果 - 通过','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1131,3,'不通过','3','bpm_process_instance_result',0,'danger','','流程实例的结果 - 不通过','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1132,4,'已取消','4','bpm_process_instance_result',0,'info','','流程实例的结果 - 撤销','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1133,10,'流程表单','10','bpm_model_form_type',0,'','','流程的表单类型 - 流程表单','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1134,20,'业务表单','20','bpm_model_form_type',0,'','','流程的表单类型 - 业务表单','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1135,10,'角色','10','bpm_task_assign_rule_type',0,'info','','任务分配规则的类型 - 角色','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1136,20,'部门的成员','20','bpm_task_assign_rule_type',0,'primary','','任务分配规则的类型 - 部门的成员','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1137,21,'部门的负责人','21','bpm_task_assign_rule_type',0,'primary','','任务分配规则的类型 - 部门的负责人','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1138,30,'用户','30','bpm_task_assign_rule_type',0,'info','','任务分配规则的类型 - 用户','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1139,40,'用户组','40','bpm_task_assign_rule_type',0,'warning','','任务分配规则的类型 - 用户组','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1140,50,'自定义脚本','50','bpm_task_assign_rule_type',0,'danger','','任务分配规则的类型 - 自定义脚本','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1141,22,'岗位','22','bpm_task_assign_rule_type',0,'success','','任务分配规则的类型 - 岗位','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1142,10,'流程发起人','10','bpm_task_assign_script',0,'','','任务分配自定义脚本 - 流程发起人','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1143,20,'流程发起人的一级领导','20','bpm_task_assign_script',0,'','','任务分配自定义脚本 - 流程发起人的一级领导','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1144,21,'流程发起人的二级领导','21','bpm_task_assign_script',0,'','','任务分配自定义脚本 - 流程发起人的二级领导','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1145,1,'管理后台','1','infra_codegen_scene',0,'','','代码生成的场景枚举 - 管理后台','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1146,2,'用户 APP','2','infra_codegen_scene',0,'','','代码生成的场景枚举 - 用户 APP','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1150,1,'数据库','1','infra_file_storage',0,'primary','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1151,10,'本地磁盘','10','infra_file_storage',0,'primary','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1152,11,'FTP 服务器','11','infra_file_storage',0,'primary','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1153,12,'SFTP 服务器','12','infra_file_storage',0,'primary','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1154,20,'S3 对象存储','20','infra_file_storage',0,'primary','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1155,103,'短信登录','103','system_login_type',0,'primary','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1156,1,'password','password','system_oauth2_grant_type',0,'primary','','密码模式','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1157,2,'authorization_code','authorization_code','system_oauth2_grant_type',0,'primary','','授权码模式','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1158,3,'implicit','implicit','system_oauth2_grant_type',0,'success','','简化模式','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1159,4,'client_credentials','client_credentials','system_oauth2_grant_type',0,'primary','','客户端模式','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1160,5,'refresh_token','refresh_token','system_oauth2_grant_type',0,'info','','刷新模式','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1162,1,'销售中','1','product_spu_status',0,'success','','商品 SPU 状态 - 销售中','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1163,0,'仓库中','0','product_spu_status',0,'info','','商品 SPU 状态 - 仓库中','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1164,0,'回收站','-1','product_spu_status',0,'primary','','商品 SPU 状态 - 回收站','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1165,1,'满减','1','promotion_discount_type',0,'success','','优惠类型 - 满减','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1166,2,'折扣','2','promotion_discount_type',0,'primary','','优惠类型 - 折扣','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1167,1,'固定日期','1','promotion_coupon_template_validity_type',0,'primary','','优惠劵模板的有限期类型 - 固定日期','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1168,2,'领取之后','2','promotion_coupon_template_validity_type',0,'primary','','优惠劵模板的有限期类型 - 领取之后','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1169,1,'全部商品参与','1','promotion_product_scope',0,'primary','','营销的商品范围 - 全部商品参与','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1170,2,'指定商品参与','2','promotion_product_scope',0,'primary','','营销的商品范围 - 指定商品参与','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1171,1,'已领取','1','promotion_coupon_status',0,'primary','','优惠劵的状态 - 已领取','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1172,2,'已使用','2','promotion_coupon_status',0,'success','','优惠劵的状态 - 已使用','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1173,3,'已过期','3','promotion_coupon_status',0,'info','','优惠劵的状态 - 已过期','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1174,1,'直接领取','1','promotion_coupon_take_type',0,'primary','','优惠劵的领取方式 - 直接领取','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1175,2,'指定发放','2','promotion_coupon_take_type',0,'success','','优惠劵的领取方式 - 指定发放','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1176,10,'未开始','10','promotion_activity_status',0,'primary','','促销活动的状态枚举 - 未开始','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1177,20,'进行中','20','promotion_activity_status',0,'success','','促销活动的状态枚举 - 进行中','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1178,30,'已结束','30','promotion_activity_status',0,'info','','促销活动的状态枚举 - 已结束','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1179,40,'已关闭','40','promotion_activity_status',0,'warning','','促销活动的状态枚举 - 已关闭','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1180,10,'满 N 元','10','promotion_condition_type',0,'primary','','营销的条件类型 - 满 N 元','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1181,20,'满 N 件','20','promotion_condition_type',0,'success','','营销的条件类型 - 满 N 件','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1182,10,'申请售后','10','trade_after_sale_status',0,'primary','','交易售后状态 - 申请售后','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1183,20,'商品待退货','20','trade_after_sale_status',0,'primary','','交易售后状态 - 商品待退货','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1184,30,'商家待收货','30','trade_after_sale_status',0,'primary','','交易售后状态 - 商家待收货','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1185,40,'等待退款','40','trade_after_sale_status',0,'primary','','交易售后状态 - 等待退款','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1186,50,'退款成功','50','trade_after_sale_status',0,'primary','','交易售后状态 - 退款成功','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1187,61,'买家取消','61','trade_after_sale_status',0,'info','','交易售后状态 - 买家取消','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1188,62,'商家拒绝','62','trade_after_sale_status',0,'info','','交易售后状态 - 商家拒绝','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1189,63,'商家拒收货','63','trade_after_sale_status',0,'info','','交易售后状态 - 商家拒收货','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1190,10,'售中退款','10','trade_after_sale_type',0,'success','','交易售后的类型 - 售中退款','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1191,20,'售后退款','20','trade_after_sale_type',0,'primary','','交易售后的类型 - 售后退款','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1192,10,'仅退款','10','trade_after_sale_way',0,'primary','','交易售后的方式 - 仅退款','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1193,20,'退货退款','20','trade_after_sale_way',0,'success','','交易售后的方式 - 退货退款','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1194,10,'微信小程序','10','terminal',0,'primary','','终端 - 微信小程序','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1195,20,'H5 网页','20','terminal',0,'primary','','终端 - H5 网页','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1196,11,'微信公众号','11','terminal',0,'primary','','终端 - 微信公众号','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1197,31,'苹果 App','31','terminal',0,'primary','','终端 - 苹果 App','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1198,32,'安卓 App','32','terminal',0,'primary','','终端 - 安卓 App','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1199,0,'普通订单','0','trade_order_type',0,'primary','','交易订单的类型 - 普通订单','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1200,1,'秒杀订单','1','trade_order_type',0,'primary','','交易订单的类型 - 秒杀订单','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1201,2,'拼团订单','2','trade_order_type',0,'primary','','交易订单的类型 - 拼团订单','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1202,3,'砍价订单','3','trade_order_type',0,'primary','','交易订单的类型 - 砍价订单','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1203,0,'待支付','0','trade_order_status',0,'primary','','交易订单状态 - 待支付','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1204,10,'待发货','10','trade_order_status',0,'primary','','交易订单状态 - 待发货','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1205,20,'已发货','20','trade_order_status',0,'primary','','交易订单状态 - 已发货','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1206,30,'已完成','30','trade_order_status',0,'success','','交易订单状态 - 已完成','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1207,40,'已取消','40','trade_order_status',0,'danger','','交易订单状态 - 已取消','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1208,0,'未售后','0','trade_order_item_after_sale_status',0,'info','','交易订单项的售后状态 - 未售后','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1209,1,'售后中','1','trade_order_item_after_sale_status',0,'primary','','交易订单项的售后状态 - 售后中','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1210,2,'已退款','2','trade_order_item_after_sale_status',0,'success','','交易订单项的售后状态 - 已退款','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1211,1,'完全匹配','1','mp_auto_reply_request_match',0,'primary','','公众号自动回复的请求关键字匹配模式 - 完全匹配','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1212,2,'半匹配','2','mp_auto_reply_request_match',0,'success','','公众号自动回复的请求关键字匹配模式 - 半匹配','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1213,1,'文本','text','mp_message_type',0,'primary','','公众号的消息类型 - 文本','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1214,2,'图片','image','mp_message_type',0,'primary','','公众号的消息类型 - 图片','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1215,3,'语音','voice','mp_message_type',0,'primary','','公众号的消息类型 - 语音','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1216,4,'视频','video','mp_message_type',0,'primary','','公众号的消息类型 - 视频','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1217,5,'小视频','shortvideo','mp_message_type',0,'primary','','公众号的消息类型 - 小视频','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1218,6,'图文','news','mp_message_type',0,'primary','','公众号的消息类型 - 图文','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1219,7,'音乐','music','mp_message_type',0,'primary','','公众号的消息类型 - 音乐','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1220,8,'地理位置','location','mp_message_type',0,'primary','','公众号的消息类型 - 地理位置','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1221,9,'链接','link','mp_message_type',0,'primary','','公众号的消息类型 - 链接','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1222,10,'事件','event','mp_message_type',0,'primary','','公众号的消息类型 - 事件','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1223,0,'初始化','0','system_mail_send_status',0,'primary','','邮件发送状态 - 初始化\n','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1224,10,'发送成功','10','system_mail_send_status',0,'success','','邮件发送状态 - 发送成功','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1225,20,'发送失败','20','system_mail_send_status',0,'danger','','邮件发送状态 - 发送失败','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1226,30,'不发送','30','system_mail_send_status',0,'info','','邮件发送状态 -  不发送','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1227,1,'通知公告','1','system_notify_template_type',0,'primary','','站内信模版的类型 - 通知公告','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1228,2,'系统消息','2','system_notify_template_type',0,'success','','站内信模版的类型 - 系统消息','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1230,13,'支付宝条码支付','alipay_bar','pay_channel_code',0,'primary','','支付宝条码支付','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1231,10,'Vue2 Element UI 标准模版','10','infra_codegen_front_type',0,'','','','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1232,20,'Vue3 Element Plus 标准模版','20','infra_codegen_front_type',0,'','','','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1233,21,'Vue3 Element Plus Schema 模版','21','infra_codegen_front_type',0,'','','','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1234,30,'Vue3 vben 模版','30','infra_codegen_front_type',0,'','','','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1235,1,'个','1','product_unit',0,'','','','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1236,1,'件','2','product_unit',0,'','','','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1237,1,'盒','3','product_unit',0,'','','','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1238,1,'袋','4','product_unit',0,'','','','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1239,1,'箱','5','product_unit',0,'','','','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1240,1,'套','6','product_unit',0,'','','','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1241,1,'包','7','product_unit',0,'','','','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1242,1,'双','8','product_unit',0,'','','','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1243,1,'卷','9','product_unit',0,'','','','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1244,0,'按件','1','trade_delivery_express_charge_mode',0,'','','','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1245,1,'按重量','2','trade_delivery_express_charge_mode',0,'','','','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1246,2,'按体积','3','trade_delivery_express_charge_mode',0,'','','','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1335,11,'订单消费','11','member_point_biz_type',0,'','','','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1336,1,'签到','1','member_point_biz_type',0,'','','','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1341,20,'已退款','20','pay_order_status',0,'danger','','已退款','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1342,21,'请求成功，但是结果失败','21','pay_notify_status',0,'warning','','请求成功，但是结果失败','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1343,22,'请求失败','22','pay_notify_status',0,'warning','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1344,4,'微信扫码支付','wx_native','pay_channel_code',0,'success','','微信扫码支付','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1345,5,'微信条码支付','wx_bar','pay_channel_code',0,'success','','微信条码支付\n','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1346,1,'支付单','1','pay_notify_type',0,'primary','','支付单','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1347,2,'退款单','2','pay_notify_type',0,'danger','',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1348,20,'模拟支付','mock','pay_channel_code',0,'primary','','模拟支付','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1349,12,'订单取消','12','member_point_biz_type',0,'','','','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1350,0,'显示','0','system_menu_status',0,'primary','','系统菜单状态','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1351,0,'隐藏','1','system_menu_status',0,'success','','系统菜单状态','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_data` VALUES (1352,0,'禁用','2','system_menu_status',0,'primary','','系统菜单状态','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');

--
-- Table structure for table `sys_dict_type`
--

DROP TABLE IF EXISTS `sys_dict_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典名称',
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '字典类型',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `deleted_time` datetime DEFAULT NULL COMMENT '删除时间',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `dict_type` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1741542027210379267 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='字典类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_type`
--

INSERT INTO `sys_dict_type` VALUES (1,'用户性别','system_user_sex',0,NULL,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_type` VALUES (6,'参数类型','infra_config_type',0,NULL,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_type` VALUES (7,'通知类型','system_notice_type',0,NULL,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_type` VALUES (9,'操作类型','system_operate_type',0,NULL,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_type` VALUES (10,'系统状态','common_status',0,NULL,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_dict_type` VALUES (11,'Boolean 是否类型','infra_boolean_string',0,'boolean 转是否',NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pid` bigint DEFAULT NULL COMMENT '父节点',
  `root_id` bigint DEFAULT NULL,
  `name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '菜单名',
  `router_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '路由',
  `component_path` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `resource_ids` varchar(255) DEFAULT NULL COMMENT '权限路径',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `keep_alive` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否缓存',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常  1隐藏）',
  `type` int DEFAULT '0' COMMENT '组件类型[0:目录,1:菜单,2:按钮]',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT '' COMMENT '更新者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_menu_name_uindex` (`name`),
  KEY `sys_menu__sort` (`sort`)
) ENGINE=InnoDB AUTO_INCREMENT=1896235964309843992 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

INSERT INTO `sys_menu` VALUES (0,NULL,0,'adminRoot',NULL,NULL,'[1]','#',_binary '','0',0,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (1,0,0,'menu.home','/home','/home','[1]','ep:home-filled',_binary '','0',1,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (2,3,0,'menu.userManager','/user','/sys/user','[]','ep:avatar',_binary '','0',1,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (3,0,0,'menu.sys','/sys',NULL,'[]','fa-solid:atom',_binary '','0',0,1,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (4,3,0,'menu.menuManager','/menu','/sys/menu','[]','ep:comment',_binary '','0',1,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (5,4,0,'action.save',NULL,NULL,'[]','#',_binary '','0',2,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (6,4,0,'action.update',NULL,NULL,'[]','#',_binary '','0',2,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (7,4,0,'action.delete',NULL,NULL,'[]','#',_binary '','0',2,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (8,4,0,'action.query',NULL,'','[]','',_binary '','0',2,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (9,3,0,'menu.dictManager','/dict-type','/sys/dict/type','[]','ep:notebook',_binary '','0',1,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (10,9,0,'menu.dictDataManager','/dict-data/:pathMatch(.*)','/sys/dict/type/data','[]','ep:document-copy',_binary '','1',1,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (11,3,0,'menu.roleManager','/role','/sys/role','[]','fa:user-secret',_binary '','0',1,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (12,0,0,'menu.infra','/infra','','[]','ep:office-building',_binary '','0',0,2,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (13,12,0,'menu.redis','/redis','/infra/redis','','fa:database',_binary '','0',1,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (14,0,0,'menu.profile','/profile','/profile','[]','ep:info-filled',_binary '','1',1,0,NULL,'2025-03-02 00:00:00','2025-04-13 22:35:53','sys:1');
INSERT INTO `sys_menu` VALUES (15,12,0,'menu.serverMonitor','/server-monitor','/infra/server','[]','ep:monitor',_binary '\0','0',1,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (16,12,0,'menu.druid','/druid','/infra/druid','','fa:bullseye',_binary '\0','0',1,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (17,3,0,'menu.tokenManager','/token-manager','/sys/token','[]','ep:key',_binary '','0',1,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (18,3,0,'menu.cacheManager','/cache-manager','/sys/cache','[]','ep:sort',_binary '','0',1,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (19,3,0,'menu.resourceManager','/resource','/sys/resource','[]','fa-solid:key',_binary '','0',1,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (20,3,0,'menu.deptManager','/dept-manager','/sys/dept','[]','ep:data-line',_binary '','0',1,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (21,3,0,'menu.oauth','/oauth2-client','/sys/oauth2','[]','ep:burger',_binary '','0',1,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (22,0,0,'menu.tools','/tool','','[]','fa-solid:tools',_binary '','0',0,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (23,22,0,'menu.build','/build','/tool/build','[]','ep:office-building',_binary '','0',1,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (24,22,0,'menu.generator','/gen','/tool/gen','[]','ep:edit-pen',_binary '','0',1,0,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','');
INSERT INTO `sys_menu` VALUES (25,22,0,'menu.build-column','/build/column/:tableId(\\d+)','/tool/gen/editTable','[]','ep:watch',_binary '','1',1,0,NULL,'2025-03-03 00:27:39','2025-03-07 00:38:28',NULL);

--
-- Table structure for table `sys_oauth2_client`
--

DROP TABLE IF EXISTS `sys_oauth2_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_oauth2_client` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `client_id` bigint NOT NULL COMMENT '客户端编号',
  `secret` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端密钥',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '应用名',
  `origin` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `logout_call` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '应用描述',
  `access_token_validity_seconds` int NOT NULL COMMENT '访问令牌的有效期',
  `refresh_token_validity_seconds` int NOT NULL COMMENT '刷新令牌的有效期',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='OAuth2 客户端表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_oauth2_client`
--

INSERT INTO `sys_oauth2_client` VALUES (43,0,'123456','server','localhost:8080','/','认证中心',1,1,'2025-03-02 00:00:00','2025-04-07 00:04:06',NULL,_binary '\0');
INSERT INTO `sys_oauth2_client` VALUES (44,1,'1','job','localhost:8070','http://localhost:8070/xxl-job-admin/oauth/client/logout-call','定时任务',1,1,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');

--
-- Table structure for table `sys_ra_token`
--

DROP TABLE IF EXISTS `sys_ra_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_ra_token` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `client_id` bigint DEFAULT NULL,
  `did` bigint NOT NULL DEFAULT '-1',
  `access_token` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '授权令牌',
  `at_expires_time` datetime DEFAULT NULL COMMENT '授权令牌过期时间',
  `refresh_token` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '刷新令牌',
  `expires_time` datetime NOT NULL COMMENT '过期时间',
  `tenant_id` bigint NOT NULL DEFAULT '0' COMMENT '租户编号',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1911841832695726082 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='OAuth2 刷新令牌';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_ra_token`
--

INSERT INTO `sys_ra_token` VALUES (1833546945219112961,1,NULL,0,'c5ed0760d27f475dbd9bd273d592e2ec','2024-09-11 01:43:52','b68215caf4f64587870914d3d180cd86','2024-09-11 02:43:52',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1834615337741033474,1,NULL,0,'41ada785e293459897f8498b68df5a58','2024-09-14 00:29:17','b59f1111f992406caef55a8432484357','2024-09-14 01:29:17',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1838577319540285441,1,NULL,0,'7aacfb0466694ddb819f75e56f0811cc','2024-09-24 23:30:37','fd06f7d2f2d7446b85a278489d5880c5','2024-09-24 23:52:47',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1839006786481901569,1,NULL,0,'545b83cdc76e489288fc16fbbcf96324','2024-09-26 03:28:02','c9deee2f499b46de8de7263fd95be99f','2024-09-26 04:19:20',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1839009393204088834,1,NULL,0,'67099fd291ce4623a817d666b2615d42','2024-09-26 03:33:23','7123f48bc8ac4a28950a6ccedd18a7a4','2024-09-26 04:29:41',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1839347003633975298,1,NULL,0,'f7324a6de151490caa0a5bff99f7ccf0','2024-09-27 02:16:52','8e51c3aa61f14870b9732d72cd060ae1','2024-09-27 02:51:14',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1840305171247738882,1,NULL,0,'332268bb80224576b1c971fcb72e54c4','2024-09-29 17:18:39','13bf5e96b0b447fba5128a31b4a15f33','2024-09-29 18:18:39',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1841521130063069186,1,NULL,0,'ce7d3f0b82424f4cb5b2adc4647ccac1','2024-10-03 01:50:26','ee5287883ec64e479372e63f8239f990','2024-10-03 02:50:26',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1841889637728522241,1,NULL,0,'3e5f2b4455884a2286aea3acce609830','2024-10-04 02:58:41','562192d6573f44f689b9961cd261d9db','2024-10-04 03:14:45',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1842102523394564097,1,NULL,0,'63906c02937c464bb8d969344a0ae029','2024-10-04 17:06:42','ee09083125e54f758ad641d2717a4743','2024-10-04 17:20:41',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1842156130496196610,1,NULL,0,'4253e94a213548aea769183371ecd84c','2024-10-04 20:30:18','38f568e34f144f7dbcbe0a034175892a','2024-10-04 20:53:42',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1842166999418449922,1,NULL,0,'a938b3d22ee741f4882ab7a18ab11143','2024-10-04 21:16:15','577b1a23e01b4863b214d7eca5130bfc','2024-10-04 21:36:53',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1842587726613368833,1,NULL,0,'5fc861aa802549b18f708e8d39ab8673','2024-10-06 01:39:41','237c5d7e58fc48c7a36e363c38778943','2024-10-06 01:28:42',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1843302685982961666,1,NULL,0,'b2042bd58d0a41b39012260494a990d4','2024-10-08 01:27:23','fe725ed5884945eab4abb3411c91334a','2024-10-08 00:49:42',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1845381965181194241,1,NULL,0,'fedda2344fb047c5a223c62e491c1a69','2024-10-13 17:32:01','1557a258dd8445b0981c226cea63ee10','2024-10-13 18:32:01',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1871606465882636289,1,NULL,0,'cf65add825bc4fe4a2f1def5d2dd0c2c','2024-12-25 02:37:09','9b49003ecdd84cf996e2f88f3518f093','2024-12-25 03:18:49',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1875942052633554945,1,NULL,0,'465885dc58f045c587dfaae71cce3711','2025-01-06 02:03:41','455d98a8a3d04a339c348a4b0ab7281e','2025-01-06 02:26:53',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1875961042878763009,1,NULL,0,'ad1b29a2f3e849b68e78b7825b1e2713','2025-01-06 02:42:21','c3500b185b8f4942919f535ce46bf3b4','2025-01-06 03:42:21',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1879510071357112322,1,NULL,0,'d713f0340c0843a0b09fd3e8da7ef9de','2025-01-15 22:04:08','86f6bc7331b44ba3b12221f5862bfe65','2025-01-15 22:44:55',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886120472416694273,1,-7849442763473998852,0,'3ffacffb09d849ecbb2c4de549b07116','2025-02-03 03:32:18','06ceb3054d1745b988a5627378e7fdcc','2025-02-03 04:32:18',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886120631968018433,1,-7849442763473998852,0,'bb2f868ea05144269f89eaabf9e52722','2025-02-03 03:32:56','dbfc474faa564ff98f38485db9168510','2025-02-03 04:32:56',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886123107614011393,1,-7849442763473998852,0,'6d4622cc02e64b2796ce51e5d28cdef9','2025-02-03 03:42:46','a98bc1f2c9904670b58bc19a95df3901','2025-02-03 04:42:46',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886123529774903298,1,-7849442763473998852,0,'273d24e59b60445d9bd8c51e71c1146c','2025-02-03 03:44:27','00915e3193e8447f8eb6338a1d19a66a','2025-02-03 04:44:27',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886123740119248898,1,-7849442763473998852,0,'e0130b066bd747509835a147656205d4','2025-02-03 03:45:17','ebc81cc10de94a63ab1c94ac5d96e17f','2025-02-03 04:45:17',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886123869421252610,1,-7849442763473998852,0,'b3d766d548754eb584ef697304303368','2025-02-03 03:45:48','1bd141ccc26d4a3c87704c1cc382e058','2025-02-03 04:45:48',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886723983846825986,1,-6614758519877597602,0,'9bb8cc56470249b1923d9a28a3bea90f','2025-02-04 19:30:26','abdc2f67a1774f14b2672ad70465330c','2025-02-04 20:30:26',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886724093334937601,1,-6614758519877597602,0,'7138f7f898864c769e8b109cdbcf3d10','2025-02-04 19:30:52','a472d29a47bb4cfa8fc9000605c3befc','2025-02-04 20:30:52',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886724391197630465,1,-6614758519877597602,0,'561b94d5263c4c1c95ae93df22b4cc42','2025-02-04 19:32:03','39e827c0e25f47dcabf1ee53a818b80c','2025-02-04 20:32:03',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886724501637849090,1,-6614758519877597602,0,'bc5e9a2f8d034601936db668099b4d97','2025-02-04 19:32:29','82b738df6d8243fba44c1e4ce8dfba6d','2025-02-04 20:32:29',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886724911480070146,1,-6614758519877597602,0,'cbc77e6caea744c4bf39c92b9bb2ff14','2025-02-04 19:34:07','10ff99afb64a4980b9de7ba09931359a','2025-02-04 20:34:07',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886725011954622466,1,-6614758519877597602,0,'9ca1db48a8c14fdda364931f88b1f606','2025-02-04 19:34:31','21f8a0778e7b4f978b7c7b4350d40fff','2025-02-04 20:34:31',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886727697055768577,1,-5388299596738020669,0,'8936d372a4224ea9afe5df5cca2eb756','2025-02-04 19:45:11','19240464769d4594a1d3691071a30acd','2025-02-04 20:45:11',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886728651222179842,1,-5388299596738020669,0,'a7e952ba672e4113865ab2b17216652f','2025-02-04 19:48:59','5654a95b004941828ead16b577f64106','2025-02-04 20:48:59',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886729470919258113,1,-5388299596738020669,0,'357f778e5b50409aaebacd26084fd158','2025-02-04 19:52:14','dcd01707c2a04fa2943fa94b65b9273e','2025-02-04 20:52:14',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886731284766957570,1,-5388299596738020669,0,'cfa2f832470641a09cea6c8518e9171f','2025-02-04 19:59:27','8cabf0da94a942ee83f024a697a7b364','2025-02-04 20:59:27',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772023337041921,1,-7908518759052645604,0,'5f1dcbf0a74d4240a64c7a8e18eedb01','2025-02-04 22:41:20','ff95a95eb30b47e195e3873b778fcd5c','2025-02-04 23:41:20',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772027350990849,1,-7908518759052645604,0,'2862db5d26ab42178a2aae9be225a315','2025-02-04 22:41:21','0bdb410da51747ccadad6ce17da47084','2025-02-04 23:41:21',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772031335579649,1,-7908518759052645604,0,'e5ed08b1b296457fb926c1d3a0d0a282','2025-02-04 22:41:21','17c2ec0bd3e14d7c9e1d61da31563be5','2025-02-04 23:41:21',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772034506473473,1,-7908518759052645604,0,'b7832616a1334fd0b46d04956d82aed6','2025-02-04 22:41:22','b1f5e70a0d0f4c5a82134f9e0117fd1d','2025-02-04 23:41:22',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772037698338817,1,-7908518759052645604,0,'90d21a9b744e495e8f615dbb82091be0','2025-02-04 22:41:23','5e8bf64d2c2b43a0af5536587d16ac0c','2025-02-04 23:41:23',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772041234137090,1,-7908518759052645604,0,'c478aadd701c4966b85c938300a07307','2025-02-04 22:41:24','18c78f9b32b348da8197749125f90263','2025-02-04 23:41:24',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772044207898625,1,-7908518759052645604,0,'50b9572e49ed4b73adc2e5d8ccbca6c3','2025-02-04 22:41:25','b89cc5a585244d178522d336dac5c29c','2025-02-04 23:41:25',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772048028909569,1,-7908518759052645604,0,'708d64e3eb2a41609c152be195709c98','2025-02-04 22:41:25','d2d260ef6f99402d8c6429e628576da5','2025-02-04 23:41:25',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772051057197058,1,-7908518759052645604,0,'39fca451597846a3a1845d7f783834bb','2025-02-04 22:41:26','dd311cdbb7e644e093f0b46915f9af96','2025-02-04 23:41:26',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772054257451010,1,-7908518759052645604,0,'1bc46d7f8da541aea7d0ec58703fea24','2025-02-04 22:41:27','e0ff3524a7844fe8a56837851fb4f808','2025-02-04 23:41:27',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772057860358145,1,-7908518759052645604,0,'4364ccbcd8744b8393b9bad64def1657','2025-02-04 22:41:28','7fad622c130b4f3997f3314e03a6ce14','2025-02-04 23:41:28',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772061261938690,1,-7908518759052645604,0,'b441cf4a50ae4307871aa915cb5e060a','2025-02-04 22:41:29','81870517a69d4fd0b253d4793fe1976c','2025-02-04 23:41:29',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772066089582593,1,-7908518759052645604,0,'6c833e6a6dfd4c4eb7725bda4706e7c8','2025-02-04 22:41:30','dbe5dac4d02f4e6ebe242130bf4c758a','2025-02-04 23:41:30',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772070195806210,1,-7908518759052645604,0,'4dd6e59c55ca456e84f2555581aec046','2025-02-04 22:41:31','6876ad00f84e4c06b00804f52f5fe66f','2025-02-04 23:41:31',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772074742431746,1,-7908518759052645604,0,'355a3d4749c94324881ee89a0aa853cf','2025-02-04 22:41:32','16474afa6ce849e98e9cde2b625d117d','2025-02-04 23:41:32',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772078655717377,1,-7908518759052645604,0,'26465dbc04554a38b637d5d18814b7b2','2025-02-04 22:41:33','d77f10a20b544e348b558eb35d3cae07','2025-02-04 23:41:33',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772082757746690,1,-7908518759052645604,0,'c9d94a8926824af4a579c2665d8537d6','2025-02-04 22:41:34','af9b7eecaa634dd6a5734143b1c63db8','2025-02-04 23:41:34',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772087082074114,1,-7908518759052645604,0,'15aeb5398791436e80ec10c9cd66bbf1','2025-02-04 22:41:35','9e8ae4976bbc4310b7035c14959427fd','2025-02-04 23:41:35',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772091200880642,1,-7908518759052645604,0,'71f28868ff57491caf82c801fee870b3','2025-02-04 22:41:36','7b17510f0cfa442a89c2bfe8aeffeadb','2025-02-04 23:41:36',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772095336464386,1,-7908518759052645604,0,'4995bedd56114c689ca142d64664c943','2025-02-04 22:41:37','a3c2a5ece81b4e078bd1796810698e2e','2025-02-04 23:41:37',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772099564322818,1,-7908518759052645604,0,'4c7b9f39123841449d36159b7340aca1','2025-02-04 22:41:38','d4c15a5b0f314d0fa7e2085d14352a7e','2025-02-04 23:41:38',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772103733460994,1,-7908518759052645604,0,'bf0bb2b04dc249f4b18763964e546194','2025-02-04 22:41:39','024479b8d2654564ab02a093224d5a62','2025-02-04 23:41:39',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772108057788417,1,-7908518759052645604,0,'00ac552fd905436995d07cb84a109b52','2025-02-04 22:41:40','8ca87a12653c47f49cdfceccc84a87fb','2025-02-04 23:41:40',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772112105291778,1,-7908518759052645604,0,'35e28c6d5be54b9895f194f4943947a0','2025-02-04 22:41:41','62f5286e7f72458284c441ef74150009','2025-02-04 23:41:41',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772116320567297,1,-7908518759052645604,0,'6705e5378a7d4c389a5119d1ff38e41e','2025-02-04 22:41:42','4f2d95d636304405b9a3e9f942a5e7d7','2025-02-04 23:41:42',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772120493899778,1,-7908518759052645604,0,'d27550dc7862441a98984d8d379085a8','2025-02-04 22:41:43','d2121398168043b59261100702b1bf27','2025-02-04 23:41:43',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772124696592386,1,-7908518759052645604,0,'4ae7acf926c14e57b63ef474ff951c4c','2025-02-04 22:41:44','00cf4d91f3e44405a985514e28fb547d','2025-02-04 23:41:44',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772128408551425,1,-7908518759052645604,0,'beae68e7114c4811b983e56972a72c69','2025-02-04 22:41:45','20d8944dd99e4bb89d5f7748a2224c89','2025-02-04 23:41:45',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772136205762561,1,-7908518759052645604,0,'66b7272483894bd8ba0c47425ba5a9fc','2025-02-04 22:41:46','c3f6d3b8fadc49359f3916b9f6165b59','2025-02-04 23:41:46',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772140022579202,1,-7908518759052645604,0,'22b71b5fa8044aadbe94c643bcc3bbd4','2025-02-04 22:41:47','48ad27eac84a43b38d7cc017f7c1ac83','2025-02-04 23:41:47',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772144481124353,1,-7908518759052645604,0,'c0a5d9756cab4211877709e0c17f3f70','2025-02-04 22:41:48','5c4093da93a144d28a19d985e5f46a8c','2025-02-04 23:41:48',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772148373438465,1,-7908518759052645604,0,'9a69b6f500d0462c811d851f40ffa655','2025-02-04 22:41:49','f9098255d9bb459398f2214028064866','2025-02-04 23:41:49',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772152408358914,1,-7908518759052645604,0,'1350c0a23be04621b787cecc4631d475','2025-02-04 22:41:50','f4ff0f729fa14bb78fc5771c590262a0','2025-02-04 23:41:50',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772156342616066,1,-7908518759052645604,0,'f85f45cbd4ea42c0b3b814d12c173456','2025-02-04 22:41:51','78b2d30c4bcf4b00a22193886d0f376b','2025-02-04 23:41:51',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772160134266882,1,-7908518759052645604,0,'9f9ae4b578cf4cb682c602ee706a700b','2025-02-04 22:41:52','76b80b7e171042349de32175706484d7','2025-02-04 23:41:52',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772164047552514,1,-7908518759052645604,0,'a08dbcb1284a469ea00cb4440b766a10','2025-02-04 22:41:53','6f4aaa6f7ba84861936a79d3cda40a6e','2025-02-04 23:41:53',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772365118291969,1,-7908518759052645604,0,'bf6e1e9c81454056a3fe53fbd623e984','2025-02-04 22:42:41','782f34cb0cdc48e98e4ddb41d6e1da15','2025-02-04 23:42:41',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772369279041537,1,-7908518759052645604,0,'fb2e36b607a940cab54cd9067037891b','2025-02-04 22:42:42','7efa1ba8013e499cb03244b4b2804fa8','2025-02-04 23:42:42',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772373087469570,1,-7908518759052645604,0,'6955ff40d5f04574a03392cc7b9080ad','2025-02-04 22:42:43','b004d12fca9f499bb7926f68aff9d61e','2025-02-04 23:42:43',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772376770068481,1,-7908518759052645604,0,'c734cd4c8b2f4baf8863a40a2ebed59d','2025-02-04 22:42:44','78dd95d52c82401686aa976315e5d37e','2025-02-04 23:42:44',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886772380121317378,1,-7908518759052645604,0,'cacb97486f0a4a8cb6ae27cdf9bc7c45','2025-02-04 22:42:45','cbb275e803824c91a2fef4d5af2148e8','2025-02-04 23:42:45',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886773031408648194,1,-7908518759052645604,0,'ae66cc88caf84aaeab7c6423c4618be6','2025-02-04 22:45:20','8a91a871361e455b941cb7b42c12166a','2025-02-04 23:45:20',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886773081379586050,1,-5358229783388956748,0,'c64f38eade494d81bafc1494ebd03017','2025-02-04 22:45:32','43ab4976c95d425fbc4e4a3d4c23efd8','2025-02-04 23:45:32',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886773232819126274,1,-5358229783388956748,0,'74b081a0093e4d06a70335770da628a3','2025-02-04 22:46:08','3445c6aba78d4244922f2f0cdbfa1da4','2025-02-04 23:46:08',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886773430500868097,1,-5358229783388956748,0,'0f875b5c89f64455a54197080dae3e2f','2025-02-04 22:46:55','7cf08be366e2471385c34fcf26a298eb','2025-02-04 23:46:55',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886773451619188737,1,-8203714272981334934,0,'d6df8bc431ab41f186376db166b8be3e','2025-02-04 22:47:00','bd393b896ed04941a3cf9aeb786f6a9a','2025-02-04 23:47:00',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886785539079028738,1,-6945921729010015936,0,'e5444d8356f446bd91d769430d025640','2025-02-04 23:35:02','5c72254075ff4312850505661b89aa66','2025-02-05 00:35:02',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886785545550839809,1,-6945921729010015936,0,'7ade83df422b45ae920763588fa7cf75','2025-02-04 23:35:03','6340b1ab8bed4b5ea0cf80ad170930e2','2025-02-05 00:35:03',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886785549942276097,1,-6945921729010015936,0,'b7b1fc5277144bfcbcb55427b43d2221','2025-02-04 23:35:05','4cbc209affa94df19be14d1575656cc0','2025-02-05 00:35:05',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886785553511628802,1,-6945921729010015936,0,'1fb9be9713844de4b6f47f8c198eaaca','2025-02-04 23:35:05','237bf4f369de4063ae6344342e74d085','2025-02-05 00:35:05',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886785557269725185,1,-6945921729010015936,0,'dba33224d0a44d64aca20ed9513fe38a','2025-02-04 23:35:06','85cd0c99622343c29b61af14a855aea9','2025-02-05 00:35:06',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886785560545476610,1,-6945921729010015936,0,'7342bb7bb6014149b673095614238731','2025-02-04 23:35:07','e26b0320976a404b950e60c136482019','2025-02-05 00:35:07',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886785564739780610,1,-6945921729010015936,0,'e9673ac69fc74ce7ada6e1a9b479d3ed','2025-02-04 23:35:08','08888fbd467d4934a29ec9de9e6624a0','2025-02-05 00:35:08',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886785568439156738,1,-6945921729010015936,0,'53ca19d0050c464983dee85138c49837','2025-02-04 23:35:09','0819794110c74b4dacde43c1939c2c90','2025-02-05 00:35:09',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886785789030187010,1,-6945921729010015936,0,'5bb2ccdd4b5e40cf8e69c2517f12e1d3','2025-02-04 23:36:02','09b7e2a18a0942fba931fe424014a792','2025-02-05 00:36:02',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886785899113889794,1,-6945921729010015936,0,'ba3f8f2757f14becad1a7cdf7cc66a2e','2025-02-04 23:36:28','ba0a5e35675c4aa1a936bdb79ad19a65','2025-02-05 00:36:28',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886785995465441281,1,-6945921729010015936,0,'8e17009ffd0f4cd9aa26ff29c765f6ad','2025-02-04 23:36:51','32f23718f06c43b3b2f8502f8fe47bfa','2025-02-05 00:36:51',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886786041875415041,1,-6945921729010015936,0,'79058b83f8ba4ec38bfd48112e3bc320','2025-02-04 23:37:02','275110d0efb84e30a565139c54557e17','2025-02-05 00:37:02',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886786045931307009,1,-6945921729010015936,0,'86267c104627454abd1eb1e719058042','2025-02-04 23:37:03','537c38f7621347e58177828b6b761a56','2025-02-05 00:37:03',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886786049991393282,1,-6945921729010015936,0,'797ecbb34755481a81e97edd0a2e6d93','2025-02-04 23:37:04','5201406b9d5b40d79006cb81a2f97c64','2025-02-05 00:37:04',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886786054248611842,1,-6945921729010015936,0,'a39d7a2b19e64f9fab4a8caab3a5ac25','2025-02-04 23:37:05','95dfbc6825c04a82978ee2c1c73e9ace','2025-02-05 00:37:05',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886786058564550657,1,-6945921729010015936,0,'2d6508e9f0e7437e879e910830206532','2025-02-04 23:37:06','1cdce349964b4c0d896986c694bacd15','2025-02-05 00:37:06',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886786062633025538,1,-6945921729010015936,0,'2d4f3ed34a7349ddb0aa891898b92f8a','2025-02-04 23:37:07','1ea0016af6e74a39a9b8c0c501fcdfdc','2025-02-05 00:37:07',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886786067498418178,1,-6945921729010015936,0,'01ece5d4437c4a36a699e3198d2aa234','2025-02-04 23:37:08','68a3cb1edc2a473896adcc6902eb94e7','2025-02-05 00:37:08',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886786074121224193,1,-6945921729010015936,0,'b1b11b675ebf4ab29ff51e61da6e56be','2025-02-04 23:37:10','b728f8226c4e449da7d8950d9b44809e','2025-02-05 00:37:10',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886786081717108737,1,-6945921729010015936,0,'892f7dab24d1419c86e4ef48eed85a6c','2025-02-04 23:37:11','c90b5718bd3848549f3e957b548d2a00','2025-02-05 00:37:11',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886793198687633410,1,-6945921729010015936,0,'ed64fd70575c4e5db782a2644d5e41d5','2025-02-05 00:05:28','fad9de5e0f4a42a39349621ef1c6aaeb','2025-02-05 01:05:28',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886794871996825602,1,-7478958916457125311,0,'9352743084ca47ec84b004a89b09593b','2025-02-05 00:12:07','fc6b96ed03eb4c35908d504fe47e4481','2025-02-05 01:12:07',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886795084274745345,1,-7478958916457125311,0,'0453a6f47d414a5d845a6e613ef44cf9','2025-02-05 00:12:58','7d072c0da1ea404fa90366c4453d732d','2025-02-05 01:12:58',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886795215967502338,1,-7478958916457125311,0,'cd0a19e0dd7548619c81d0e3b9ed8074','2025-02-05 00:13:29','a4ff9d0849a341a29f07a6f8c071bf03','2025-02-05 01:13:29',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886795633657266177,1,-7478958916457125311,0,'f4c496a983a94634b57662e2cdcaebc4','2025-02-05 00:15:09','9cdb8cb3e10e480991bc1363795c3f40','2025-02-05 01:15:09',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886798494629773313,1,-7478958916457125311,0,'7e849c404e8a4d2f812b2627c969cd1a','2025-02-05 00:26:31','c5fe72fadc6840b2b9abefba14a2879c','2025-02-05 01:26:31',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886799521206648834,1,-7478958916457125311,0,'45a4238662d34949b2b9ef4a368e7fd2','2025-02-05 00:30:36','d0e9ea7962fe4526a243458b3d50ea85','2025-02-05 01:30:36',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886801045861318658,1,-8408576078717648910,0,'9a2dfc6612a54134b0a809619fe126e1','2025-02-05 00:36:39','bd33df12d78c4728893c453bb855465d','2025-02-05 01:36:39',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886801767512293377,1,-8408576078717648910,0,'727cabb368354aa2845b6b749fc1e81b','2025-02-05 00:39:31','ff30a5ed440749369d4cce93eeabde5b','2025-02-05 01:39:31',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886803744388431874,1,-5160997806155724258,0,'eeeca00471b849c59d2b69fe563a58af','2025-02-05 00:47:22','8a98dba6c7574e04966e453f22b62333','2025-02-05 01:47:22',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886803808917798913,1,-5160997806155724258,0,'52396054b458451b8cffeed1133a59b6','2025-02-05 00:47:38','bdb6f9a08edd47d7971235aaf5c1774d','2025-02-05 01:47:38',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886803813711888385,1,-5160997806155724258,0,'0472be55ce30401c82d8a5d7a1d90706','2025-02-05 00:47:39','07ff17de372b4ebd9c611bfa3116d807','2025-02-05 01:47:39',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886803818250125313,1,-5160997806155724258,0,'69efe05e33f04af09173a2396e84c10a','2025-02-05 00:47:40','fa22975ecf92409a996b36a80052ee77','2025-02-05 01:47:40',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886803821421019137,1,-5160997806155724258,0,'6ee0a6764cd840efaea79f5590d99bd4','2025-02-05 00:47:41','21ccde7353714c0abd5a88f71491bba8','2025-02-05 01:47:41',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886803824969400322,1,-5160997806155724258,0,'fc671dd257fc4eebb4bab29b5a547e82','2025-02-05 00:47:42','ac33116a06fc44ff96689e8c9363841d','2025-02-05 01:47:42',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1886812525436485634,1,-8610698188288433054,0,'e66b824f924f4235b3205cd3a3e3f9dd','2025-02-05 01:22:16','fd29974207594ccfbfd892e8c4d90672','2025-02-05 02:22:16',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162576159518721,1,-8757217089921965512,0,'77fa3014677b4eac85978fd3a0e92102','2025-02-06 00:33:15','d45dcc042d4d4091979764d2aaf3b675','2025-02-06 01:33:15',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162578369916930,1,-8757217089921965512,0,'30d6efb17b7744258c911d8d62ff14ba','2025-02-06 00:33:15','526c90a4f94a40689f8f6e43e75fe826','2025-02-06 01:33:15',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162580177661954,1,-8757217089921965512,0,'13310218d93144eb9308a0bd64581d44','2025-02-06 00:33:16','2befdc878f5d40c4828dba321bcafc7b','2025-02-06 01:33:16',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162581926686722,1,-8757217089921965512,0,'b80c93300434403788ee27118e8a0527','2025-02-06 00:33:16','977124890de847fcb4a77dc6cf63ae7f','2025-02-06 01:33:16',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162584632012801,1,-8757217089921965512,0,'0cfdc5e81c35452691e7fabb9b2e5453','2025-02-06 00:33:17','c3fb119700654fd6adae1784d6726ed1','2025-02-06 01:33:17',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162586947268610,1,-8757217089921965512,0,'1cda467694114e2aaa064e5f4899812b','2025-02-06 00:33:17','db52e916a1624c85b1bb7bb036626466','2025-02-06 01:33:17',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162589149278210,1,-8757217089921965512,0,'5b4335e2fbf1429891e6bf72a79a86a7','2025-02-06 00:33:18','6a6e5322a22748088921be0732ea44ff','2025-02-06 01:33:18',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162590625673217,1,-8757217089921965512,0,'1975445ba38647b2ba39b87fcc3a160b','2025-02-06 00:33:18','fc8e8767360b433e974f202d3914ad50','2025-02-06 01:33:18',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162592240480258,1,-8757217089921965512,0,'a73e50d6c45b49e2b5bcd81d7df9ef0c','2025-02-06 00:33:18','d64f103a8f0b4a00aa451fb9fc8b2f83','2025-02-06 01:33:18',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162594123722754,1,-8757217089921965512,0,'2b66f6eeaa54406f95039210fcc072f8','2025-02-06 00:33:19','3d3a8c1214214e498a563869be8c2c08','2025-02-06 01:33:19',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162595730141185,1,-8757217089921965512,0,'927a5a6d7efc45e8aa85c8e745b6729b','2025-02-06 00:33:19','187bbe6ff1734801bfd8d9b4330b7d0b','2025-02-06 01:33:19',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162597357531138,1,-8757217089921965512,0,'f7c228e31aab40eca13ddcfdfa27cbf8','2025-02-06 00:33:20','1b8a68e593b845b4baa2d9861b9565a3','2025-02-06 01:33:20',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162599073001474,1,-8757217089921965512,0,'8872cf7a81214a6db86b8faef4024c49','2025-02-06 00:33:20','437b4d4583a34a1398e5999e9e61e0fb','2025-02-06 01:33:20',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162600750723073,1,-8757217089921965512,0,'a021c778f58645529bf05678428bc12a','2025-02-06 00:33:20','eafb489da5d5470689fe789791736922','2025-02-06 01:33:20',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162603208585217,1,-8757217089921965512,0,'91fcb804d76f4d4dae38e4f8bb345e28','2025-02-06 00:33:21','e6d66790f8b441ae8d764eb1f731e2e3','2025-02-06 01:33:21',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162604869529602,1,-8757217089921965512,0,'9f7b3ebfa3904bb697f7c09a856aa897','2025-02-06 00:33:21','2ebaae22e54941a48e1039830134b1d8','2025-02-06 01:33:21',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162606379479042,1,-8757217089921965512,0,'7d3408f24a624b0893da99c59941de04','2025-02-06 00:33:22','a5644260c7a3492f816119022b7fb731','2025-02-06 01:33:22',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162608782815233,1,-8757217089921965512,0,'f8bc6407cab54eaeb498e9e2240cb3c4','2025-02-06 00:33:22','e01bcb0678b94f558ee4f834e2b65f3c','2025-02-06 01:33:22',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162610741555201,1,-8757217089921965512,0,'2f37fe55f1e04c10b7cf75885e65c25b','2025-02-06 00:33:23','20129a6778ba4d878dd3261f1e5aed41','2025-02-06 01:33:23',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162612436054017,1,-8757217089921965512,0,'2d0fd7b70a19451799a1c11023fb550d','2025-02-06 00:33:23','0e4bc4b2182f4d02bc55d1a4ae09b7b6','2025-02-06 01:33:23',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162615871188993,1,-8757217089921965512,0,'06f4f3390e634ba0a211014097f9a4dd','2025-02-06 00:33:24','129dafe068b14f0b8296cd23f0c83314','2025-02-06 01:33:24',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162617695711233,1,-8757217089921965512,0,'b7ee1657b521478e84fa819b99ef4c14','2025-02-06 00:33:25','5c07bfefbb4b46299d39251cb818f98c','2025-02-06 01:33:25',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162619784474626,1,-8757217089921965512,0,'27d6d3f0749d49dc950fd2735eee0918','2025-02-06 00:33:25','990cea22cfb14934a8b18a0e7e78a568','2025-02-06 01:33:25',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162621353144322,1,-8757217089921965512,0,'d723489a0487465eb81371a6f8cb96f2','2025-02-06 00:33:25','708f887557ba47369b498063773269c8','2025-02-06 01:33:25',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162622804373505,1,-8757217089921965512,0,'064d00d4bce1430ca5ca48110709b8fa','2025-02-06 00:33:26','dfa0b6ce512c46338ad2eac1ed3e12df','2025-02-06 01:33:26',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162624427569153,1,-8757217089921965512,0,'b46e61f345f049afb876cd34c5634813','2025-02-06 00:33:26','d9a18740912c44d087981f53415153c6','2025-02-06 01:33:26',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887162626080124929,1,-8757217089921965512,0,'0a9b01d28c70428a826c9ee6793f63a1','2025-02-06 00:33:27','773c40a5fe8049619aae29cd23fdf8df','2025-02-06 01:33:27',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887166185844649985,1,-8757217089921965512,0,'c50478e2172143c7a217975ff85ef7d8','2025-02-06 00:47:35','b374d3f21a4c4b52b2fd525566790ca1','2025-02-06 01:47:35',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887166189057486849,1,-8757217089921965512,0,'809046968c8746a7bfd08f037f36c6fd','2025-02-06 00:47:36','2ce539848daf494b9d690ba12b864132','2025-02-06 01:47:36',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887166192886886402,1,-8757217089921965512,0,'1b4f686734a44c798d1d11c3ff97df0c','2025-02-06 00:47:37','010b529bc8b148b1a579e69cb2f31b9e','2025-02-06 01:47:37',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887166593254174722,1,-8757217089921965512,0,'2a726388adf54dd5bcb4779c29d741b1','2025-02-06 00:49:12','e1b9fa169a374288a384720d66c586de','2025-02-06 01:49:12',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887167605562028033,1,-8569246141641592753,0,'63a011afbfd14dbabb305ec423fab1cb','2025-02-06 00:54:13','9f82e02a8c294626b43ba5305f6dac78','2025-02-06 01:53:14',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1887184967346565121,1,-7138849408127419729,0,'3e5bfa498ae648f8bd4e29cc3e47b248','2025-02-06 02:02:26','36705f1d98c24a269139539baf290776','2025-02-06 03:02:13',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1890702276331446273,1,1,1890702208880156673,'b0c05244333245e39fba769e9b42350c','2025-02-15 18:58:45','b780af29a0c3427396e1dbdbe5498bfc','2025-02-15 19:58:45',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1890702791014490113,1,1,1890702208880156673,'b3863a6e820b40d196fac8d7f9b734cf','2025-02-15 19:00:48','4a17af03a98141278fe36a96e272283e','2025-02-15 20:00:48',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1893601385874894849,1,0,1893599891802402818,'64fbbed8d27944728a4ec0ca11f6c8a9','2025-02-23 18:58:46','bedeb92aee504896991ea229f47ac9f1','2025-02-23 19:58:46',0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '');
INSERT INTO `sys_ra_token` VALUES (1896236059008839682,1,0,1896092184580009986,'073a727fb8de470397e821beafa30f8b','2025-03-03 01:28:01','29ddf5d6dc5444fa8a9e95e64a4e2ab2','2025-03-03 02:28:01',0,'2025-03-03 00:28:01','2025-03-03 00:28:01',NULL,_binary '');
INSERT INTO `sys_ra_token` VALUES (1896254007484231681,1,0,1896092184580009986,'677814a6622f410b983a50c6951df8ef','2025-03-03 02:39:21','7a9e0a2e3a9b4ee894f3ae826d8b4f4f','2025-03-03 03:39:21',0,'2025-03-03 01:39:21','2025-03-03 01:39:21',NULL,_binary '');
INSERT INTO `sys_ra_token` VALUES (1896254600323936258,1,0,1896092184580009986,'cc0c93206b114bf3addab9b9e2f1e027','2025-03-03 02:41:42','a004c0d326434e9a824589d16f67e491','2025-03-03 03:41:42',0,'2025-03-03 01:41:42','2025-03-03 01:41:42',NULL,_binary '');
INSERT INTO `sys_ra_token` VALUES (1896254944005206017,1,0,1896092184580009986,'59dd8fa9a72442bdb568a1bb9c393cef','2025-03-03 02:43:04','91fff99e74ff4d37b373cc40830f4206','2025-03-03 03:43:04',0,'2025-03-03 01:43:04','2025-03-03 01:43:04',NULL,_binary '');
INSERT INTO `sys_ra_token` VALUES (1896255186423394305,1,0,1896092184580009986,'80e0f8d75f7047b88b5aaf1b75263e26','2025-03-03 02:44:02','98299844514a4cc2b8bb99f86ee53284','2025-03-03 03:44:02',0,'2025-03-03 01:44:02','2025-03-03 01:44:02',NULL,_binary '');
INSERT INTO `sys_ra_token` VALUES (1897688305265487874,1,0,1897669601598648321,'85687e68e4fa4c45a8cd61bb756425c2','2025-03-07 01:38:44','bac8c5ab427340fb90d6b174dea87550','2025-03-07 02:38:44',0,'2025-03-07 00:38:44','2025-03-07 00:38:44',NULL,_binary '');
INSERT INTO `sys_ra_token` VALUES (1906406823364378625,1,0,1906363439904301058,'35adaaf1a72f49eea36d13c79123ce9c','2025-03-31 03:03:01','cb7c7e3d729c4a59902b2d96013813b0','2025-03-31 04:03:01',0,'2025-03-31 02:03:01','2025-03-31 02:03:01',NULL,_binary '');
INSERT INTO `sys_ra_token` VALUES (1908565691670425601,1,0,1908561199202119682,'f989db8f088b4afb9ce0c4dc6d059efb','2025-04-06 02:01:35','c7d568af26254a679f3fb9bb7913c43f','2025-04-06 03:01:35',0,'2025-04-06 01:01:35','2025-04-06 01:01:35',NULL,_binary '');
INSERT INTO `sys_ra_token` VALUES (1908576131267948546,1,0,1908561199202119682,'106ce6f2c400467895e0792e4a8d8188','2025-04-06 02:43:04','ba8fd6c2d54e48bcaa0b0d0fe9ba4986','2025-04-06 03:43:04',0,'2025-04-06 01:43:04','2025-04-06 01:43:04',NULL,_binary '');
INSERT INTO `sys_ra_token` VALUES (1908581701500792834,1,0,1908561199202119682,'71c0b9dddd5f43148dcd38fd5a35f3c7','2025-04-06 03:05:12','3e17dccafe724bf39d2a0790bb1a2eb0','2025-04-06 04:05:12',0,'2025-04-06 02:05:12','2025-04-06 02:05:12',NULL,_binary '');
INSERT INTO `sys_ra_token` VALUES (1908622560547975170,1,0,1908561199202119682,'158299e63d4b417fb465b9a5012e95d1','2025-04-06 05:47:34','13574ce634b144038680f2063079c1e8','2025-04-06 06:47:34',0,'2025-04-06 04:47:34','2025-04-06 04:47:34',NULL,_binary '');
INSERT INTO `sys_ra_token` VALUES (1908883300941189122,1,0,1908871274869309441,'86e5a52a75934def83fafbca021b4dd8','2025-04-06 23:03:39','7574958bc7404203b2c21a18cf3cbacf','2025-04-07 00:03:39',0,'2025-04-06 22:03:39','2025-04-06 22:03:39',NULL,_binary '');
INSERT INTO `sys_ra_token` VALUES (1908883638054178817,1,0,1908871274869309441,'046a4446eb0743ba8ca74de02ca87826','2025-04-06 23:04:59','115913850995423ba7018fa1a53d9869','2025-04-07 00:04:59',0,'2025-04-06 22:04:59','2025-04-06 22:04:59',NULL,_binary '');
INSERT INTO `sys_ra_token` VALUES (1908888798130229249,1,0,1908871274869309441,'dc4173a2f50b4cca87d509c3d86b858a','2025-04-06 23:25:30','ea11502a20f344a89031229813431c91','2025-04-07 00:25:30',0,'2025-04-06 22:25:30','2025-04-06 22:25:30',NULL,_binary '');
INSERT INTO `sys_ra_token` VALUES (1908960528400113665,1,0,1908960514663895042,'43b84d933f624936a88b6d996f91187b','2025-04-07 04:10:31','1f2202bb8617404297e9d50b8a23074e','2025-04-07 05:10:31',0,'2025-04-07 03:10:31','2025-04-07 03:10:31',NULL,_binary '\0');
INSERT INTO `sys_ra_token` VALUES (1911425530067058689,1,0,1911345326670262273,'bceffd3bf6cc47d88f95ee1371fe8ff2','2025-04-13 23:25:34','5a0418cc053f4c02b9910d9e14832922','2025-04-14 00:25:34',0,'2025-04-13 22:25:34','2025-04-13 22:25:34',NULL,_binary '');
INSERT INTO `sys_ra_token` VALUES (1911426088094097410,1,0,1911345326670262273,'e46b6b006f82486a902c44ecb0ce25ca','2025-04-13 23:27:47','5654ae7b2c2b43abbf6dc998a3719323','2025-04-14 00:27:47',0,'2025-04-13 22:27:47','2025-04-13 22:27:47',NULL,_binary '');
INSERT INTO `sys_ra_token` VALUES (1911427346842419201,1,0,1911345326670262273,'e09d5850c7a0428c814c8eca229a690c','2025-04-13 23:32:47','1a37d8cdb240429ab9030f7e16359ce4','2025-04-14 00:32:47',0,'2025-04-13 22:32:47','2025-04-13 22:32:47',NULL,_binary '');
INSERT INTO `sys_ra_token` VALUES (1911428175892742146,1,0,1911345326670262273,'f5bc412117f34af08f797ce2bc942873','2025-04-13 23:36:04','5c29ca3bb17e4675b772039c82a9af31','2025-04-14 00:36:04',0,'2025-04-13 22:36:04','2025-04-13 22:36:04',NULL,_binary '');
INSERT INTO `sys_ra_token` VALUES (1911828357462323201,1,0,1911825466718236673,'6e7d92b101674c2abc68fc3404c002f9','2025-04-15 02:06:15','b34b699ce1794787bce435b3a2223a5d','2025-04-15 03:06:15',0,'2025-04-15 01:06:15','2025-04-15 01:06:15',NULL,_binary '');
INSERT INTO `sys_ra_token` VALUES (1911838691711483905,1,0,1911825466718236673,'9aa5019593be40968d222f35a5670e1f','2025-04-15 02:47:19','f53f64b26b4341f8a4e6a0b3efd034b2','2025-04-15 03:47:19',0,'2025-04-15 01:47:19','2025-04-15 01:47:19',NULL,_binary '');
INSERT INTO `sys_ra_token` VALUES (1911841832695726081,1,0,1911825466718236673,'a0f0e657bbf84209b5602938db3bafc1','2025-04-15 02:59:48','3a353b3c21d0434898a3cae28e6862dc','2025-04-15 03:59:48',0,'2025-04-15 01:59:48','2025-04-15 01:59:48',NULL,_binary '\0');

--
-- Table structure for table `sys_resource`
--

DROP TABLE IF EXISTS `sys_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_resource` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '资源名称',
  `patterns` varchar(255) DEFAULT NULL COMMENT '资源路径',
  `methods` varchar(255) DEFAULT NULL COMMENT '请求方式',
  `description` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '描述',
  `category_id` varchar(255) DEFAULT NULL COMMENT '资源分类ID',
  `mapping_type` int NOT NULL DEFAULT '0' COMMENT '映射类型[盒模型,块模型]',
  `access_type` int NOT NULL DEFAULT '0' COMMENT '访问类型[授权,认证,匿名]',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='后台资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_resource`
--

INSERT INTO `sys_resource` VALUES (1,'admin','/**','0,1,2,3,5',NULL,'all',0,0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_resource` VALUES (2,'block','/abc/**','0,1',NULL,NULL,1,0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `status` char(1) DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `sort` int NOT NULL DEFAULT '0',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

INSERT INTO `sys_role` VALUES (1,'admin','',0,'','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_role` VALUES (5,'user','0',1,'','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL DEFAULT '0' COMMENT '权限id',
  `mapping_type` int NOT NULL DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=232 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

INSERT INTO `sys_role_menu` VALUES (226,5,1758434223360434178,1,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_role_menu` VALUES (227,5,0,1,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_role_menu` VALUES (228,5,1,1,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_role_menu` VALUES (229,5,1758179133546422274,0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_role_menu` VALUES (230,1,1758434223360434178,1,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_role_menu` VALUES (231,1,0,0,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');

--
-- Table structure for table `sys_role_resource`
--

DROP TABLE IF EXISTS `sys_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_resource` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint DEFAULT NULL COMMENT '资源ID',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1833546930283196419 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='后台角色资源关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_resource`
--

INSERT INTO `sys_role_resource` VALUES (1833546930283196418,1,1,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL COMMENT '主键',
  `user_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `password` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `nick_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '昵称',
  `dept_id` bigint DEFAULT NULL COMMENT '部门id',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phone_number` varchar(32) DEFAULT NULL COMMENT '手机号',
  `sex` char(1) NOT NULL DEFAULT '2' COMMENT '用户性别（0男，1女，2未知）',
  `avatar` varchar(128) DEFAULT NULL COMMENT '头像',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` char(1) DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `login_ip` varchar(15) DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

INSERT INTO `sys_user` VALUES (1,'admin','$2a$10$KmoDd5ffYpmekPu9nPWKJu/KrvSO2arORTiCDn11XPWroArakm.J6','ChainXi',1,'chainxi@foxmail.com','13517990532','1','9f82aeda43c64036a0a0999991b1fee8','dasdasddzxs','0',NULL,NULL,'2025-03-02 00:00:00','2025-04-15 01:47:33','sys:1',_binary '\0');
INSERT INTO `sys_user` VALUES (1832655526807842818,'subAccount','$2a$10$kPiIN6BSwi6dqq.x7GnInucaabekCIYELmfkoC3EDPCSGtzDmDHE2','',513,'140455271@qq.com','','2','fd29f38a288e4336a0df55ac7421a376','','0',NULL,NULL,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_user` VALUES (1872317988548620289,'admin123','$2a$10$gXnXS7SpNfyOjAOQU2EWdeNE302pi9x5WaWMI8M71QT4h9ggh.tH2','',257,'123456789@qq.com','','2','d13c792c912440e8af8a1a8116ba49af','','0',NULL,NULL,'2025-03-02 00:00:00','2025-04-06 04:57:15','sys:1',_binary '\0');

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '用户id',
  `role_id` bigint NOT NULL DEFAULT '0' COMMENT '角色id',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_user_role_pk` (`role_id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

INSERT INTO `sys_user_role` VALUES (1,1,1,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_user_role` VALUES (2,1832655526807842818,5,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-01 19:53:35
