/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50521
Source Host           : localhost:3306
Source Database       : hiwi

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2012-03-09 14:31:04
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `accounts`
-- ----------------------------
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
  `benutzername` varchar(60) NOT NULL,
  `passworthash` text NOT NULL,
  `accounttyp` int(11) NOT NULL,
  `email` text NOT NULL,
  `name` text NOT NULL,
  `institut` int(11) DEFAULT NULL,
  `stellvertreter` text,
  PRIMARY KEY (`benutzername`),
  KEY `institut` (`institut`),
  CONSTRAINT `id` FOREIGN KEY (`institut`) REFERENCES `institute` (`IID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of accounts
-- ----------------------------
INSERT INTO `accounts` VALUES ('adminbk201', 'mask', '0', 'hei@blackreaper.com', 'Li Shengshung', '100', '');
INSERT INTO `accounts` VALUES ('agent47', 'hitman', '1', 'agent47@agency.com', 'Unknown Baldhead', '600', 'super.man');
INSERT INTO `accounts` VALUES ('bat.man', 'joker', '1', 'batman@gothamcity.com', 'Bruce Wayne', '300', '');
INSERT INTO `accounts` VALUES ('ezio.tore', 'assasin', '2', 'ezio@anymus.com', 'Ezio Auditore de la Firenze', '200', '');
INSERT INTO `accounts` VALUES ('hulk.bam', 'angry', '2', 'hulkisangry@youuu.com', 'Bruce Banner', '500', '');
INSERT INTO `accounts` VALUES ('max.payne', 'bullettime', '3', 'max.payne@world.de', 'Max Payne', '100', '');
INSERT INTO `accounts` VALUES ('sam.fisher', 'nightvision', '3', 'sam.fisher@nnsa.com', 'Sam Fisher', '100', '');
INSERT INTO `accounts` VALUES ('super.man', 'luthor', '1', 'superman@dailyplanet.com', 'Clark Kent', '600', '');

-- ----------------------------
-- Table structure for `angebote`
-- ----------------------------
DROP TABLE IF EXISTS `angebote`;
CREATE TABLE `angebote` (
  `AID` int(11) NOT NULL,
  `Ersteller` text NOT NULL COMMENT 'Muss Foreign Key von Tabelle ''accounts'' Spalte ''benutzername'' sein... gibt aber einen Fehler\r\n\r\nFrom: Oemer\r\nDate: 09.03.2012',
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
  PRIMARY KEY (`AID`),
  KEY `Institute` (`Institut`),
  CONSTRAINT `Institute` FOREIGN KEY (`Institut`) REFERENCES `institute` (`IID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of angebote
-- ----------------------------
INSERT INTO `angebote` VALUES ('901', 'agent47', 'The Cleaning', 'Bitte schnell online stellen.', '0', '3', '47', 'Spuren beseitigen, tatort reinigen, DNA Spurenbeseitigung.', '2012-03-19', '2012-05-26', '10', '500', '2012-03-10');
INSERT INTO `angebote` VALUES ('902', 'bat.man', 'Gamma 3 Strahlen', 'Nur Wissenschaftler mit Prof.-Titel', '1', '6', '78', 'Nach dem Unfall  im Labor von Dr. Bruce Banner bin ich immer noch auf der Suche nach einer Heilungsmethode. Sie brauchen viel Interesse, Motivation, Wissen und Neugier. (Im Falle eines Wutausbruchs, muss Ihnen klar sein, dass sie von Hulk plattgemacht werden.)', '2012-04-02', '2012-07-28', '20', '300', '2012-03-09');
INSERT INTO `angebote` VALUES ('903', 'bat.man', 'Find Catwoman', 'Achtung ein KrallenJob. Alfred braucht ein neues Hemd.', '1', '3', '23', 'Catwoman ist sexy aber durchgeknallt. Bei der Suche kann es toedlich sein. Auf eigenen Gefahr.', '2012-03-05', '2012-03-31', '13', '100', '2012-03-03');
INSERT INTO `angebote` VALUES ('904', 'super.man', 'Tokio Drift', 'Sushi essen am Freitag nicht vergessen.', '0', '17', '8', 'Racing und electric stuff. No loseres.', '2012-05-18', '2011-10-12', '9.34', '600', '2012-03-09');

-- ----------------------------
-- Table structure for `bewerbungen`
-- ----------------------------
DROP TABLE IF EXISTS `bewerbungen`;
CREATE TABLE `bewerbungen` (
  `benutzername` varchar(60) NOT NULL,
  `AID` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `sachbearbeiter` varchar(60) DEFAULT NULL,
  `ausgewaehlt` tinyint(1) NOT NULL,
  PRIMARY KEY (`benutzername`,`AID`),
  KEY `a` (`AID`),
  KEY `b` (`sachbearbeiter`),
  KEY `c` (`benutzername`),
  CONSTRAINT `sachbearbeiter` FOREIGN KEY (`sachbearbeiter`) REFERENCES `accounts` (`benutzername`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `angebot` FOREIGN KEY (`AID`) REFERENCES `angebote` (`AID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `bewerber` FOREIGN KEY (`benutzername`) REFERENCES `accounts` (`benutzername`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of bewerbungen
-- ----------------------------
INSERT INTO `bewerbungen` VALUES ('max.payne', '901', '0', 'hulk.bam', '0');
INSERT INTO `bewerbungen` VALUES ('max.payne', '902', '0', 'ezio.tore', '0');
INSERT INTO `bewerbungen` VALUES ('sam.fisher', '901', '0', 'ezio.tore', '0');
INSERT INTO `bewerbungen` VALUES ('sam.fisher', '904', '1', 'ezio.tore', '0');

-- ----------------------------
-- Table structure for `bewerbungsunterlagen`
-- ----------------------------
DROP TABLE IF EXISTS `bewerbungsunterlagen`;
CREATE TABLE `bewerbungsunterlagen` (
  `benutzername` varchar(60) NOT NULL,
  `AID` int(11) NOT NULL,
  `UID` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`benutzername`,`AID`,`UID`),
  KEY `AID` (`AID`),
  KEY `UID` (`UID`),
  CONSTRAINT `AID` FOREIGN KEY (`AID`) REFERENCES `angebote` (`AID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `benutzername` FOREIGN KEY (`benutzername`) REFERENCES `accounts` (`benutzername`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `UID` FOREIGN KEY (`UID`) REFERENCES `unterlagen` (`UID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of bewerbungsunterlagen
-- ----------------------------
INSERT INTO `bewerbungsunterlagen` VALUES ('max.payne', '901', '0', '1');
INSERT INTO `bewerbungsunterlagen` VALUES ('max.payne', '901', '111', '0');
INSERT INTO `bewerbungsunterlagen` VALUES ('max.payne', '901', '333', '1');
INSERT INTO `bewerbungsunterlagen` VALUES ('max.payne', '901', '777', '0');
INSERT INTO `bewerbungsunterlagen` VALUES ('max.payne', '902', '0', '0');
INSERT INTO `bewerbungsunterlagen` VALUES ('max.payne', '902', '111', '0');
INSERT INTO `bewerbungsunterlagen` VALUES ('max.payne', '902', '333', '0');
INSERT INTO `bewerbungsunterlagen` VALUES ('max.payne', '902', '666', '0');
INSERT INTO `bewerbungsunterlagen` VALUES ('max.payne', '902', '888', '1');
INSERT INTO `bewerbungsunterlagen` VALUES ('sam.fisher', '901', '0', '0');
INSERT INTO `bewerbungsunterlagen` VALUES ('sam.fisher', '901', '111', '0');
INSERT INTO `bewerbungsunterlagen` VALUES ('sam.fisher', '901', '333', '0');
INSERT INTO `bewerbungsunterlagen` VALUES ('sam.fisher', '904', '0', '1');
INSERT INTO `bewerbungsunterlagen` VALUES ('sam.fisher', '904', '111', '1');
INSERT INTO `bewerbungsunterlagen` VALUES ('sam.fisher', '904', '222', '0');
INSERT INTO `bewerbungsunterlagen` VALUES ('sam.fisher', '904', '444', '0');
INSERT INTO `bewerbungsunterlagen` VALUES ('sam.fisher', '904', '666', '1');

-- ----------------------------
-- Table structure for `institute`
-- ----------------------------
DROP TABLE IF EXISTS `institute`;
CREATE TABLE `institute` (
  `IID` int(11) NOT NULL,
  `Name` text NOT NULL,
  PRIMARY KEY (`IID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of institute
-- ----------------------------
INSERT INTO `institute` VALUES ('100', 'Informatik');
INSERT INTO `institute` VALUES ('200', 'Mathematik');
INSERT INTO `institute` VALUES ('300', 'Biologie');
INSERT INTO `institute` VALUES ('400', 'Medizin');
INSERT INTO `institute` VALUES ('500', 'Wirtschaft');
INSERT INTO `institute` VALUES ('600', 'Physik');

-- ----------------------------
-- Table structure for `standardunterlagen`
-- ----------------------------
DROP TABLE IF EXISTS `standardunterlagen`;
CREATE TABLE `standardunterlagen` (
  `AID` int(11) NOT NULL,
  `UID` int(11) NOT NULL,
  PRIMARY KEY (`AID`,`UID`),
  KEY `UID` (`UID`),
  CONSTRAINT `angebotsid` FOREIGN KEY (`AID`) REFERENCES `angebote` (`AID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `unterlagenid` FOREIGN KEY (`UID`) REFERENCES `unterlagen` (`UID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of standardunterlagen
-- ----------------------------
INSERT INTO `standardunterlagen` VALUES ('901', '0');
INSERT INTO `standardunterlagen` VALUES ('902', '0');
INSERT INTO `standardunterlagen` VALUES ('904', '0');
INSERT INTO `standardunterlagen` VALUES ('901', '111');
INSERT INTO `standardunterlagen` VALUES ('902', '111');
INSERT INTO `standardunterlagen` VALUES ('904', '111');
INSERT INTO `standardunterlagen` VALUES ('904', '222');
INSERT INTO `standardunterlagen` VALUES ('901', '333');
INSERT INTO `standardunterlagen` VALUES ('902', '333');
INSERT INTO `standardunterlagen` VALUES ('904', '444');
INSERT INTO `standardunterlagen` VALUES ('902', '666');
INSERT INTO `standardunterlagen` VALUES ('904', '666');
INSERT INTO `standardunterlagen` VALUES ('902', '888');

-- ----------------------------
-- Table structure for `unterlagen`
-- ----------------------------
DROP TABLE IF EXISTS `unterlagen`;
CREATE TABLE `unterlagen` (
  `UID` int(11) NOT NULL,
  `Name` text NOT NULL,
  `Beschreibung` text,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of unterlagen
-- ----------------------------
INSERT INTO `unterlagen` VALUES ('0', 'Normvertrag', 'Standard');
INSERT INTO `unterlagen` VALUES ('111', 'Personalangaben', 'Allgemeine Angaben');
INSERT INTO `unterlagen` VALUES ('222', 'Superheldenbewilligung', 'Bewilligugnszeitraum des Superheld-Daseins');
INSERT INTO `unterlagen` VALUES ('333', 'Aufenthaltserlaubnis', 'Auf der Welt. Ausserirdische brauchen ein Visum und eine Arbeitserlaubnis.');
INSERT INTO `unterlagen` VALUES ('444', 'Mitgliedschaft bei den Fantastic Four', 'Mitglieder der fantastic four');
INSERT INTO `unterlagen` VALUES ('555', 'Marvel-Vertrag', 'Justice League');
INSERT INTO `unterlagen` VALUES ('666', 'Boesewichtszertifikat', 'Auflistung der Leistungen');
INSERT INTO `unterlagen` VALUES ('777', 'Angaben zu den Schwachstellen', 'Materialien und Ressourcen Ihrer Schwaeche.');
INSERT INTO `unterlagen` VALUES ('888', 'IQ-Zertifikat', 'IQ-Testbelege');

DROP TABLE IF EXISTS `standardangebot`
CREATE TABLE `standardangebot` (
  `stdProMonat` text,
  `startDatum` text,
  `endDatum` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `standardangebot` VALUES ('40', 'todo', 'todo');
