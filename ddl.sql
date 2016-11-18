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
  `auth` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'userauth  1 is admin',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='user_p_o';


CREATE TABLE `comment_p_o` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `article_id` int(12) NOT NULL DEFAULT '-1' COMMENT 'article_id',
  `reply_id` int(12) NOT NULL DEFAULT '-1' COMMENT 'reply_id',
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT 'username',
  `content` text NOT NULL COMMENT 'content',
  `addtime` datetime NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'addtime',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updatetime',
  PRIMARY KEY (`id`),
  KEY `article_id` (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='comment_p_o';


