-- MySQL dump 10.13  Distrib 5.7.13, for Linux (x86_64)
--
-- Host: localhost    Database: world
-- ------------------------------------------------------
-- Server version       5.7.13

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
-- Table structure for table `a_p_o`
--
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS `a_p_o` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT 'userName',
  `brand_name` varchar(50) NOT NULL DEFAULT '' COMMENT 'brandName',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='a_p_o';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `archive_dd`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS `archive_dd` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `addtime` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'addtime',
  `title` varchar(50) NOT NULL DEFAULT '' COMMENT 'title',
  `order` varchar(50) NOT NULL DEFAULT '' COMMENT 'order',
  `gre` varchar(50) NOT NULL DEFAULT '' COMMENT 'gre',
  `less` varchar(50) NOT NULL DEFAULT '' COMMENT 'less',
  `nimade` int(12) NOT NULL DEFAULT '-1' COMMENT 'nimade',
  `order_id` int(12) NOT NULL DEFAULT '-1' COMMENT 'orderId',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT 'userName',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT 'password',
  `order_number` bigint(20) NOT NULL DEFAULT '-1' COMMENT 'orderNumber',
  `serial_number` int(12) NOT NULL DEFAULT '-1' COMMENT 'serialNumber',
  `price` decimal(14,4) NOT NULL DEFAULT '0.0000' COMMENT 'price',
  `pay_price` decimal(14,4) NOT NULL DEFAULT '0.0000' COMMENT 'payPrice',
  `user_pay_amount` decimal(14,4) NOT NULL DEFAULT '0.0000' COMMENT 'userPayAmount',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='`archive_dd`';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `archives_cc`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS `archives_cc` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `addtime` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'addtime',
  `title` varchar(50) NOT NULL DEFAULT '' COMMENT 'title',
  `order` varchar(50) NOT NULL DEFAULT '' COMMENT 'order',
  `gre` varchar(50) NOT NULL DEFAULT '' COMMENT 'gre',
  `less` varchar(50) NOT NULL DEFAULT '' COMMENT 'less',
  `nimade` int(12) NOT NULL DEFAULT '-1' COMMENT 'nimade',
  `order_id` int(12) NOT NULL DEFAULT '-1' COMMENT 'orderId',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT 'userName',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT 'password',
  `order_number` bigint(20) NOT NULL DEFAULT '-1' COMMENT 'orderNumber',
  `serial_number` int(12) NOT NULL DEFAULT '-1' COMMENT 'serialNumber',
  `price` decimal(14,4) NOT NULL DEFAULT '0.0000' COMMENT 'price',
  `pay_price` decimal(14,4) NOT NULL DEFAULT '0.0000' COMMENT 'payPrice',
  `user_pay_amount` decimal(14,4) NOT NULL DEFAULT '0.0000' COMMENT 'userPayAmount',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='archives_cc';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `b_p_o`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS `b_p_o` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `good` bigint(20) NOT NULL DEFAULT '-1' COMMENT 'good',
  `now` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'now',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='b_p_o';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `comment_p_o`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS `comment_p_o` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NOT NULL DEFAULT '-1' COMMENT 'article_id',
  `reply_id` int(11) NOT NULL DEFAULT '-1' COMMENT 'reply_id',
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT 'username',
  `content` text NOT NULL COMMENT 'content',
  `addtime` datetime(6) NOT NULL DEFAULT '1000-01-01 00:00:00.000000' COMMENT 'addtime',
  `updatetime` datetime(6) NOT NULL DEFAULT '1000-01-01 00:00:00.000000' COMMENT 'updatetime',
  `viewed` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'viewed',
  `comment_ip` varchar(50) NOT NULL DEFAULT '' COMMENT 'comment_ip',
  PRIMARY KEY (`id`),
  KEY `article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `content`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `source_content` text NOT NULL,
  `html_content` text NOT NULL,
  `updatetime` datetime(6) NOT NULL,
  `addtime` datetime(6) NOT NULL,
  `index_content` text NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT '-1' COMMENT 'userId',
  `status` int(11) NOT NULL DEFAULT '-1' COMMENT 'status',
  PRIMARY KEY (`id`),
  KEY `addtime` (`addtime`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contenttime`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS `contenttime` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` smallint(6) NOT NULL,
  `month` tinyint(3) NOT NULL,
  `day` tinyint(3) NOT NULL,
  `content_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `year` (`year`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS `liquibase_change_log` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `liquibase_change_loglock`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS `liquibase_change_loglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `my_user`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS `my_user` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `cookie` varchar(50) NOT NULL DEFAULT '' COMMENT 'cookie',
  `type` int(12) NOT NULL DEFAULT '-1' COMMENT 'type',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT 'userName',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT 'password',
  `age` int(12) NOT NULL DEFAULT '-1' COMMENT 'age',
  `remaining_amount` decimal(14,4) NOT NULL DEFAULT '0.0000' COMMENT 'remainingAmount',
  `add_time` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'addTime',
  `serial_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT 'serialId',
  `global_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT 'globalId',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='`my_user`';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_p_o`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS `user_p_o` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT 'username',
  `mobile` varchar(50) NOT NULL DEFAULT '' COMMENT 'mobile',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT 'email',
  `cryptpasswod` varchar(100) DEFAULT NULL,
  `passwordcookie` varchar(100) NOT NULL DEFAULT '' COMMENT 'passwordcookie',
  `auth` tinyint(3) NOT NULL DEFAULT '0' COMMENT 'userauth  1 is admin',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `execution_time_log`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `class_name` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'className',
    `method_name` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'methodName',
    `execution_time` BIGINT (15) NOT NULL DEFAULT -1 COMMENT 'executionTime',
    `args_value` TEXT NOT NULL COMMENT 'argsValue',
    `create_time` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'createTime',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`execution_time_log`';
