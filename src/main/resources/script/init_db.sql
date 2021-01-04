-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: learning_db
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `attachment_file`
--

DROP TABLE IF EXISTS `attachment_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attachment_file` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `attachment_path` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `cour_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtb8webjfkjf7uunucesoogb23` (`cour_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment_file`
--

LOCK TABLES `attachment_file` WRITE;
/*!40000 ALTER TABLE `attachment_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `attachment_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `organization_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk48cv6hfekdxqb8gbjumwsve5` (`organization_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversation`
--

DROP TABLE IF EXISTS `conversation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conversation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `last_user_send` bigint DEFAULT NULL,
  `not_read_messages` int NOT NULL,
  `user1` bigint DEFAULT NULL,
  `user2` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgvxo16fc2893m1wradl70t2ie` (`user1`),
  KEY `FKrd8ss2bgw5pr74at0dnilmorr` (`user2`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation`
--

LOCK TABLES `conversation` WRITE;
/*!40000 ALTER TABLE `conversation` DISABLE KEYS */;
/*!40000 ALTER TABLE `conversation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `arab_lang` varchar(50) DEFAULT NULL,
  `code` varchar(3) DEFAULT NULL,
  `english_lang` varchar(50) DEFAULT NULL,
  `french_lang` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cour`
--

DROP TABLE IF EXISTS `cour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cour` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `content` longtext,
  `launched` bit(1) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `module_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkbo67gv1imgarwjghfx50ucfd` (`module_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cour`
--

LOCK TABLES `cour` WRITE;
/*!40000 ALTER TABLE `cour` DISABLE KEYS */;
/*!40000 ALTER TABLE `cour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `end_date_time` datetime DEFAULT NULL,
  `launched` tinyint(1) NOT NULL DEFAULT '0',
  `name` varchar(100) DEFAULT NULL,
  `scale` double NOT NULL,
  `start_date_time` datetime DEFAULT NULL,
  `type` int DEFAULT NULL,
  `module_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe3i1ekyjqpulr2mu4ayngdnbr` (`module_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercices`
--

DROP TABLE IF EXISTS `exercices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercices` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `end_date_time` datetime DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `scale` double NOT NULL,
  `start_date_time` datetime DEFAULT NULL,
  `type` int DEFAULT NULL,
  `cour_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK39lc8cx318v0euq9ih0ab996l` (`cour_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercices`
--

LOCK TABLES `exercices` WRITE;
/*!40000 ALTER TABLE `exercices` DISABLE KEYS */;
/*!40000 ALTER TABLE `exercices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupe`
--

DROP TABLE IF EXISTS `groupe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `groupe` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `branch_id` bigint DEFAULT NULL,
  `level_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2thr8re5p1tv3ugopub4fov2d` (`branch_id`),
  KEY `FKff8pm6bocoyyppio317w33xiy` (`level_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupe`
--

LOCK TABLES `groupe` WRITE;
/*!40000 ALTER TABLE `groupe` DISABLE KEYS */;
/*!40000 ALTER TABLE `groupe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `level`
--

DROP TABLE IF EXISTS `level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `level` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `organization_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnft2a3h8u7k1jddei02s53qih` (`organization_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `level`
--

LOCK TABLES `level` WRITE;
/*!40000 ALTER TABLE `level` DISABLE KEYS */;
/*!40000 ALTER TABLE `level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `conversation_id` bigint DEFAULT NULL,
  `user_from` bigint DEFAULT NULL,
  `user_to` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6yskk3hxw5sklwgi25y6d5u1l` (`conversation_id`),
  KEY `FK8mpdm3x2cxyng0lum5jgki2lp` (`user_from`),
  KEY `FKkrwkhh9neetc3idcwbg6d972n` (`user_to`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `module`
--

DROP TABLE IF EXISTS `module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `module` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `organization_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk7oed082iq9fvvd6ryocuhaek` (`organization_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `module_affected`
--

DROP TABLE IF EXISTS `module_affected`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `module_affected` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `coefficient` double NOT NULL,
  `is_launched` tinyint(1) NOT NULL DEFAULT '0',
  `name` varchar(100) DEFAULT NULL,
  `percentage_absence` double NOT NULL,
  `percentage_cour` double NOT NULL,
  `percentage_exam` double NOT NULL,
  `percentage_quiz` double NOT NULL,
  `scale` double NOT NULL,
  `group_id` bigint DEFAULT NULL,
  `module_id` bigint DEFAULT NULL,
  `professor_id` bigint DEFAULT NULL,
  `session_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb3ryjipp0ldosoam8dbm29vk9` (`group_id`),
  KEY `FK9g3q7tip8q0dnne6hk03mb7ad` (`module_id`),
  KEY `FKpj7vpcma9ytaaexeyyy5lyyv9` (`professor_id`),
  KEY `FK5n61jwo4ntf5b4fj3olv8x8a` (`session_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module_affected`
--

LOCK TABLES `module_affected` WRITE;
/*!40000 ALTER TABLE `module_affected` DISABLE KEYS */;
/*!40000 ALTER TABLE `module_affected` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `note_exam`
--

DROP TABLE IF EXISTS `note_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `note_exam` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `finished` bit(1) NOT NULL,
  `score` double DEFAULT NULL,
  `show_score` bit(1) NOT NULL,
  `exam_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg0herogk60apbbhydbsr2u19p` (`exam_id`),
  KEY `FKe1b0xu0lx819fh0wg81eply1d` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note_exam`
--

LOCK TABLES `note_exam` WRITE;
/*!40000 ALTER TABLE `note_exam` DISABLE KEYS */;
/*!40000 ALTER TABLE `note_exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `note_exam_suggestion`
--

DROP TABLE IF EXISTS `note_exam_suggestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `note_exam_suggestion` (
  `note_exam_id` bigint NOT NULL,
  `suggestion_id` bigint NOT NULL,
  KEY `FKleyc5cer2r490740esqhayhxo` (`suggestion_id`),
  KEY `FKq04gy9c6ibch7ac5jdgr2hnoa` (`note_exam_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note_exam_suggestion`
--

LOCK TABLES `note_exam_suggestion` WRITE;
/*!40000 ALTER TABLE `note_exam_suggestion` DISABLE KEYS */;
/*!40000 ALTER TABLE `note_exam_suggestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `organization` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `deletable` tinyint(1) DEFAULT '1',
  `logo` longtext,
  `name` varchar(100) DEFAULT NULL,
  `nbr_attempt` int DEFAULT '3',
  `phone_number` varchar(255) DEFAULT NULL,
  `scale` double DEFAULT NULL,
  `thresholde_catch_up` double DEFAULT NULL,
  `thresholde_succcess` double DEFAULT NULL,
  `time_of_block` int DEFAULT '5',
  `time_zone` varchar(255) DEFAULT NULL,
  `type` int DEFAULT NULL,
  `country_id` bigint DEFAULT NULL,
  `percentage_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKodgf9ldern0bvgyin3xpcvwye` (`country_id`),
  KEY `FKkt53wrdsqqy90dhdobrdym34e` (`percentage_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization`
--

LOCK TABLES `organization` WRITE;
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
INSERT INTO `organization` VALUES (1,'2021-01-04 00:12:35','2021-01-04 00:12:35',NULL,0,NULL,'Emsi',0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `percentage`
--

DROP TABLE IF EXISTS `percentage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `percentage` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `absence_after` double DEFAULT NULL,
  `absence_before` double DEFAULT NULL,
  `cour_after` double DEFAULT NULL,
  `cour_before` double DEFAULT NULL,
  `exam_after` double DEFAULT NULL,
  `exam_before` double DEFAULT NULL,
  `quiz_after` double DEFAULT NULL,
  `quiz_before` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `percentage`
--

LOCK TABLES `percentage` WRITE;
/*!40000 ALTER TABLE `percentage` DISABLE KEYS */;
/*!40000 ALTER TABLE `percentage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `progression_cour`
--

DROP TABLE IF EXISTS `progression_cour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `progression_cour` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `cour_finished` tinyint(1) NOT NULL,
  `progression` double DEFAULT NULL,
  `td_finished` tinyint(1) NOT NULL,
  `cour_id` bigint DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkbbw6a3vcv3btyhviduoavb4j` (`cour_id`),
  KEY `FK7gq33noskn7k5bkl6iv4t7r6g` (`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `progression_cour`
--

LOCK TABLES `progression_cour` WRITE;
/*!40000 ALTER TABLE `progression_cour` DISABLE KEYS */;
/*!40000 ALTER TABLE `progression_cour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `progression_module`
--

DROP TABLE IF EXISTS `progression_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `progression_module` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `firs_note` double DEFAULT NULL,
  `first_success` bit(1) NOT NULL,
  `note_final` double DEFAULT NULL,
  `progression_absence` double DEFAULT '100',
  `progression_cour` double DEFAULT NULL,
  `progression_exam_quiz` double DEFAULT NULL,
  `second_success` bit(1) NOT NULL,
  `statut` int DEFAULT NULL,
  `module_id` bigint DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5p0n3s9ojnudff55cooq3i9cj` (`module_id`),
  KEY `FKnmc92iub31kjhxejb99jwl43n` (`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `progression_module`
--

LOCK TABLES `progression_module` WRITE;
/*!40000 ALTER TABLE `progression_module` DISABLE KEYS */;
/*!40000 ALTER TABLE `progression_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  `correct_comment` longtext,
  `index_numerator` int NOT NULL,
  `name` varchar(512) DEFAULT NULL,
  `note` double NOT NULL,
  `exam_id` bigint DEFAULT NULL,
  `exercices_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhupso6ldavcx993tfnrjsdl1p` (`exam_id`),
  KEY `FK3774c0bvkqx4k7gqjffqtm31g` (`exercices_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_client` bit(1) DEFAULT NULL,
  `name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_epk9im9l9q67xmwi4hbed25do` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,NULL,NULL,_binary '\0','ROLE_ADMIN_TECHNIQUE'),(2,NULL,NULL,_binary '','ROLE_ADMIN_CLIENT'),(3,NULL,NULL,_binary '','ROLE_TEACHER'),(4,NULL,NULL,_binary '','ROLE_STUDENT');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `session` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `end_date_catch_up` date DEFAULT NULL,
  `end_date_exam` date DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `start_date_catch_up` date DEFAULT NULL,
  `start_date_exam` date DEFAULT NULL,
  `organization_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKic1wl6c6p8jqdehbbki771cch` (`organization_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suggestion`
--

DROP TABLE IF EXISTS `suggestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suggestion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `correct` tinyint(1) NOT NULL,
  `name` varchar(512) DEFAULT NULL,
  `question_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg8q3y0mxjk7evtgs7hjhpmotw` (`question_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suggestion`
--

LOCK TABLES `suggestion` WRITE;
/*!40000 ALTER TABLE `suggestion` DISABLE KEYS */;
/*!40000 ALTER TABLE `suggestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `is_locked` tinyint(1) NOT NULL DEFAULT '0',
  `is_new` tinyint(1) NOT NULL DEFAULT '0',
  `is_offline` bit(1) NOT NULL,
  `is_online` bit(1) NOT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `old_token` longtext,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `token` varchar(512) DEFAULT NULL,
  `token_date` varchar(255) DEFAULT NULL,
  `token_date_creation` datetime DEFAULT NULL,
  `organization_id` bigint DEFAULT NULL,
  `ref_role` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi3ynrf4qjomj2hdjx7ssa3mlh` (`organization_id`),
  KEY `FKb85a4w8cq1bfgbhuk26ttm71w` (`ref_role`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,'2021-01-04 00:22:06','admint@email.com',NULL,0,0,_binary '',_binary '\0',NULL,NULL,'$2a$10$tBJs5GfjB.GdN7LU7eUrSu4Gw6EFyHIpbZIWC7AQOQUdLp5p16UhC',NULL,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbnRAZW1haWwuY29tIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9BRE1JTl9URUNITklRVUUifV0sImlhdCI6MTYwOTcxNjExNywiZXhwIjoxNjA5NzE5NzE3fQ.vULmf6RAUoIFIktGYpI4izqxz6Ik5qJ-BrK721PeTexZSCAZfJZx1iecZpMBgx68K6yu_81JsooswF_3odp7GQ',NULL,'2021-01-04 00:21:57',NULL,1),(2,'2021-01-04 00:14:45','2021-01-04 00:24:13','adminc@email.com','adminc',0,0,_binary '\0',_binary '','adminc','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbmNAZW1haWwuY29tIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9BRE1JTl9DTElFTlQifV0sImlhdCI6MTYwOTcxNjI1MiwiZXhwIjoxNjA5NzE5ODUyfQ.vOaPF7TiQF5AqFdgMzmpapI_TBaOZrz3KKB6tbsBUWf0wPLw85gjSEkZfwvs9ijkfm0pAldtJRfepqXjgnfujA','$2a$10$tBJs5GfjB.GdN7LU7eUrSu4Gw6EFyHIpbZIWC7AQOQUdLp5p16UhC','','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbmNAZW1haWwuY29tIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9BRE1JTl9DTElFTlQifV0sImlhdCI6MTYwOTcxNjI1MiwiZXhwIjoxNjA5NzE5ODUyfQ.vOaPF7TiQF5AqFdgMzmpapI_TBaOZrz3KKB6tbsBUWf0wPLw85gjSEkZfwvs9ijkfm0pAldtJRfepqXjgnfujA',NULL,'2021-01-04 00:24:13',1,2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_attempt`
--

DROP TABLE IF EXISTS `user_attempt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_attempt` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `attempts` int DEFAULT NULL,
  `date_first_attempt` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_attempt`
--

LOCK TABLES `user_attempt` WRITE;
/*!40000 ALTER TABLE `user_attempt` DISABLE KEYS */;
INSERT INTO `user_attempt` VALUES (1,1,'2021-01-04 00:19:22','adminc'),(2,0,NULL,'adminc@email.com');
/*!40000 ALTER TABLE `user_attempt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_connexion`
--

DROP TABLE IF EXISTS `user_connexion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_connexion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_connexion` date DEFAULT NULL,
  `number_connexion` int DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjtp9qmvfxhnq3x6vlryy2ba9u` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_connexion`
--

LOCK TABLES `user_connexion` WRITE;
/*!40000 ALTER TABLE `user_connexion` DISABLE KEYS */;
INSERT INTO `user_connexion` VALUES (1,'2021-01-04',3,1),(2,'2021-01-04',1,2);
/*!40000 ALTER TABLE `user_connexion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_group` (
  `user_id` bigint NOT NULL,
  `group_id` bigint NOT NULL,
  KEY `FKb0xhtx49263tfllv47gq517as` (`group_id`),
  KEY `FK1c1dsw3q36679vaiqwvtv36a6` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group`
--

LOCK TABLES `user_group` WRITE;
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-04  9:27:17

INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (1,'أفغانستان','af','Afghanistan','Afghanistan');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (2,'ألبانيا','al','Albania','Albanie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (3,'الجزائر','dz','Algeria','Algérie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (4,'أندورا','ad','Andorra','Andorre');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (5,'أنغولا','ao','Angola','Angola');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (6,'أنتيغوا وباربودا','ag','Antigua and Barbuda','Antigua-et-Barbuda');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (7,'الأرجنتين','ar','Argentina','Argentine');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (8,'أرمينيا','am','Armenia','Arménie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (9,'أستراليا','au','Australia','Australie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (10,'النمسا','at','Austria','Autriche');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (11,'أذربيجان','az','Azerbaijan','Azerbaïdjan');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (12,'باهاماس','bs','Bahamas','Bahamas');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (13,'البحرين','bh','Bahrain','Bahreïn');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (14,'بنغلاديش','bd','Bangladesh','Bangladesh');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (15,'باربادوس','bb','Barbados','Barbade');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (16,'روسيا البيضاء','by','Belarus','Biélorussie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (17,'بلجيكا','be','Belgium','Belgique');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (18,'بليز','bz','Belize','Belize');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (19,'بنين','bj','Benin','Bénin');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (20,'بوتان','bt','Bhutan','Bhoutan');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (21,'بوليفيا','bo','Bolivia (Plurinational State of)','Bolivie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (22,'البوسنة والهرسك','ba','Bosnia and Herzegovina','Bosnie-Herzégovine');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (23,'بوتسوانا','bw','Botswana','Botswana');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (24,'البرازيل','br','Brazil','Brésil');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (25,'بروناي','bn','Brunei Darussalam','Brunei');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (26,'بلغاريا','bg','Bulgaria','Bulgarie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (27,'بوركينا فاسو','bf','Burkina Faso','Burkina Faso');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (28,'بوروندي','bi','Burundi','Burundi');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (29,'الرأس الأخضر','cv','Cabo Verde','Cap-Vert');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (30,'كمبوديا','kh','Cambodia','Cambodge');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (31,'الكاميرون','cm','Cameroon','Cameroun');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (32,'كندا','ca','Canada','Canada');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (33,'جمهورية أفريقيا الوسطى','cf','Central African Republic','République centrafricaine');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (34,'تشاد','td','Chad','Tchad');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (35,'تشيلي','cl','Chile','Chili');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (36,'الصين','cn','China','Chine');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (37,'كولومبيا','co','Colombia','Colombie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (38,'جزر القمر','km','Comoros','Comores (pays)');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (39,'جمهورية الكونغو','cg','Congo','République du Congo');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (40,'جمهورية الكونغو الديمقراطية','cd','Democratic Republic of the Congo','République démocratique du Congo');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (41,'كوستاريكا','cr','Costa Rica','Costa Rica');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (42,'ساحل العاج','ci','Côte d\'Ivoire','Côte d\'Ivoire');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (43,'كرواتيا','hr','Croatia','Croatie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (44,'كوبا','cu','Cuba','Cuba');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (45,'قبرص','cy','Cyprus','Chypre (pays)');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (46,'جمهورية التشيك','cz','Czechia','Tchéquie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (47,'الدنمارك','dk','Denmark','Danemark');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (48,'جيبوتي','dj','Djibouti','Djibouti');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (49,'دومينيكا','dm','Dominica','Dominique');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (50,'جمهورية الدومينيكان','do','Dominican Republic','République dominicaine');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (51,'الإكوادور','ec','Ecuador','Équateur (pays)');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (52,'مصر','eg','Egypt','Égypte');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (53,'السلفادور','sv','El Salvador','Salvador');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (54,'غينيا الاستوائية','gq','Equatorial Guinea','Guinée équatoriale');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (55,'إريتريا','er','Eritrea','Érythrée');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (56,'إستونيا','ee','Estonia','Estonie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (57,'سوازيلاند','sz','Eswatini','Swaziland');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (58,'إثيوبيا','et','Ethiopia','Éthiopie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (59,'فيجي','fj','Fidji','Espagne');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (60,'فنلندا','fi','Finland','Finlande');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (61,'فرنسا','fr','France','France');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (62,'الغابون','ga','Gabon','Gabon');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (63,'غامبيا','gm','Gambia','Gambie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (64,'جورجيا','ge','Georgia','Géorgie (pays)');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (65,'ألمانيا','de','Germany','Allemagne');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (66,'غانا','gh','Ghana','Ghana');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (67,'اليونان','gr','Greece','Grèce');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (68,'غرينادا','gd','Grenada','Grenade (pays)');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (69,'غواتيمالا','gt','Guatemala','Guatemala');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (70,'غينيا','gn','Guinea','Guinée');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (71,'غينيا بيساو','gw','Guinea-Bissau','Guinée-Bissau');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (72,'غيانا','gy','Guyana','Guyana');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (73,'هايتي','ht','Haiti','Haïti');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (74,'هندوراس','hn','Honduras','Honduras');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (75,'المجر','hu','Hungary','Hongrie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (76,'آيسلندا','is','Iceland','Islande');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (77,'الهند','in','India','Inde');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (78,'إندونيسيا','id','Indonesia','Indonésie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (79,'إيران','ir','Iran (Islamic Republic of Iran)','Iran');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (80,'العراق','iq','Iraq','Irak');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (81,'أيرلندا','ie','Ireland','Irlande (pays)');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (82,'إسرائيل','il','Israel','Israël');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (83,'إيطاليا','it','Italy','Italie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (84,'جامايكا','jm','Jamaica','Jamaïque');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (85,'اليابان','jp','Japan','Japon');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (86,'الأردن','jo','Jordan','Jordanie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (87,'كازاخستان','kz','Kazakhstan','Kazakhstan');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (88,'كينيا','ke','Kenya','Kenya');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (89,'كيريباتي','ki','Kiribati','Kiribati');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (90,'كوريا الشمالية','kp','Korea (Democratic People\'s Republic of)','Corée du Nord');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (91,'كوريا الجنوبية','kr','Republic of Korea','Corée du Sud');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (92,'الكويت','kw','Kuwait','Koweït');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (93,'قيرغيزستان','kg','Kyrgyzstan','Kirghizistan');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (94,'لاوس','la','Lao People\'s Democratic Republic','Laos');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (95,'لاتفيا','lv','Latvia','Lettonie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (96,'لبنان','lb','Lebanon','Liban');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (97,'ليسوتو','ls','Lesotho','Lesotho');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (98,'ليبيريا','lr','Liberia','Liberia');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (99,'ليبيا','ly','Libya','Libye');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (100,'ليختنشتاين','li','Liechtenstein','Liechtenstein');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (101,'ليتوانيا','lt','Lithuania','Lituanie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (102,'لوكسمبورغ','lu','Luxembourg','Luxembourg (pays)');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (103,'مدغشقر','mg','Madagascar','Madagascar');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (104,'مالاوي','mw','Malawi','Malawi');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (105,'ماليزيا','my','Malaysia','Malaisie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (106,'جزر المالديف','mv','Maldives','Maldives');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (107,'مالي','ml','Mali','Mali');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (108,'مالطا','mt','Malta','Malte');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (109,'جزر مارشال','mh','Marshall Islands','Îles Marshall (pays)');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (110,'موريتانيا','mr','Mauritania','Mauritanie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (111,'موريشيوس','mu','Mauritius','Maurice (pays)');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (112,'المكسيك','mx','Mexico','Mexique');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (113,'ولايات ميكرونيسيا المتحدة','fm','Micronesia (Federated States of)','États fédérés de Micronésie (pays)');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (114,'مولدوفا','md','Republic of Moldova','Moldavie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (115,'موناكو','mc','Monaco','Monaco');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (116,'منغوليا','mn','Mongolia','Mongolie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (117,'الجبل الأسود','me','Montenegro','Monténégro');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (118,'المغرب','ma','Morocco','Maroc');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (119,'موزمبيق','mz','Mozambique','Mozambique');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (120,'ميانمار','mm','Myanmar','Birmanie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (121,'ناميبيا','na','Namibia','Namibie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (122,'ناورو','nr','Nauru','Nauru');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (123,'نيبال','np','Nepal','Népal');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (124,'هولندا','nl','Netherlands','Pays-Bas');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (125,'نيوزيلندا','nz','New Zealand','Nouvelle-Zélande');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (126,'نيكاراغوا','ni','Nicaragua','Nicaragua');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (127,'النيجر','ne','Niger','Niger');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (128,'نيجيريا','ng','Nigeria','Nigeria');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (129,'مقدونيا','mk','North Macedonia','Macédoine du Nord');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (130,'النرويج','no','Norway','Norvège');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (131,'عمان','om','Oman','Oman');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (132,'باكستان','pk','Pakistan','Pakistan');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (133,'بالاو','pw','Palau','Palaos');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (134,'بنما','pa','Panama','Panama');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (135,'بابوا غينيا الجديدة','pg','Papua New Guinea','Papouasie-Nouvelle-Guinée');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (136,'باراغواي','py','Paraguay','Paraguay');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (137,'بيرو','pe','Peru','Pérou');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (138,'الفلبين','ph','Philippines','Philippines');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (139,'بولندا','pl','Poland','Pologne');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (140,'البرتغال','pt','Portugal','Portugal');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (141,'قطر','qa','Qatar','Qatar');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (142,'رومانيا','ro','Romania','Roumanie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (143,'روسيا','ru','Russian Federation','Russie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (144,'رواندا','rw','Rwanda','Rwanda');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (145,'سانت كيتس ونيفيس','kn','Saint Kitts and Nevis','Saint-Christophe-et-Niévès');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (146,'سانت لوسيا','lc','Saint Lucia','Sainte-Lucie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (147,'سانت فينسنت والغرينادين','vc','Saint Vincent and the Grenadines','Saint-Vincent-et-les-Grenadines');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (148,'ساموا','ws','Samoa','Samoa');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (149,'سان مارينو','sm','San Marino','Saint-Marin');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (150,'ساو تومي وبرينسيب','st','Sao Tome and Principe','Sao Tomé-et-Principe');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (151,'السعودية','sa','Saudi Arabia','Arabie saoudite');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (152,'السنغال','sn','Senegal','Sénégal');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (153,'صربيا','rs','Serbia','Serbie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (154,'سيشل','sc','Seychelles','Seychelles');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (155,'سيراليون','sl','Sierra Leone','Sierra Leone');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (156,'سنغافورة','sg','Singapore','Singapour');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (157,'سلوفاكيا','sk','Slovakia','Slovaquie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (158,'سلوفينيا','si','Slovenia','Slovénie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (159,'جزر سليمان','sb','Solomon Islands','Salomon');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (160,'الصومال','so','Somalia','Somalie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (161,'جنوب أفريقيا','za','South Africa','Afrique du Sud');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (162,'جنوب السودان','ss','South Sudan','Soudan du Sud');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (163,'إسبانيا','es','Spain','Espagne');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (164,'سريلانكا','lk','Sri Lanka','Sri Lanka');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (165,'السودان','sd','Sudan','Soudan');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (166,'سورينام','sr','Suriname','Suriname');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (167,'السويد','se','Sweden','Suède');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (168,'سويسرا','ch','Switzerland','Suisse');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (169,'سوريا','sy','Syrian Arab Republic','Syrie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (170,'طاجيكستان','tj','Tajikistan','Tadjikistan');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (171,'تنزانيا','tz','United Republic of Tanzania','Tanzanie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (172,'تايلاند','th','Thailand','Thaïlande');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (173,'تيمور الشرقية','tl','Timor-Leste','Timor oriental');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (174,'توغو','tg','Togo','Togo');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (175,'تونغا','to','Tonga','Tonga');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (176,'ترينيداد وتوباغو','tt','Trinidad and Tobago','Trinité-et-Tobago');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (177,'تونس','tn','Tunisia','Tunisie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (178,'تركيا','tr','Turkey','Turquie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (179,'تركمانستان','tm','Turkmenistan','Turkménistan');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (180,'توفالو','tv','Tuvalu','Tuvalu');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (181,'أوغندا','ug','Uganda','Ouganda');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (182,'أوكرانيا','ua','Ukraine','Ukraine');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (183,'الإمارات العربية المتحدة','ae','United Arab Emirates','Émirats arabes unis');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (185,'الولايات المتحدة','us','United States of America','États-Unis');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (186,'الأوروغواي','uy','Uruguay','Uruguay');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (187,'أوزبكستان','uz','Uzbekistan','Ouzbékistan');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (188,'فانواتو','vu','Vanuatu','Vanuatu');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (189,'فنزويلا','ve','Venezuela (Bolivarian Republic of)','Venezuela');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (190,'فيتنام','vn','Viet Nam','Viêt Nam');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (191,'اليمن','ye','Yemen','Yémen');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (192,'زامبيا','zm','Zambia','Zambie');
INSERT INTO `country` (`id`,`arab_lang`,`code`,`english_lang`,`french_lang`) VALUES (193,'زيمبابوي','zw','Zimbabwe','Zimbabwe');

/*
-- Query: SELECT * FROM language
-- Date: 2019-11-14 17:52
*/
INSERT INTO `language` (`id`,`created_at`,`updated_at`,`name`) VALUES (null,'2019-07-25 16:13:15','2019-07-25 16:13:15','ENGLISH');
INSERT INTO `language` (`id`,`created_at`,`updated_at`,`name`) VALUES (null,'2019-07-25 16:13:17','2019-07-25 16:13:17','ARABIC');
INSERT INTO `language` (`id`,`created_at`,`updated_at`,`name`) VALUES (null,'2019-07-25 16:13:16','2019-07-25 16:13:16','FRENCH');


