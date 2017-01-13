ALTER TABLE user_p_o ADD verified tinyint(1) NOT NULL DEFAULT 0 COMMENT 'verified';
ALTER TABLE user_p_o ADD create_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'createTime';
