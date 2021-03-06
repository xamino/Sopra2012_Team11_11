/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50519
 Source Host           : localhost
 Source Database       : sopra2

 Target Server Type    : MySQL
 Target Server Version : 50519
 File Encoding         : utf-8

 Date: 07/08/2012 16:19:23 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `Accounts`
-- ----------------------------
DROP TABLE IF EXISTS `Accounts`;
CREATE TABLE `Accounts` (
  `benutzername` varchar(60) NOT NULL,
  `passworthash` text NOT NULL,
  `accounttyp` int(11) NOT NULL,
  `email` text NOT NULL,
  `name` text NOT NULL,
  `institut` int(11) DEFAULT NULL,
  `stellvertreter` text,
  PRIMARY KEY (`benutzername`),
  KEY `institut` (`institut`),
  CONSTRAINT `id` FOREIGN KEY (`institut`) REFERENCES `Institute` (`IID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `Angebote`
-- ----------------------------
DROP TABLE IF EXISTS `Angebote`;
CREATE TABLE `Angebote` (
  `AID` int(11) NOT NULL,
  `Ersteller` text NOT NULL,
  `Name` text NOT NULL,
  `Notiz` text,
  `Geprueft` tinyint(1) NOT NULL,
  `Plaetze` int(11) NOT NULL,
  `Stundenprowoche` double NOT NULL,
  `Beschreibung` text NOT NULL,
  `Beginn` date NOT NULL,
  `Ende` date NOT NULL,
  `Stundenlohn` double DEFAULT NULL,
  `Institut` int(11) DEFAULT NULL,
  `aenderungsdatum` date NOT NULL,
  `abgeschlossen` tinyint(4) NOT NULL,
  PRIMARY KEY (`AID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `Bewerbungen`
-- ----------------------------
DROP TABLE IF EXISTS `Bewerbungen`;
CREATE TABLE `Bewerbungen` (
  `benutzername` varchar(60) NOT NULL,
  `AID` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `sachbearbeiter` varchar(60) DEFAULT NULL,
  `ausgewaehlt` tinyint(1) NOT NULL,
  PRIMARY KEY (`AID`,`benutzername`),
  KEY `a` (`AID`),
  KEY `b` (`sachbearbeiter`),
  KEY `c` (`benutzername`),
  CONSTRAINT `angebot` FOREIGN KEY (`AID`) REFERENCES `Angebote` (`AID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `bewerber` FOREIGN KEY (`benutzername`) REFERENCES `Accounts` (`benutzername`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sachbearbeiter` FOREIGN KEY (`sachbearbeiter`) REFERENCES `Accounts` (`benutzername`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `Bewerbungsunterlagen`
-- ----------------------------
DROP TABLE IF EXISTS `Bewerbungsunterlagen`;
CREATE TABLE `Bewerbungsunterlagen` (
  `benutzername` varchar(60) NOT NULL,
  `AID` int(11) NOT NULL,
  `UID` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`benutzername`,`AID`,`UID`),
  KEY `AID` (`AID`),
  KEY `UID` (`UID`),
  CONSTRAINT `AID` FOREIGN KEY (`AID`) REFERENCES `Angebote` (`AID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `benutzername` FOREIGN KEY (`benutzername`) REFERENCES `Accounts` (`benutzername`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `UID` FOREIGN KEY (`UID`) REFERENCES `Unterlagen` (`UID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `Institute`
-- ----------------------------
DROP TABLE IF EXISTS `Institute`;
CREATE TABLE `Institute` (
  `IID` int(11) NOT NULL,
  `Name` text NOT NULL,
  PRIMARY KEY (`IID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `Standardangebot`
-- ----------------------------
DROP TABLE IF EXISTS `Standardangebot`;
CREATE TABLE `Standardangebot` (
  `StdProMonat` int(11) DEFAULT NULL,
  `StartDatum` date DEFAULT NULL,
  `EndDatum` date DEFAULT NULL,
  `Lohn` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `Standardunterlagen`
-- ----------------------------
DROP TABLE IF EXISTS `Standardunterlagen`;
CREATE TABLE `Standardunterlagen` (
  `AID` int(11) NOT NULL,
  `UID` int(11) NOT NULL,
  PRIMARY KEY (`AID`,`UID`),
  KEY `UID` (`UID`),
  CONSTRAINT `angebotsid` FOREIGN KEY (`AID`) REFERENCES `Angebote` (`AID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `unterlagenid` FOREIGN KEY (`UID`) REFERENCES `Unterlagen` (`UID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `Unterlagen`
-- ----------------------------
DROP TABLE IF EXISTS `Unterlagen`;
CREATE TABLE `Unterlagen` (
  `UID` int(11) NOT NULL,
  `Name` text NOT NULL,
  `Beschreibung` text,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
