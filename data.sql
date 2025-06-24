-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: big_event
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(30) NOT NULL COMMENT '文章标题',
  `content` varchar(10000) NOT NULL COMMENT '文章内容',
  `cover_img` varchar(128) NOT NULL COMMENT '文章封面',
  `state` varchar(3) DEFAULT '草稿' COMMENT '文章状态: 只能是[已发布] 或者 [草稿]',
  `category_id` int unsigned DEFAULT NULL COMMENT '文章分类ID',
  `create_user` int unsigned NOT NULL COMMENT '创建人ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `fk_article_category` (`category_id`),
  KEY `fk_article_user` (`create_user`),
  CONSTRAINT `fk_article_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `fk_article_user` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

/*!40000 ALTER TABLE `article` DISABLE KEYS */;
/*!40000 ALTER TABLE `article` ENABLE KEYS */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `category_name` varchar(32) NOT NULL COMMENT '分类名称',
  `category_alias` varchar(32) NOT NULL COMMENT '分类别名',
  `create_user` int unsigned NOT NULL COMMENT '创建人ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `fk_category_user` (`create_user`),
  CONSTRAINT `fk_category_user` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (11,'漫画','漫画',1,'2024-06-15 12:48:27','2024-06-15 12:48:28'),(12,'历史','历史',1,'2024-06-18 13:20:28','2024-06-18 13:20:28'),(17,'晚安叨叨','daodao',1,'2024-06-19 18:53:26','2024-06-19 18:53:26');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'急诊科'),(2,'外科'),(3,'内科'),(4,'检验科'),(5,'妇产科'),(6,'儿科'),(7,'神经科'),(8,'放射科'),(9,'药剂科');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `picture` varchar(128) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `department_id` int DEFAULT NULL,
  `id_card` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (11,1,'123',NULL,'男',123,'张三',1,'123'),(16,15,'119',NULL,'男',10000,'lxr',6,'25552225'),(20,16,'123123',NULL,'男',123,'李四',1,'123123'),(23,23,'123123123',NULL,'女',111,'叨叨',4,'123123123123'),(30,25,'34',NULL,'男',565,'anyone',2,'34'),(35,26,'123',NULL,'男',22,'lala',1,'111'),(40,27,'123',NULL,'男',20,'嘻嘻',4,'123123123123'),(42,28,'123123',NULL,'男',23,'xixi',2,'13123123');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;

--
-- Table structure for table `doctor_patient`
--

DROP TABLE IF EXISTS `doctor_patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor_patient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `doctor_id` int DEFAULT NULL,
  `patient_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='陪诊师和患者表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_patient`
--

