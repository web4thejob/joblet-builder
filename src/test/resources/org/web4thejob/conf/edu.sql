-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 31, 2013 at 02:40 PM
-- Server version: 5.5.24-log
-- PHP Version: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `edu`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendee`
--

CREATE TABLE IF NOT EXISTS `attendee` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Course` bigint(20) NOT NULL,
  `Student` bigint(20) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Students-in-Course` (`Course`,`Student`),
  KEY `Course` (`Course`),
  KEY `Student` (`Student`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE IF NOT EXISTS `course` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Code` varchar(10) NOT NULL,
  `Description` varchar(250) NOT NULL,
  `Professor` bigint(20) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Code` (`Code`),
  KEY `Description` (`Description`),
  KEY `Professor` (`Professor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `coursenote`
--

CREATE TABLE IF NOT EXISTS `coursenote` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Notes` text NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `professor`
--

CREATE TABLE IF NOT EXISTS `professor` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `LastName` varchar(200) NOT NULL,
  `FirstName` varchar(100) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `LastName` (`LastName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `LastName` varchar(200) NOT NULL,
  `FirstName` varchar(100) NOT NULL,
  `BirthDate` date NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `LastName` (`LastName`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Constraints for table `attendee`
--
ALTER TABLE `attendee`
  ADD CONSTRAINT `attendee_ibfk_2` FOREIGN KEY (`Student`) REFERENCES `student` (`Id`),
  ADD CONSTRAINT `attendee_ibfk_1` FOREIGN KEY (`Course`) REFERENCES `course` (`Id`);

--
-- Constraints for table `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `course_ibfk_1` FOREIGN KEY (`Professor`) REFERENCES `professor` (`Id`);

--
-- Constraints for table `coursenote`
--
ALTER TABLE `coursenote`
  ADD CONSTRAINT `coursenote_ibfk_1` FOREIGN KEY (`Id`) REFERENCES `course` (`Id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
