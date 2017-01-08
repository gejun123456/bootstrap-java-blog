ALTER TABLE content ADD user_id int(11) NOT NULL DEFAULT '' COMMENT 'userId';
ALTER TABLE content ADD status int(11) NOT NULL DEFAULT -1 COMMENT 'status';
