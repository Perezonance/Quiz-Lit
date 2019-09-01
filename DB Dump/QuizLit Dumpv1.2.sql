CREATE DATABASE  IF NOT EXISTS `quizit` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `quizit`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: quizit
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(45) DEFAULT NULL,
  `quiz_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `corejava`
--

DROP TABLE IF EXISTS `corejava`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `corejava` (
  `corejava_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `corejava_question` varchar(45) DEFAULT NULL,
  `corejava_correct_answer` varchar(45) DEFAULT NULL,
  `corejava_wrong_answer1` varchar(45) NOT NULL,
  `corejava_wrong_answer2` varchar(45) NOT NULL,
  `corejava_wrong_answer3` varchar(45) NOT NULL,
  PRIMARY KEY (`corejava_id`),
  KEY `category_id_idx` (`category_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corejava`
--

LOCK TABLES `corejava` WRITE;
/*!40000 ALTER TABLE `corejava` DISABLE KEYS */;
/*!40000 ALTER TABLE `corejava` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `j2ee`
--

DROP TABLE IF EXISTS `j2ee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `j2ee` (
  `j2ee_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `j2ee_question` varchar(45) DEFAULT NULL,
  `j2ee_correct_answer` varchar(45) DEFAULT NULL,
  `j2ee_wrong_answer1` varchar(45) NOT NULL,
  `j2ee_wrong_answer2` varchar(45) NOT NULL,
  `j2ee_wrong_answer3` varchar(45) NOT NULL,
  PRIMARY KEY (`j2ee_id`),
  KEY `category_id_idx` (`category_id`),
  CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `j2ee`
--

LOCK TABLES `j2ee` WRITE;
/*!40000 ALTER TABLE `j2ee` DISABLE KEYS */;
/*!40000 ALTER TABLE `j2ee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz` (
  `quiz_id` int(11) NOT NULL AUTO_INCREMENT,
  `quiz_no_questions` int(11) DEFAULT NULL,
  `quiz_time` time DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `scoringmodel_id` int(11) DEFAULT NULL,
  `quiz_access_code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`quiz_id`),
  UNIQUE KEY `quiz_access_code_UNIQUE` (`quiz_access_code`),
  KEY `fk_quiz_category_idx` (`category_id`),
  KEY `fk_scoringmodel_id_idx` (`scoringmodel_id`),
  CONSTRAINT `fk_quiz_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  CONSTRAINT `fk_scoringmodel_id` FOREIGN KEY (`scoringmodel_id`) REFERENCES `scoringmodel` (`scoringmodel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz`
--

LOCK TABLES `quiz` WRITE;
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
/*!40000 ALTER TABLE `quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scoringmodel`
--

DROP TABLE IF EXISTS `scoringmodel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scoringmodel` (
  `scoringmodel_id` int(11) NOT NULL AUTO_INCREMENT,
  `scoringmodel_right` int(11) NOT NULL,
  `scoringmodel_wrong` int(11) NOT NULL,
  `scoringmodel_unattempted` int(11) NOT NULL,
  PRIMARY KEY (`scoringmodel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scoringmodel`
--

LOCK TABLES `scoringmodel` WRITE;
/*!40000 ALTER TABLE `scoringmodel` DISABLE KEYS */;
/*!40000 ALTER TABLE `scoringmodel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sql`
--

DROP TABLE IF EXISTS `sql`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sql` (
  `sql_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `sql_question` varchar(45) DEFAULT NULL,
  `sql_correct_answer` varchar(45) DEFAULT NULL,
  `sql_wrong_answer1` varchar(45) NOT NULL,
  `sql_wrong_answer2` varchar(45) NOT NULL,
  `sql_wrong_answer3` varchar(45) NOT NULL,
  PRIMARY KEY (`sql_id`),
  KEY `category_id_idx` (`category_id`),
  CONSTRAINT `fk_sql_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sql`
--

LOCK TABLES `sql` WRITE;
/*!40000 ALTER TABLE `sql` DISABLE KEYS */;
/*!40000 ALTER TABLE `sql` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentquiz`
--

DROP TABLE IF EXISTS `studentquiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studentquiz` (
  `studentquiz_id` int(11) NOT NULL AUTO_INCREMENT,
  `studentquiz_right` int(11) DEFAULT NULL,
  `studentquiz_wrong` int(11) DEFAULT NULL,
  `studentquiz_unattempted` int(11) DEFAULT NULL,
  `studentquiz_total_score` int(11) DEFAULT NULL,
  `quiz_id` int(11) NOT NULL,
  `quiz_student_id` int(11) NOT NULL,
  PRIMARY KEY (`studentquiz_id`),
  KEY `gk_quiz_id_idx` (`quiz_id`),
  KEY `fk_quiz_student_id_idx` (`quiz_student_id`),
  CONSTRAINT `fk_quiz_creater_id` FOREIGN KEY (`quiz_student_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `fk_quiz_id` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`quiz_id`),
  CONSTRAINT `fk_quiz_student_id` FOREIGN KEY (`quiz_student_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentquiz`
--

LOCK TABLES `studentquiz` WRITE;
/*!40000 ALTER TABLE `studentquiz` DISABLE KEYS */;
/*!40000 ALTER TABLE `studentquiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_last_name` varchar(45) NOT NULL,
  `user_first_name` varchar(45) NOT NULL,
  `user_email` varchar(45) NOT NULL,
  `user_role_type` varchar(45) NOT NULL,
  `user_password` varchar(45) NOT NULL,
  `user_password1` varchar(45) DEFAULT NULL,
  `user_password2` varchar(45) DEFAULT NULL,
  `user_login_attempt` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_email_UNIQUE` (`user_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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

-- Dump completed on 2019-09-01 14:56:19
