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
  `pokedexNumber` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `combatPower` int(11) DEFAULT NULL,
  `healthPoints` int(11) DEFAULT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `lat` float DEFAULT NULL,
  `lng` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ownerId_idx` (`ownerId`),
  CONSTRAINT `ownerId` FOREIGN KEY (`ownerId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemon`
--

LOCK TABLES `pokemon` WRITE;
/*!40000 ALTER TABLE `pokemon` DISABLE KEYS */;
INSERT INTO `pokemon` VALUES (1,134124,'Pokemongo',12,234,30,NULL,NULL),(2,134124,'Pokemongo',12,234,30,NULL,NULL),(3,134124,'Pokemongo',12,234,30,NULL,NULL);
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
  `pokedexNumber` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `imageUrl` varchar(2083) CHARACTER SET utf8 DEFAULT NULL,
  `spriteUrl` varchar(2083) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemondata`
--

LOCK TABLES `pokemondata` WRITE;
/*!40000 ALTER TABLE `pokemondata` DISABLE KEYS */;
INSERT INTO `pokemondata` VALUES (1,1,'Bulbasaur','http://www.serebii.net/pokemongo/pokemon/001.png\n\n','http://www.serebii.net/pokearth/sprites/em/001.png'),(4,4,'Charmander','http://www.serebii.net/pokemongo/pokemon/004.png','http://www.serebii.net/pokearth/sprites/em/004.png'),(7,7,'Squirtle','http://www.serebii.net/pokemongo/pokemon/007.png','http://www.serebii.net/pokearth/sprites/em/007.png'),(10,10,'Caterpie','http://www.serebii.net/pokemongo/pokemon/010.png','http://www.serebii.net/pokearth/sprites/em/010.png');
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
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'Försvunnen Pokemon','Någon som sett min Pokemon? Blå, fulsnygg och lite tjock. Sågs senast vid Stenpiren.','2016-09-14 08:53:07',30,NULL),(2,'Mat till min Pokemon','Vad äter en Pokemon?','2016-09-14 08:53:27',30,NULL),(3,'Fredag','Nån som ska med på AW på fredag? Glöm inte att uppdatera appen till dess!','2016-09-14 08:53:45',30,NULL);
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (27,'Testuser A','testmail-a@mail.com',NULL),(28,'Testuser B','testmail-b@mail.com',NULL),(29,'Testuser C','testmail-c@mail.com',NULL),(30,'Testgubbe','gubbe@gubbe.se','');
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

-- Dump completed on 2016-09-27 14:38:35
