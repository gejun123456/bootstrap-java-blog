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

CREATE TABLE IF NOT EXISTS `contenttime` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` smallint(6) NOT NULL,
  `month` tinyint(3) NOT NULL,
  `day` tinyint(3) NOT NULL,
  `content_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `year` (`year`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `user_p_o` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) UNIQUE NOT NULL  COMMENT 'username',
  `mobile` varchar(50) UNIQUE COMMENT 'mobile',
  `email` varchar(50) UNIQUE NOT NULL COMMENT 'email',
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


CREATE TABLE IF NOT EXISTS `about_po`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `source_content` TEXT NOT NULL COMMENT 'sourceContent',
    `create_time` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'createTime',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updateTime',
    `user_id` INT (11) NOT NULL DEFAULT -1 COMMENT 'userId',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`about_po`';



CREATE TABLE IF NOT EXISTS `tag_po`(
  `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tag_name` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'tagName',
  `create_time` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'createTime',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updateTime',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`tag_po`';


CREATE TABLE IF NOT EXISTS `content_tag_relation`(
  `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content_id` INT (11) NOT NULL DEFAULT -1 COMMENT 'contentId',
  `tag_id` INT (11) NOT NULL DEFAULT -1 COMMENT 'tagId',
  `create_time` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'createTime',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updateTime',
  PRIMARY KEY (`id`),
  key(`content_id`),
  key(`tag_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`content_tag_relation`';
