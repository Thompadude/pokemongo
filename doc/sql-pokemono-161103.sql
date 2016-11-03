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
  `timeAdded` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ownerId_idx` (`ownerId`),
  CONSTRAINT `ownerId` FOREIGN KEY (`ownerId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemon`
--

LOCK TABLES `pokemon` WRITE;
/*!40000 ALTER TABLE `pokemon` DISABLE KEYS */;
INSERT INTO `pokemon` VALUES (38,'2','Ivysaur',55,68,35,57.7088,11.9744,'2016-10-20 11:28:32'),(39,'17','Pidgeotto',62,54,35,57.7087,11.974,'2016-10-20 11:29:01'),(49,'1','Bulbasaur',23,23,36,57.7089,11.9744,'2016-11-03 12:41:46'),(50,'12','Butterfree',23,23,36,57.7088,11.9746,'2016-11-03 12:44:06');
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemondata`
--

LOCK TABLES `pokemondata` WRITE;
/*!40000 ALTER TABLE `pokemondata` DISABLE KEYS */;
INSERT INTO `pokemondata` VALUES (1,1,'Bulbasaur'),(2,2,'Ivysaur'),(3,3,'Venusaur'),(4,4,'Charmander'),(5,5,'Charmeleon'),(6,6,'Charizard'),(7,7,'Squirtle'),(8,8,'Wartortle'),(9,9,'Blastoise'),(10,10,'Caterpie'),(11,11,'Metapod'),(12,12,'Butterfree'),(13,13,'Weedle'),(14,14,'Kakuna'),(15,15,'Beedrill'),(16,16,'Pidgey'),(17,17,'Pidgeotto'),(18,18,'Pidgeot'),(19,19,'Rattata'),(20,20,'Raticate'),(21,21,'Spearow'),(22,22,'Fearow'),(23,23,'Ekans'),(24,24,'Arbok'),(25,25,'Pikachu'),(26,26,'Raichu'),(27,27,'Sandshrew'),(28,28,'Sandslash'),(29,29,'Nidoran&#9792;'),(30,30,'Nidorina'),(31,31,'Nidoqueen'),(32,32,'Nidoran&#9794;'),(33,33,'Nidorino'),(34,34,'Nidoking'),(35,35,'Clefairy'),(36,36,'Clefable'),(37,37,'Vulpix'),(38,38,'Ninetales'),(39,39,'Jigglypuff'),(40,40,'Wigglytuff'),(41,41,'Zubat'),(42,42,'Golbat'),(43,43,'Oddish'),(44,44,'Gloom'),(45,45,'Vileplume'),(46,46,'Paras'),(47,47,'Parasect'),(48,48,'Venonat'),(49,49,'Venomoth'),(50,50,'Diglett'),(51,51,'Dugtrio'),(52,52,'Meowth'),(53,53,'Persian'),(54,54,'Psyduck'),(55,55,'Golduck'),(56,56,'Mankey'),(57,57,'Primeape'),(58,58,'Growlithe'),(59,59,'Arcanine'),(60,60,'Poliwag'),(61,61,'Poliwhirl'),(62,62,'Poliwrath'),(63,63,'Abra'),(64,64,'Kadabra'),(65,65,'Alakazam'),(66,66,'Machop'),(67,67,'Machoke'),(68,68,'Machamp'),(69,69,'Bellsprout'),(70,70,'Weepinbell'),(71,71,'Victreebel'),(72,72,'Tentacool'),(73,73,'Tentacruel'),(74,74,'Geodude'),(75,75,'Graveler'),(76,76,'Golem'),(77,77,'Ponyta'),(78,78,'Rapidash'),(79,79,'Slowpoke'),(80,80,'Slowbro'),(81,81,'Magnemite'),(82,82,'Magneton'),(83,83,'Farfetch\'d'),(84,84,'Doduo'),(85,85,'Dodrio'),(86,86,'Seel'),(87,87,'Dewgong'),(88,88,'Grimer'),(89,89,'Muk'),(90,90,'Shellder'),(91,91,'Cloyster'),(92,92,'Gastly'),(93,93,'Haunter'),(94,94,'Gengar'),(95,95,'Onix'),(96,96,'Drowzee'),(97,97,'Hypno'),(98,98,'Krabby'),(99,99,'Kingler'),(100,100,'Voltorb'),(101,101,'Electrode'),(102,102,'Exeggcute'),(103,103,'Exeggutor'),(104,104,'Cubone'),(105,105,'Marowak'),(106,106,'Hitmonlee'),(107,107,'Hitmonchan'),(108,108,'Lickitung'),(109,109,'Koffing'),(110,110,'Weezing'),(111,111,'Rhyhorn'),(112,112,'Rhydon'),(113,113,'Chansey'),(114,114,'Tangela'),(115,115,'Kangaskhan'),(116,116,'Horsea'),(117,117,'Seadra'),(118,118,'Goldeen'),(119,119,'Seaking'),(120,120,'Staryu'),(121,121,'Starmie'),(122,122,'Mr.'),(123,123,'Scyther'),(124,124,'Jynx'),(125,125,'Electabuzz'),(126,126,'Magmar'),(127,127,'Pinsir'),(128,128,'Tauros'),(129,129,'Magikarp'),(130,130,'Gyarados'),(131,131,'Lapras'),(132,132,'Ditto'),(133,133,'Eevee'),(134,134,'Vaporeon'),(135,135,'Jolteon'),(136,136,'Flareon'),(137,137,'Porygon'),(138,138,'Omanyte'),(139,139,'Omastar'),(140,140,'Kabuto'),(141,141,'Kabutops'),(142,142,'Aerodactyl'),(143,143,'Snorlax'),(144,144,'Articuno'),(145,145,'Zapdos'),(146,146,'Moltres'),(147,147,'Dratini'),(148,148,'Dragonair'),(149,149,'Dragonite'),(150,150,'Mewtwo'),(151,151,'Mew');
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
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (121,'YOYOYO','MANOGGER','2016-10-18 14:42:42',35,NULL),(122,'MOOORE!','CAPS!!!','2016-10-18 14:51:12',35,NULL),(123,NULL,'COMMENT TO MOOORE!','2016-10-18 14:52:12',35,122),(128,'QWERTY','QWERTY','2016-11-03 14:16:26',36,NULL),(129,'Färska bullar','FÄRSKA BULLAR','2016-11-03 14:32:01',37,NULL),(130,'Det finns fisk i affären','DET FINNS FISK I AFFÄREN','2016-11-03 14:33:55',38,NULL);
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
  `userImageName` varchar(45) DEFAULT NULL,
  `team` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (35,'Tobias Ljungström','tobias.ljungstrom@gmail.com','35.jpg','none'),(36,'Thomas Lansing','thompa.lansing@gmail.com','36.png','none'),(37,'Kalle Brallsson','kallebrallsson1@gmail.com','37.jpg','none'),(38,'Rigmor Osvaldsson','rigmorosvaldsson1@gmail.com','38.jpg','none');
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

-- Dump completed on 2016-11-03 14:36:54
