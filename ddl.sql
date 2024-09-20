-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 192.168.146.10    Database: pub_1
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Current Database: `pub_1`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `pub_1` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `pub_1`;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friends` (
                           `user_id` int NOT NULL COMMENT '用户编号',
                           `friend_id` int NOT NULL COMMENT '朋友编号',
                           `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           PRIMARY KEY (`user_id`,`friend_id`) USING BTREE,
                           KEY `friend_id` (`friend_id`) USING BTREE,
                           KEY `user_id` (`user_id`),
                           CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES (0,1,'2024-09-19 08:49:04'),(0,2,'2024-09-19 08:49:09'),(0,3,'2024-09-19 08:49:12'),(2,0,'2024-09-19 08:49:23'),(2,1,'2024-09-19 08:49:36');
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts_1`
--

DROP TABLE IF EXISTS `posts_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts_1` (
                           `post_id` int NOT NULL AUTO_INCREMENT COMMENT '发布编号',
                           `user_id` int NOT NULL COMMENT '用户编号',
                           `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发布内容',
                           `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '发布图片',
                           `location` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '发布位置',
                           `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
                           PRIMARY KEY (`post_id`) USING BTREE,
                           KEY `user_id` (`user_id`) USING BTREE,
                           KEY `created_time` (`created_time` DESC),
                           CONSTRAINT `posts_1_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts_1`
--

LOCK TABLES `posts_1` WRITE;
/*!40000 ALTER TABLE `posts_1` DISABLE KEYS */;
INSERT INTO `posts_1` VALUES (18,0,'1234','','','2024-09-19 16:06:22'),(19,2,'12345','','','2024-09-19 16:06:22'),(20,2,'12345','','','2024-09-19 16:06:22'),(21,2,'12345','','','2024-09-19 16:06:22'),(22,0,'12345','','','2024-09-19 16:06:22'),(23,0,'12345','','','2024-09-19 16:06:22'),(24,0,'12345','','','2024-09-19 16:06:22'),(25,0,'12345','','','2024-09-19 16:06:22');
/*!40000 ALTER TABLE `posts_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts_2`
--

DROP TABLE IF EXISTS `posts_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts_2` (
                           `post_id` int NOT NULL AUTO_INCREMENT COMMENT '发布编号',
                           `user_id` int NOT NULL COMMENT '用户编号',
                           `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发布内容',
                           `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '发布图片',
                           `location` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '发布位置',
                           `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
                           PRIMARY KEY (`post_id`) USING BTREE,
                           KEY `user_id` (`user_id`) USING BTREE,
                           KEY `created_time` (`created_time` DESC),
                           CONSTRAINT `posts_2_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts_2`
--

LOCK TABLES `posts_2` WRITE;
/*!40000 ALTER TABLE `posts_2` DISABLE KEYS */;
/*!40000 ALTER TABLE `posts_2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                         `user_id` int NOT NULL COMMENT '用户编号',
                         `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
                         `user_password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
                         `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户邮箱',
                         `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         PRIMARY KEY (`user_id`) USING BTREE,
                         UNIQUE KEY `user_name` (`user_name`) USING BTREE,
                         UNIQUE KEY `email` (`email`) USING BTREE,
                         KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (0,'hhh','hhh','123@qq.com','2024-09-19 03:05:47'),(2,'cc','cc','12345@qq.com','2024-09-18 09:51:14');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `pub_2`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `pub_2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `pub_2`;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friends` (
                           `user_id` int NOT NULL COMMENT '用户编号',
                           `friend_id` int NOT NULL COMMENT '朋友编号',
                           `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           PRIMARY KEY (`user_id`,`friend_id`) USING BTREE,
                           KEY `friend_id` (`friend_id`) USING BTREE,
                           KEY `user_id` (`user_id`),
                           CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES (1,0,'2024-09-19 08:49:17'),(1,2,'2024-09-19 08:49:34'),(3,0,'2024-09-19 08:49:27');
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts_1`
--

DROP TABLE IF EXISTS `posts_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts_1` (
                           `post_id` int NOT NULL AUTO_INCREMENT COMMENT '发布编号',
                           `user_id` int NOT NULL COMMENT '用户编号',
                           `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发布内容',
                           `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '发布图片',
                           `location` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '发布位置',
                           `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
                           PRIMARY KEY (`post_id`) USING BTREE,
                           KEY `user_id` (`user_id`) USING BTREE,
                           KEY `created_time` (`created_time` DESC),
                           CONSTRAINT `posts_1_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts_1`
--

LOCK TABLES `posts_1` WRITE;
/*!40000 ALTER TABLE `posts_1` DISABLE KEYS */;
/*!40000 ALTER TABLE `posts_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts_2`
--

DROP TABLE IF EXISTS `posts_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts_2` (
                           `post_id` int NOT NULL AUTO_INCREMENT COMMENT '发布编号',
                           `user_id` int NOT NULL COMMENT '用户编号',
                           `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发布内容',
                           `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '发布图片',
                           `location` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '发布位置',
                           `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
                           PRIMARY KEY (`post_id`) USING BTREE,
                           KEY `user_id` (`user_id`) USING BTREE,
                           KEY `created_time` (`created_time` DESC),
                           CONSTRAINT `posts_2_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts_2`
--

LOCK TABLES `posts_2` WRITE;
/*!40000 ALTER TABLE `posts_2` DISABLE KEYS */;
INSERT INTO `posts_2` VALUES (5,1,'12345','','','2024-09-19 16:06:22'),(6,3,'12345','','','2024-09-19 16:06:22'),(7,3,'12345','','','2024-09-19 16:06:22'),(8,3,'12345','','','2024-09-19 16:06:22'),(9,1,'12345','','','2024-09-19 16:06:22'),(10,1,'12345','','','2024-09-19 16:06:22'),(11,1,'12345','','','2024-09-19 16:06:22'),(12,1,'12345','','','2024-09-19 16:06:23'),(13,1,'12345','','','2024-09-19 16:06:24'),(14,1,'12345','','','2024-09-19 16:06:25'),(15,1,'12345','','','2024-09-19 16:06:26'),(16,1,'12345','','','2024-09-19 16:06:27'),(17,1,'12345','','','2024-09-19 16:06:28'),(18,1,'12345','','','2024-09-19 16:06:29'),(19,1,'12345','','','2024-09-19 16:07:01'),(20,1,'12345','','','2024-09-19 16:07:02'),(21,1,'12345','','','2024-09-19 16:07:03'),(22,1,'12345','','','2024-09-19 16:07:03'),(23,1,'12345','','','2024-09-19 16:07:04'),(24,1,'12345','','','2024-09-19 16:07:05'),(25,1,'12345','','','2024-09-19 16:07:06');
/*!40000 ALTER TABLE `posts_2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                         `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户编号',
                         `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
                         `user_password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
                         `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户邮箱',
                         `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         PRIMARY KEY (`user_id`) USING BTREE,
                         UNIQUE KEY `user_name` (`user_name`) USING BTREE,
                         UNIQUE KEY `email` (`email`) USING BTREE,
                         KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'user','user','123456@qq.com','2024-09-18 17:50:23'),(3,'admin','admin','874587675@qq.com','2024-09-18 17:49:08');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-19 19:18:32
