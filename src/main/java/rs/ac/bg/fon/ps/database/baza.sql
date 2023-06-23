/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 8.0.31 : Database - biletarnica
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`biletarnica` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `biletarnica`;

/*Table structure for table `karta` */

DROP TABLE IF EXISTS `karta`;

CREATE TABLE `karta` (
  `kartaId` int NOT NULL AUTO_INCREMENT,
  `cena` int NOT NULL,
  `rezervacijaId` int NOT NULL,
  `stavkaId` int NOT NULL,
  PRIMARY KEY (`kartaId`,`rezervacijaId`,`stavkaId`),
  KEY `r_fk` (`rezervacijaId`),
  KEY `st_fk` (`stavkaId`),
  CONSTRAINT `r_fk` FOREIGN KEY (`rezervacijaId`) REFERENCES `rezervacija` (`rezervacijaId`),
  CONSTRAINT `st_fk` FOREIGN KEY (`stavkaId`) REFERENCES `stavkarezervacije` (`stavkaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `karta` */

/*Table structure for table `klijent` */

DROP TABLE IF EXISTS `klijent`;

CREATE TABLE `klijent` (
  `klijentId` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(100) NOT NULL,
  `prezime` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  PRIMARY KEY (`klijentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `klijent` */

/*Table structure for table `plangledanja` */

DROP TABLE IF EXISTS `plangledanja`;

CREATE TABLE `plangledanja` (
  `klijentId` int NOT NULL,
  `predstavaId` int NOT NULL,
  `gledao` tinyint(1) NOT NULL,
  `ocena` int NOT NULL,
  PRIMARY KEY (`klijentId`,`predstavaId`),
  KEY `predstava_fk` (`predstavaId`),
  CONSTRAINT `klijent_fk` FOREIGN KEY (`klijentId`) REFERENCES `klijent` (`klijentId`),
  CONSTRAINT `predstava_fk` FOREIGN KEY (`predstavaId`) REFERENCES `predstava` (`predstavaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `plangledanja` */

/*Table structure for table `predstava` */

DROP TABLE IF EXISTS `predstava`;

CREATE TABLE `predstava` (
  `predstavaId` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) NOT NULL,
  `mesto` varchar(100) NOT NULL,
  `vreme` datetime NOT NULL,
  `kapacitet` int NOT NULL,
  PRIMARY KEY (`predstavaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `predstava` */

/*Table structure for table `rezervacija` */

DROP TABLE IF EXISTS `rezervacija`;

CREATE TABLE `rezervacija` (
  `rezervacijaId` int NOT NULL AUTO_INCREMENT,
  `brojPredstave` int NOT NULL,
  `klijentId` int NOT NULL,
  PRIMARY KEY (`rezervacijaId`),
  KEY `kl_fk` (`klijentId`),
  CONSTRAINT `kl_fk` FOREIGN KEY (`klijentId`) REFERENCES `klijent` (`klijentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `rezervacija` */

/*Table structure for table `stavkarezervacije` */

DROP TABLE IF EXISTS `stavkarezervacije`;

CREATE TABLE `stavkarezervacije` (
  `stavkaId` int NOT NULL AUTO_INCREMENT,
  `popust` int NOT NULL,
  `brojSedista` int NOT NULL,
  `gledato` tinyint(1) NOT NULL,
  `rezervacijaId` int NOT NULL,
  `predstavaId` int NOT NULL,
  PRIMARY KEY (`stavkaId`,`rezervacijaId`),
  KEY `rezervacija_fk` (`rezervacijaId`),
  KEY `pr_fk` (`predstavaId`),
  CONSTRAINT `pr_fk` FOREIGN KEY (`predstavaId`) REFERENCES `predstava` (`predstavaId`),
  CONSTRAINT `rezervacija_fk` FOREIGN KEY (`rezervacijaId`) REFERENCES `rezervacija` (`rezervacijaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `stavkarezervacije` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
