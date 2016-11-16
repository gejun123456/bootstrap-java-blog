
create database world;

use world;

CREATE TABLE `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `source_content` text NOT NULL,
  `html_content` text NOT NULL,
  `updatetime` datetime NOT NULL,
  `addtime` datetime NOT NULL,
  `index_content` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `addtime` (`addtime`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

CREATE TABLE `contenttime` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` smallint(6) NOT NULL,
  `month` tinyint(4) NOT NULL,
  `day` tinyint(4) NOT NULL,
  `content_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `year` (`year`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;


CREATE TABLE `user_p_o` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT 'username',
  `mobile` varchar(50) NOT NULL DEFAULT '' COMMENT 'mobile',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT 'email',
  `cryptpasswod` varchar(100) DEFAULT NULL,
  `passwordcookie` varchar(100) NOT NULL DEFAULT '' COMMENT 'passwordcookie',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='user_p_o';


