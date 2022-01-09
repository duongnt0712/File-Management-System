CREATE DATABASE  IF NOT EXISTS `file-management` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `file-management`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: file-management
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `file` (
  `id` bigint(20) NOT NULL,
  `created_date_time` datetime DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `mime` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number_of_download` int(11) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` VALUES (9,'2022-01-03 15:26:14',593296,'image/jpeg','img_02.jpg',1,'D:\\[HANU] Forth Year\\JSD\\Assignment 3\\Assignment 3\\FileManagementSystem\\upload-files\\version 1',_binary '',1),(17,'2022-01-03 15:30:29',282320,'image/png','download20211002230524.png',0,'D:\\[HANU] Forth Year\\JSD\\Assignment 3\\Assignment 3\\FileManagementSystem\\upload-files\\version 1',_binary '',1),(11,'2022-01-03 15:26:15',3200058,'audio/mpeg','bgm.mp3',5,'D:\\[HANU] Forth Year\\JSD\\Assignment 3\\Assignment 3\\FileManagementSystem\\upload-files\\version 1',_binary '',1),(12,'2022-01-03 15:26:59',468278,'application/pdf','1-B2-ANH-Danh sach phong thi -12.12.2021.pdf',1,'D:\\[HANU] Forth Year\\JSD\\Assignment 3\\Assignment 3\\FileManagementSystem\\upload-files\\version 1',_binary '',1),(13,'2022-01-03 15:27:15',593296,'image/jpeg','img_02.jpg',2,'D:\\[HANU] Forth Year\\JSD\\Assignment 3\\Assignment 3\\FileManagementSystem\\upload-files\\version 2',_binary '',2);
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-03 15:35:48
