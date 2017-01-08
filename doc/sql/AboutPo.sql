-- auto Generated on 2017-01-08 22:37:34 
-- DROP TABLE IF EXISTS `about_po`; 
CREATE TABLE `about_po`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `source_content` TEXT NOT NULL COMMENT 'sourceContent',
    `create_time` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'createTime',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updateTime',
    `user_id` INT (11) NOT NULL DEFAULT -1 COMMENT 'userId',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`about_po`';