/*!40000 ALTER TABLE `doctor_patient` DISABLE KEYS */;
INSERT INTO `doctor_patient` VALUES (5,11,9),(6,11,12),(7,11,13),(8,16,14),(9,11,15),(10,20,16),(11,11,17),(12,11,18),(13,11,19),(17,11,23),(18,27,24),(19,11,25),(21,30,27),(22,16,28),(23,11,29),(24,11,30),(25,11,31),(26,11,32);
/*!40000 ALTER TABLE `doctor_patient` ENABLE KEYS */;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `doctor_id` int DEFAULT NULL,
  `visit_time` varchar(30) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `caseDescription` varchar(200) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL COMMENT '接送地址',
  `service_type` varchar(100) DEFAULT NULL COMMENT '服务类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (9,'李四','男','123123',12,11,'2024-06-20 11:20:36 ','来接我','80岁以上,就诊人骨折且未进行医护处理',2,'无','院内陪诊(不含接送)'),(10,'lala',NULL,NULL,NULL,12,NULL,NULL,NULL,NULL,'无','院内陪诊(不含接送)'),(11,'haha',NULL,NULL,NULL,13,NULL,NULL,NULL,NULL,'无','院内陪诊(不含接送)'),(12,'lala','男','1111',120,11,'2024-06-04 12:00:00 ','lalallala','冠状动脉粥样硬化心脏病患者,严重冠心病患者',2,'无','院内陪诊(不含接送)'),(13,'lxr','女','119',1000,11,'2024-06-03 12:00:00 ','医生给我当牛马','80岁以上',15,'无','院内陪诊(不含接送)'),(14,'林晓荣','女','999',999999,16,'2024-06-12 12:00:00 ','我是精神病','80岁以上,就诊人骨折且未进行医护处理,就诊人不能行走且无助走工具或轮椅,冠状动脉粥样硬化心脏病患者,严重冠心病患者,有脑血管疾病,就诊人为精神病患者',2,'无','院内陪诊(不含接送)'),(15,'huahua','男','111111111',111,11,'2024-06-04 12:00:00 ','123','80岁以上,就诊人骨折且未进行医护处理,就诊人不能行走且无助走工具或轮椅',16,'无','院内陪诊(不含接送)'),(16,'叨叨','','123123123',12,20,'2024-06-03 03:01:02 ','来接我','80岁以上,就诊人骨折且未进行医护处理',2,'无','院内陪诊(不含接送)'),(17,'我要吃薯条','男','1111111',111,11,'2024-07-09 12:00:00 ','来接我','80岁以上,就诊人骨折且未进行医护处理',2,'福州大学','尊享陪诊'),(18,'我要吃汉堡','男','111111',121,11,'2024-07-02 12:06:00 ','呼呼呼呼','就诊人不能行走且无助走工具或轮椅',23,'lalala','尊享陪诊'),(19,'晚安叨叨','女','123123123',20,11,'2024-07-31 03:00:00 ','嘻嘻嘻嘻','80岁以上',2,'无','代取报告'),(23,'xixi','男','111111',12,11,'2024-07-03 12:00:00 ','1111','80岁以上,就诊人骨折且未进行医护处理',24,'无','院内陪诊(不含接送)'),(24,'jk','男','234',4,27,'2024-07-07 12:00:00 ','35332','就诊人不能行走且无助走工具或轮椅',25,'35','全程陪诊'),(25,'王瑞芳','女','1646',45,11,'2024-07-09 12:00:00 ','','冠状动脉粥样硬化心脏病患者',25,'无','尊享陪诊'),(27,'jakc','女','54',56,30,'2024-07-07 12:00:00 ','iij','就诊人不能行走且无助走工具或轮椅',1,'45','全程陪诊'),(28,'5','男','4',4,16,'2024-07-09 12:00:00 ','','无',25,'无','院内陪诊(不含接送)'),(29,'额大头鬼','女','456',4,11,'2024-07-02 12:00:00 ','','就诊人骨折且未进行医护处理',25,'无','院内陪诊(不含接送)'),(30,'xixi','男','123123',23,11,'2024-07-16 07:02:02 ','哈哈哈哈哈哈哈哈','冠状动脉粥样硬化心脏病患者,80岁以上,就诊人为精神病患者',26,' 福州大学','尊享陪诊'),(31,'lalala','女','123123',12,11,'2024-07-02 08:11:43 ','哈哈哈哈哈哈哈','就诊人为精神病患者',27,'福州大学','尊享陪诊'),(32,'xixi','男','123123',20,11,'2024-07-02 02:54:50 ','hhhh','冠状动脉粥样硬化心脏病患者',28,'12312312','尊享陪诊');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;

--
-- Table structure for table `service_type`
--

DROP TABLE IF EXISTS `service_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(100) DEFAULT NULL COMMENT '服务类型',
  `price` int DEFAULT NULL COMMENT '价格',
  `description` varchar(200) DEFAULT NULL COMMENT '服务详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='服务类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_type`
--

