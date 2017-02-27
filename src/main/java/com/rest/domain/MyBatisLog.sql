-- auto Generated on 2017-02-27 19:56:38 
-- DROP TABLE IF EXISTS `my_batis_log`; 
CREATE TABLE `my_batis_log`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `class_name` VARCHAR (100) NOT NULL DEFAULT '' COMMENT 'className',
    `user_ip` VARCHAR (20) NOT NULL DEFAULT '' COMMENT 'userIp',
    `messages` TEXT NOT NULL COMMENT 'messages',
    `create_time` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'createTime',
    `logger_level` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'loggerLevel',
    INDEX(create_time),
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`my_batis_log`';
