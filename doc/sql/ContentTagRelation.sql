-- auto Generated on 2017-01-25 14:47:06 
-- DROP TABLE IF EXISTS `content_tag_relation`; 
CREATE TABLE `content_tag_relation`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `content_id` INT (11) NOT NULL DEFAULT -1 COMMENT 'contentId',
    `tag_id` INT (11) NOT NULL DEFAULT -1 COMMENT 'tagId',
    `create_time` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'createTime',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updateTime',
    PRIMARY KEY (`id`),
    key(`content_id`),
    key(`tag_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`content_tag_relation`';
