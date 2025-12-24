-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: flintzy_db
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `facebook_pages`
--

DROP TABLE IF EXISTS `facebook_pages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facebook_pages` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `page_access_token` varchar(500) DEFAULT NULL,
  `page_id` varchar(255) DEFAULT NULL,
  `page_name` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKsgbbmp8i572o4by9yg3ajv0t8` (`page_id`,`user_id`),
  KEY `FKgfsjs1vav33caxi2foampd1kn` (`user_id`),
  CONSTRAINT `FKgfsjs1vav33caxi2foampd1kn` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facebook_pages`
--

LOCK TABLES `facebook_pages` WRITE;
/*!40000 ALTER TABLE `facebook_pages` DISABLE KEYS */;
INSERT INTO `facebook_pages` VALUES (3,'EAAixnhTOOT0BQQt7XzcLKssOsGdGiirCmZAi1wAxaeKxPfLWEe1yxE9O8ZCmndpOHKfoHyfE9HgR3PgEqSsmu8uj4ZBBAITYQCLAQaZBB3ShK9CqDnokW57zlRIT4l7KeDELbxRW4T5ojhZBakNFkk3R3UDeprLN9B43phlfkRf3q9sILBy9NZBorR3wpBZCBoBbJm7ZCicommEfkOR6LwxaxXMaFjgXgqZBu8IJ9f6kZD','852957511245030','Flintzy Test Page',4);
/*!40000 ALTER TABLE `facebook_pages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `facebook_post_id` varchar(255) DEFAULT NULL,
  `pageid` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5lidm6cqbc7u4xhqpxm898qme` (`user_id`),
  CONSTRAINT `FK5lidm6cqbc7u4xhqpxm898qme` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (1,'Hello this is the test Post done by Flintzy backend team and it was published using Spring Boot + Facebook Graph API','2025-12-22 16:55:49.989051','852957511245030_122093526453190628','852957511245030','PUBLISH SUCESS',4),(2,'Final Post in the testing phase made By Flintzy backend team','2025-12-23 14:23:51.123355','852957511245030_122094052965190628','852957511245030','PUBLISH SUCESS',4);
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `provider` enum('GOOGLE') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (4,'2025-12-22 16:39:11.857812','bheeshmagaming@gmail.com','Bheeshma_Gaming','GOOGLE');
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

-- Dump completed on 2025-12-24 16:20:59
