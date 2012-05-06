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

 Date: 05/06/2012 14:43:33 PM
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
--  Records of `Accounts`
-- ----------------------------
BEGIN;
INSERT INTO `Accounts` VALUES ('admin', 'ISMvKXpXpadDiUoOSoAfww', '0', 'mmamam', 'amamam', '0', null), ('applicant', 'TgJ3G1XABBGA78n8puBKdw', '3', 'email', 'Name', '0', null), ('applicant2', 'gCKP4zQ8lhNHSr3F1UlBbQ', '3', 'applicant2', 'applicant2', '0', null), ('clerk', 'NHdpgfpHqmzz8pFdEbrgUQ', '2', 'kjkln', 'knklnln', '1', null), ('clerk2', 'MxyKM4k57nhTweZhgmcyQg', '2', 'clerk2', 'Name', '2', null), ('clerk3', 'I6rao/bIEptym rMb4czDA', '2', 'clerk3', 'clerk3', '2', null), ('provider', 'np89cL2MiVdifq2pbZZ3Bg', '1', '2Email', 'Name22', '1', null), ('provider2', '6/yBXuLMahYiUQW7ez4eUw', '1', 'provider2', 'provider2', '2', null);
COMMIT;

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
--  Records of `Angebote`
-- ----------------------------
BEGIN;
INSERT INTO `Angebote` VALUES ('1', 'provider', 'Tutorium FGdI', 'Ich benötige folgende Prüfungsbescheinigung: FGdI', '1', '10', '10', 'Tutorium im Fach FGdI', '2012-05-15', '2012-07-13', '8.4', '1', '2012-04-29', '0'), ('2', 'provider', 'Tutorium Logik', null, '0', '10', '10', 'Tutorium im Fach Logik', '2012-05-15', '2012-09-28', '8.36', '1', '2012-04-29', '0'), ('3', 'provider2', 'Tutorium Medienpsychologie', 'Benötige Superheldenbescheinigung', '1', '10', '10', 'Tutorium im Fach Medienpsychologie', '2012-05-15', '2012-08-12', '5.35', '2', '2012-04-29', '0'), ('4', 'provider2', 'Tutorium Psychokram', 'Benötige Superheldenbescheinigung', '0', '10', '10', 'Tutorium im Fach Psychokram', '2012-05-15', '2012-06-13', '10.3', '2', '2012-04-29', '0'), ('5', 'provider2', 'Korrektur Psychokram', null, '1', '10', '10', 'Korrektur der Übunbgsblätter aus \"Psychokram\"', '2012-05-15', '2012-07-06', '15.9', '2', '2012-04-29', '0');
COMMIT;

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
--  Records of `Bewerbungen`
-- ----------------------------
BEGIN;
INSERT INTO `Bewerbungen` VALUES ('applicant', '1', '0', null, '0'), ('applicant2', '1', '1', 'clerk', '1'), ('applicant', '3', '1', 'clerk', '1'), ('applicant2', '3', '0', null, '0'), ('applicant2', '5', '0', null, '1');
COMMIT;

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
--  Records of `Bewerbungsunterlagen`
-- ----------------------------
BEGIN;
INSERT INTO `Bewerbungsunterlagen` VALUES ('applicant', '3', '111', '0'), ('applicant', '3', '999', '0'), ('applicant2', '1', '111', '1'), ('applicant2', '1', '222', '0'), ('applicant2', '5', '111', '1');
COMMIT;

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
--  Records of `Institute`
-- ----------------------------
BEGIN;
INSERT INTO `Institute` VALUES ('0', 'default'), ('1', 'Informatik'), ('2', 'Psychologie');
COMMIT;

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
--  Records of `Standardunterlagen`
-- ----------------------------
BEGIN;
INSERT INTO `Standardunterlagen` VALUES ('1', '111'), ('2', '111'), ('3', '111'), ('4', '111'), ('5', '111'), ('1', '222'), ('3', '999'), ('4', '999');
COMMIT;

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

-- ----------------------------
--  Records of `Unterlagen`
-- ----------------------------
BEGIN;
INSERT INTO `Unterlagen` VALUES ('111', 'Normvertrag', 'Normvertrag für Bewerber'), ('222', 'Prüfungsbescheinigung', 'Bescheinigung über bestandene Prüfung'), ('888', 'Spezialbescheinigung', 'Spezielle Spezialbescheinigung für Spezialfälle'), ('999', 'Superheldenbescheinigung', 'Hiermit werden Superkräfte atestiert.');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
