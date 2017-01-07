-- auto Generated on 2017-01-08 04:21:20 
-- DROP TABLE IF EXISTS `execution_time_log`; 
CREATE TABLE `execution_time_log`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `class_name` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'className',
    `method_name` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'methodName',
    `execution_time` BIGINT (15) NOT NULL DEFAULT -1 COMMENT 'executionTime',
    `args_value` TEXT NOT NULL COMMENT 'argsValue',
    `create_time` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'createTime',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`execution_time_log`';
