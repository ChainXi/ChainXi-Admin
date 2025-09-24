-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: chainxi_admin
-- ------------------------------------------------------
-- Server version	9.3.0

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

INSERT INTO `gen_table` VALUES (3,'sys_cache_info','缓存信息',NULL,NULL,'SysCacheInfoo','crud','element-plus','com.ruoyi.system','system','cacheInfo','缓存信息','ruoyi','1','H:\\Project\\chainxi_admin\\module-generator\\src','{\"treeCode\":null,\"treeName\":null,\"treeParentCode\":null,\"parentMenuId\":\"1891530775170527233\"}',NULL,'2025-04-01 01:58:48','2025-04-06 04:38:11','',_binary '\0');
INSERT INTO `gen_table` VALUES (4,'sys_menu','菜单表','','','SysMenuu','tree','element-plus','com.ruoyi.system','system','menu','菜单','ruoyi','1','H:\\Project\\chainxi_admin\\module-generator\\src','{\"treeCode\":\"id\",\"treeName\":\"name\",\"treeParentCode\":\"pid\",\"parentMenuId\":\"1891530775170527233\"}',NULL,'2025-04-04 23:52:31','2025-04-06 04:37:50','',_binary '\0');
INSERT INTO `gen_table` VALUES (5,'sys_user','用户表',NULL,NULL,'SysUserr','crud','element-plus','com.ruoyi.system','system','userr','用户','ruoyi','1','H:\\Project\\chainxi_admin\\module-generator\\src','{\"treeCode\":null,\"treeName\":null,\"treeParentCode\":null,\"parentMenuId\":\"1891530775170527233\"}',NULL,'2025-04-06 04:40:57','2025-04-06 04:45:18','',_binary '\0');

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
  `expire_times` varchar(255) DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `sys_cache_pk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COMMENT='缓存信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_cache_info`
--

INSERT INTO `sys_cache_info` VALUES (1,'auth_user','[{\"storageCache\":1,\"expireTime\":300000}]','登录过期时间','2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');
INSERT INTO `sys_cache_info` VALUES (2,'captcha_codes','[{\"storageCache\":1,\"expireTime\":60000}]','验证码',NULL,NULL,'',_binary '\0');

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
  `updater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
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
  `updater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
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
  `secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端密钥',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '应用名',
  `origin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `logout_call` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '应用描述',
  `access_token_validity_seconds` int NOT NULL COMMENT '访问令牌的有效期',
  `refresh_token_validity_seconds` int NOT NULL COMMENT '刷新令牌的有效期',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='OAuth2 客户端表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_oauth2_client`
--

INSERT INTO `sys_oauth2_client` VALUES (43,0,'123456','server','https://chainxiadmin.cloud','/','认证中心',1,1,'2025-03-02 00:00:00','2025-04-07 00:04:06',NULL,_binary '\0');
INSERT INTO `sys_oauth2_client` VALUES (44,1,'1','job','https://chainxiadmin.cloud:8070','https://chainxiadmin.cloud:8070/xxl-job-admin/oauth/client/logout-call','定时任务',1,1,'2025-03-02 00:00:00','2025-03-02 00:00:00','',_binary '\0');

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
  `access_token` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '授权令牌',
  `at_expires_time` datetime DEFAULT NULL COMMENT '授权令牌过期时间',
  `refresh_token` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '刷新令牌',
  `expires_time` datetime NOT NULL COMMENT '过期时间',
  `tenant_id` bigint NOT NULL DEFAULT '0' COMMENT '租户编号',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1970886633532297218 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='OAuth2 刷新令牌';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_ra_token`
--

INSERT INTO `sys_ra_token` VALUES (1970886633532297217,1,0,1970882019873837058,'ffe64e71f9ec4e9f981cf563d5c4b7c9','2025-09-25 00:52:45','acb5a29aca1e4ca4bd3aedefc26d1a25','2025-10-02 00:22:45',0,'2025-09-25 00:22:45','2025-09-25 00:22:45',NULL,_binary '\0');

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

INSERT INTO `sys_user` VALUES (1,'admin','$2a$10$8gNw070/l1eFU5NnMA0AsO4zURUUGD0fv3o7GCYLtC9flpLE4L2VC','ChainXi',1,'chainxi@foxmail.com','13517990532','1','9f82aeda43c64036a0a0999991b1fee8','dasdasddzxs','0',NULL,NULL,'2025-03-02 00:00:00','2025-09-04 00:00:42','sys:1',_binary '\0');
INSERT INTO `sys_user` VALUES (1832655526807842818,'subAccount','$2a$10$AWcFwaPBPVT6ll5psX7sNeIfV9wxND6XwphUCtQL13bLOUJpmG2lu','',513,'140455271@qq.com','','2','fd29f38a288e4336a0df55ac7421a376','','0',NULL,NULL,'2025-03-02 00:00:00','2025-09-04 00:00:27','sys:1',_binary '\0');
INSERT INTO `sys_user` VALUES (1872317988548620289,'admin123','$2a$10$bL4cm6ggN1vU4n6r7mpbgeYCItPtB6dnF/sVk4ftsgAUcadnkng.q','',257,'123456789@qq.com','','2','d13c792c912440e8af8a1a8116ba49af','','0',NULL,NULL,'2025-03-02 00:00:00','2025-09-04 00:00:22','sys:1',_binary '\0');

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

-- Dump completed on 2025-09-25  0:27:46
