-- MySQL dump 10.13  Distrib 5.5.19, for Win64 (x86)
--
-- Host: 127.0.0.1    Database: houhan_library
-- ------------------------------------------------------
-- Server version	5.5.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `apply_record`
--

DROP TABLE IF EXISTS `apply_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apply_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apply_remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `review_remark` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `book_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaislx5163mfwcxewkde1ajgg4` (`book_id`),
  KEY `FK3ya7rde8ifuka0kk8e7sxn5ii` (`user_id`),
  CONSTRAINT `FK3ya7rde8ifuka0kk8e7sxn5ii` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKaislx5163mfwcxewkde1ajgg4` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apply_record`
--

LOCK TABLES `apply_record` WRITE;
/*!40000 ALTER TABLE `apply_record` DISABLE KEYS */;
INSERT INTO `apply_record` VALUES (1,'','2017-09-18 21:20:22',NULL,'',1,2,'2017-09-18 21:20:22',2,6);
/*!40000 ALTER TABLE `apply_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `num` bigint(20) NOT NULL,
  `press` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKam9riv8y6rjwkua1gapdfew4j` (`category_id`),
  CONSTRAINT `FKam9riv8y6rjwkua1gapdfew4j` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (2,'Tobby','2017-09-17 18:59:48','Kotlin Something',NULL,NULL,'Kotlin Something',0,'Pearson','\0','2017-09-17 18:59:48',3),(3,'One','2017-09-18 20:27:36','Ruby Something',NULL,NULL,'Ruby Something',0,'Pearson','\0','2017-09-18 20:27:36',3);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow_record`
--

DROP TABLE IF EXISTS `borrow_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `borrow_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `return_time` datetime DEFAULT NULL,
  `should_return_time` datetime DEFAULT NULL,
  `status` int(11) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `book_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKblllbxv8r2dt3j07c3hgdruqi` (`book_id`),
  KEY `FKavuecet365b04i1scrarwsirr` (`user_id`),
  CONSTRAINT `FKavuecet365b04i1scrarwsirr` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKblllbxv8r2dt3j07c3hgdruqi` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow_record`
--

LOCK TABLES `borrow_record` WRITE;
/*!40000 ALTER TABLE `borrow_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `borrow_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `parent_cat_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhk8thuicuab2cfi49w504ou7m` (`parent_cat_id`),
  CONSTRAINT `FKhk8thuicuab2cfi49w504ou7m` FOREIGN KEY (`parent_cat_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'2017-09-17 18:47:18','文学','文学','2017-09-17 18:47:18',NULL),(2,'2017-09-17 18:47:35','历史','历史','2017-09-17 18:47:35',NULL),(3,'2017-09-17 18:59:32','Coding','Coding','2017-09-17 18:59:32',NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `parent_dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfah4pv78w9qc8lmb9pwhg8iel` (`parent_dept_id`),
  CONSTRAINT `FKfah4pv78w9qc8lmb9pwhg8iel` FOREIGN KEY (`parent_dept_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'2017-09-16 22:54:14','技术支持','技术部','2017-09-16 22:54:14',NULL),(2,'2017-09-16 22:59:04','产品部','产品部','2017-09-16 22:59:04',NULL),(3,'2017-09-17 11:42:32','行政','行政部','2017-09-17 11:42:32',NULL),(4,'2017-09-17 17:14:18','商务something','商务部','2017-09-17 17:14:18',NULL);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `privilege` varchar(255) DEFAULT NULL,
  `total_borrow_num` int(11) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'2017-09-17 11:38:31','新秀, 初级','新秀','',5,'2017-09-17 11:38:31'),(2,'2017-09-17 11:40:24','盟主, superman','盟主','',5,'2017-09-17 11:40:24');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birth` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `pw` varchar(255) DEFAULT NULL,
  `sex` int(11) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `user_borrow_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgkh2fko1e4ydv1y6vtrwdc6my` (`department_id`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`),
  KEY `FKfp5814ax9pkfhgroogen11vdu` (`user_borrow_id`),
  CONSTRAINT `FKfp5814ax9pkfhgroogen11vdu` FOREIGN KEY (`user_borrow_id`) REFERENCES `user_borrow` (`id`),
  CONSTRAINT `FKgkh2fko1e4ydv1y6vtrwdc6my` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (5,'1993-01-20','2017-09-17 13:49:04',NULL,'18122222222','superman','不晓得','123456',1,'2017-09-17 13:49:04',3,2,1),(6,'1991-01-20','2017-09-17 13:53:24',NULL,'18133333333','Bobo','java开发','123456',0,'2017-09-17 13:53:24',1,1,2),(7,'1993-08-20','2017-09-17 17:50:40',NULL,'18155555555','Lucy','商务something','123456',1,'2017-09-17 17:50:40',4,1,3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_borrow`
--

DROP TABLE IF EXISTS `user_borrow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_borrow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `cur_borrow` int(11) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_borrow`
--

LOCK TABLES `user_borrow` WRITE;
/*!40000 ALTER TABLE `user_borrow` DISABLE KEYS */;
INSERT INTO `user_borrow` VALUES (1,'2017-09-17 13:49:04',0,'2017-09-17 13:49:04'),(2,'2017-09-17 13:53:24',0,'2017-09-17 13:53:24'),(3,'2017-09-17 17:50:40',0,'2017-09-17 17:50:40');
/*!40000 ALTER TABLE `user_borrow` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-18 22:52:19
