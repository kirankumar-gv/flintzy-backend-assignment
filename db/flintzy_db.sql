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
  `page_id` varchar(255) NOT NULL,
  `page_name` varchar(255) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKsgbbmp8i572o4by9yg3ajv0t8` (`page_id`,`user_id`),
  KEY `FKgfsjs1vav33caxi2foampd1kn` (`user_id`),
  CONSTRAINT `FKgfsjs1vav33caxi2foampd1kn` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facebook_pages`
--

LOCK TABLES `facebook_pages` WRITE;
/*!40000 ALTER TABLE `facebook_pages` DISABLE KEYS */;
INSERT INTO `facebook_pages` VALUES (1,'EAAixnhTOOT0BQWMXlkPNg1fSd2oeE3WiRHrhqglOZCNoof5NXhXpGlFJk2Ovmd9NRFjsZBV8Xz2fgz20dYLM8P4Du95LGoEjTQui7wKu93FD9iE5CmjQSLxufXEEufZAGwZAInbEFDRTHneOX6VJf5CydSBZAZCwvZCY17D1DbUwKRXAeNFZBIAUedSfhmQJxXHIXpVef5NO','852957511245030','Flintzy Test Page',1),(2,'EAAixnhTOOT0BQd45o5EfZAwUPqXhF5HXTE8XpGdu5RAsIJvNqZAmw5wV49oGLtkwdGZBRBqZBsvQxRLRjyUWQn6sj1gVP5ZA2U2RWN5EZAGZCzcosKM9AIza8wpgTcyzHPNAQU5wdzZBqdglRw8pOOKDAZBoS9EH1FQWZCzwjYVpwnbeE4qk9HeKkWmDFFwr2px8dGpcDH','907622085774731','Flintzy Test page2',2),(3,'EAAixnhTOOT0BQTxdmp0VpDuZBwNHHXtDA1Fh6y1uji39jpLVEEU2hr6Gtzu9deMXt2N7Y1WgqqAwxzdWpIRu78oEtziWAn0qFySOtOEe6QzQUxvAh9ZBUREcoM6BwKqjr2CeZCxmnPuZC7ZCcnzDNgXAxrloQHEKcwLmpaIFGVqzzbZAO1XolOGU4KQhi0dyUjErPs','852957511245030','Flintzy Test Page',2),(4,'EAAixnhTOOT0BQfnpIi54BDbKUCFysXIZAvTyiuzd1PL7mw2UAgxFlN5H8WmdmgwAhCZCg9AfPowe3HazXjCp1bhtSgmoGzLq2fM4mWaX0fLswCYOrKg72TMRS0ZCYhj87lZBKZCLucyH6JhjR2eY8Ud22eHrkpLNRLdmSrhBfdtzpEPRupPRQ3ZChf4tYQLekxbzuVQBC0','907622085774731','Flintzy Test page2',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (1,'First Post in the testing phase made By Flintzy backend team after adjusting OAuth flow','2025-12-31 14:53:44.564415','852957511245030_122099898729190628','852957511245030','PUBLISH SUCESS',1),(2,'Test post1 OAuth flow','2026-01-02 15:36:21.603774','907622085774731_122097029373198200','907622085774731','PUBLISH SUCESS',2),(3,'Test post2 by different user linked OAuth flow','2026-01-02 17:31:14.986683','907622085774731_122097098565198200','907622085774731','PUBLISH SUCESS',1),(4,'Test post by user 2 linked, OAuth flow','2026-01-02 18:12:02.124338','852957511245030_122101655085190628','852957511245030','PUBLISH SUCESS',2),(5,'Test post by user 2 linked, OAuth flow','2026-01-02 18:32:30.189563','852957511245030_122101665963190628','852957511245030','PUBLISH SUCESS',2);
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
  `facebook_access_token` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2025-12-31 14:46:41.314608','bheeshmagaming.online@gmail.com','Kiran Kumar G V','GOOGLE','EAAixnhTOOT0BQaQjEzkKgs7VoQzFkJ083ZAarqKyMVpQdBihdkj1ZCOmvDtHZCrq9R04QJGTTlk8VLAcoq5u2HllvvfZA5O1HvqGtd9qdIAEMZBU2DijTsZBdJhUBH7nKmmmOkrEgZAJJ54iD7ED70WkhHbrf1TSwDTZBwCYOAVvP1ExAeGa2925MXudTZAoZADw428kayxfsZAqYQDkwCv1gCX7ZCyGuqgtECjeQexiFXUVDhfo3tSRn73YZAvZAUTHu8gfedTIw0wEoH4chBiGDQbQHTzWK0hm3JMYfLEAii'),(2,'2026-01-02 15:33:41.978419','kirankumargv888@gmail.com','Kiran Kumar G V','GOOGLE','EAAixnhTOOT0BQV7XtI6I5kJX1UfNIOZAacBx4ZA2vPzpSc1ZAe44KZBZAD8RBpEZC8ZBjxnE6lAXBS1gCHNxnZCgxnWiKZA7qkyoPXYY1p1gUBakTgQBLKBlBgojp42iY4WZCGBuR9qXlf4nzt5RQ3gekHKIzNvtcLK0ZAnKZBBoIvKAR9zOuBecLYfD6PAUGWAspieaGhXrV7ElHuNjKtZB7Dfw8sUXmBZBcZA92pZAyi4e9ZCkMQ4klZAYxvLbjwNZBbZADPE7CEcakFC886jbIs4o8S6Mg10Wtki2NGZCv2dQ8NBA0');
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

-- Dump completed on 2026-01-03 11:03:05