/*!40000 ALTER TABLE `service_type` DISABLE KEYS */;
INSERT INTO `service_type` VALUES (1,'院内陪诊(不含接送)',1999999,'院陪诊师协助完成病友院内就诊流程，含规划就诊流程、协助排队取号、排队缴费等服务'),(2,'代取报告',999999,'取送报告到家服务含异地寄送'),(3,'代问诊',1999999,'代病友到医疗机构咨询及代开检查单服务'),(4,'尊享陪诊',5999999,'一条龙服务'),(5,'全程陪诊',3999999,'全程陪着');
/*!40000 ALTER TABLE `service_type` ENABLE KEYS */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(10) DEFAULT '' COMMENT '昵称',
  `email` varchar(128) DEFAULT '' COMMENT '邮箱',
  `user_pic` varchar(128) DEFAULT '' COMMENT '头像',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `role` varchar(30) DEFAULT 'user',
  `serviceType` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'daodao','4297f44b13955235245b2497399d7a93','晚安叨叨','76898926@qq.com','https://wanandaodao-sky-take-out.oss-cn-hangzhou.aliyuncs.com/b1f20717-bf90-45de-8fa6-387eb0eb6afe.png','2024-06-10 16:18:56','2024-07-01 19:23:16','admin',''),(2,'wanandaodao','e10adc3949ba59abbe56e057f20f883e','叨叨','768989265@qq.com','https://wanandaodao-sky-take-out.oss-cn-hangzhou.aliyuncs.com/04545e88-92c0-43cf-a4e2-20ca46502eea.png','2024-06-10 16:19:53','2024-06-19 21:48:28','user',NULL),(3,'admin','e10adc3949ba59abbe56e057f20f883e','叨叨','768989265@qq.com','https://wanandaodao-sky-take-out.oss-cn-hangzhou.aliyuncs.com/04545e88-92c0-43cf-a4e2-20ca46502eea.png','2024-06-10 16:21:37','2024-06-19 21:48:28','user',NULL),(10,'ddddd','4297f44b13955235245b2497399d7a93','叨叨','768989265@qq.com','https://wanandaodao-sky-take-out.oss-cn-hangzhou.aliyuncs.com/04545e88-92c0-43cf-a4e2-20ca46502eea.png','2024-06-14 22:24:37','2024-06-19 21:48:28','user',NULL),(11,'aaaaa','4297f44b13955235245b2497399d7a93','叨叨','768989265@qq.com','https://wanandaodao-sky-take-out.oss-cn-hangzhou.aliyuncs.com/04545e88-92c0-43cf-a4e2-20ca46502eea.png','2024-06-14 22:24:50','2024-06-19 21:48:28','user',NULL),(12,'ccccc','4297f44b13955235245b2497399d7a93','叨叨','768989265@qq.com','https://wanandaodao-sky-take-out.oss-cn-hangzhou.aliyuncs.com/04545e88-92c0-43cf-a4e2-20ca46502eea.png','2024-06-14 22:25:19','2024-06-19 21:48:28','doctor',NULL),(13,'eqweq','4297f44b13955235245b2497399d7a93','叨叨','768989265@qq.com','https://wanandaodao-sky-take-out.oss-cn-hangzhou.aliyuncs.com/04545e88-92c0-43cf-a4e2-20ca46502eea.png','2024-06-14 22:40:51','2024-06-19 21:48:28','user',NULL),(14,'wanan','111111','叨叨','','https://wanandaodao-sky-take-out.oss-cn-hangzhou.aliyuncs.com/57998dc2-a58b-4ddf-9382-ba0fc8c67495.png','2024-06-19 22:27:14','2024-06-19 22:28:22','user',NULL),(15,'linnnnn','e10adc3949ba59abbe56e057f20f883e','','','https://wanandaodao-sky-take-out.oss-cn-hangzhou.aliyuncs.com/b2d79959-c46b-4ff8-83e0-c8463e6f0ab5.jpg','2024-06-21 15:49:41','2024-06-21 15:51:31','doctor',NULL),(16,'123123','4297f44b13955235245b2497399d7a93','123123','123123','','2024-06-23 16:45:29','2024-06-26 17:53:33','doctor',NULL),(23,'111111','4297f44b13955235245b2497399d7a93','晚安叨叨','','https://wanandaodao-sky-take-out.oss-cn-hangzhou.aliyuncs.com/4c5f4f3f-9dd1-43f4-a5ed-b0c644b90662.png','2024-07-01 00:45:35','2024-07-01 00:46:39','doctor',NULL),(24,'wanandaodao1','4297f44b13955235245b2497399d7a93','叨叨','','','2024-07-01 16:10:15','2024-07-01 16:10:15','doctor',NULL),(25,'anyone','e10adc3949ba59abbe56e057f20f883e','叨叨','','','2024-07-01 22:36:42','2024-07-01 22:36:42','doctor',NULL),(26,'wanandaodao123','4297f44b13955235245b2497399d7a93','晚安叨叨','768989265@qq.com','https://wanandaodao-sky-take-out.oss-cn-hangzhou.aliyuncs.com/9b729dff-7aa4-4af9-94b2-846446777f17.png','2024-07-02 13:07:00','2024-07-02 13:10:27','doctor',NULL),(27,'xixixi','4297f44b13955235245b2497399d7a93','晚安叨叨','','https://wanandaodao-sky-take-out.oss-cn-hangzhou.aliyuncs.com/6df6ab79-a2e6-4bef-b6c6-f9f000241720.png','2024-07-02 14:07:18','2024-07-02 14:10:45','doctor',NULL),(28,'wanandaodao111','4297f44b13955235245b2497399d7a93','叨叨','','','2024-07-02 15:54:07','2024-07-02 15:54:07','doctor',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-24 18:35:52
