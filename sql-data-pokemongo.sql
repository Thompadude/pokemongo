-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: pokemongo
-- ------------------------------------------------------
-- Server version	5.7.11

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
-- Table structure for table `pokemon`
--

DROP TABLE IF EXISTS `pokemon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pokemon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pokedexNumber` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `combatPower` int(11) DEFAULT NULL,
  `healthPoints` int(11) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `lat` float DEFAULT NULL,
  `lng` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ownerId_idx` (`ownerId`),
  CONSTRAINT `ownerId` FOREIGN KEY (`ownerId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemon`
--

LOCK TABLES `pokemon` WRITE;
/*!40000 ALTER TABLE `pokemon` DISABLE KEYS */;
INSERT INTO `pokemon` VALUES (1,'003','3',12,12,31,57.7092,11.9742),(2,'001','1',23,23,31,57.7092,11.9741);
/*!40000 ALTER TABLE `pokemon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pokemondata`
--

DROP TABLE IF EXISTS `pokemondata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pokemondata` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pokedexNumber` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `imageUrl` varchar(2083) CHARACTER SET utf8 DEFAULT NULL,
  `spriteUrl` varchar(2083) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemondata`
--

LOCK TABLES `pokemondata` WRITE;
/*!40000 ALTER TABLE `pokemondata` DISABLE KEYS */;
INSERT INTO `pokemondata` VALUES (1,'001','1',NULL,NULL),(2,'002','2',NULL,NULL),(3,'003','3',NULL,NULL);
/*!40000 ALTER TABLE `pokemondata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `content` text,
  `postTime` datetime DEFAULT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `parentPostId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `posts_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (105,'a','a','2016-10-07 11:25:52',31,NULL),(106,'b','b','2016-10-07 11:39:03',31,NULL),(107,NULL,'tjo','2016-10-07 11:39:09',31,106),(108,NULL,'tjo','2016-10-07 11:39:16',31,105),(109,NULL,'tja','2016-10-07 11:39:21',31,105),(110,'bbb','bbb','2016-10-07 11:39:57',31,NULL);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `tokenId` longtext,
  `userImageName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (27,'Testuser A','testmail-a@mail.com',NULL,NULL),(28,'Testuser B','testmail-b@mail.com',NULL,NULL),(29,'Testuser C','testmail-c@mail.com',NULL,NULL),(30,'Testgubbe','gubbe@gubbe.se','',NULL),(31,'Thomas Lansing','thompa.lansing@gmail.com','eyJhbGciOiJSUzI1NiIsImtpZCI6IjQwZDg0OTU5YjY1ZGZmM2QwNTJkYjI1YmZhZTRmZTAyMmI4MzVjYTUifQ.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiYXRfaGFzaCI6ImpMZmJEOFd4TjlsYXNGVXlUZmZMZ2ciLCJhdWQiOiI4NTM5MDQ3MzU4NC1hZWFkNDJtdDRtaHRmNWQyYnJldnFkMGRhbjQ2czBkNy5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjExMTU1NjgzNDQzMzAyNDMzMDAxNyIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhenAiOiI4NTM5MDQ3MzU4NC1hZWFkNDJtdDRtaHRmNWQyYnJldnFkMGRhbjQ2czBkNy5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsImVtYWlsIjoidGhvbXBhLmxhbnNpbmdAZ21haWwuY29tIiwiaWF0IjoxNDc0OTc5ODM2LCJleHAiOjE0NzQ5ODM0MzYsIm5hbWUiOiJUaG9tYXMgTGFuc2luZyIsInBpY3R1cmUiOiJodHRwczovL2xoNi5nb29nbGV1c2VyY29udGVudC5jb20vLUtMZ0lRRm1HUjIwL0FBQUFBQUFBQUFJL0FBQUFBQUFBQU5vL2d5SW9ucmpkUFVFL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJUaG9tYXMiLCJmYW1pbHlfbmFtZSI6IkxhbnNpbmciLCJsb2NhbGUiOiJzdiJ9.r6bvBSrsiJDbdDnB4X_oKQJ6efd2EYiPNrso85t_jVgEFFY_aZGuz2WgNo6Lq6imac4zZAZkqPhKBC9Cs-9s9E_vSowsZGuPh56a5QsncBZTEjvHXdRwQ-ryafMMG-Z8595O81m1-C2b5K0KdJFf8ckxdT5dK8bfOgb1VVjJxjDl-bqeKLlNmE_ukQgpXQlQvB36lEINSAaF7l8CJMbcxK_iBJiFpV4ZhTqFrDalecKWmhEf3IDOD8Su5ciuWAt1ZfAS3JUskGLig_F45nVQL50WhadkHdhbhGPeGht2UcnfG8DBc9BTYiqZURXm5mDEL2UoYjXnitGMVHIDJl49LA',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-11  9:19:51
