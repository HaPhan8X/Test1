-- -----------------------------------------------------
-- add field status in `tbl_gcm`
-- -----------------------------------------------------
ALTER TABLE tbl_gcm ADD status TINYINT(1) NOT NULL DEFAULT 0;

-- -----------------------------------------------------
-- add field sold in `tbl_player_show`
-- -----------------------------------------------------
ALTER TABLE tbl_player_show ADD sold TINYINT(1) NOT NULL DEFAULT 0;

-- -----------------------------------------------------
-- add field sold in `tbl_player_show`
-- -----------------------------------------------------
ALTER TABLE tbl_show ADD created_by varchar(255) CHARACTER SET utf8 DEFAULT NULL;

-- -----------------------------------------------------
-- Table `tbl_gcm_offline`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_gcm_offline` ;

CREATE  TABLE IF NOT EXISTS `tbl_gcm_offline` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `gcm_action` INT(11)  NULL DEFAULT NULL,
  `player_id` INT(11)  NULL DEFAULT NULL,
  `from_player_id` INT(11)  NULL DEFAULT NULL,
  `friend_account` VARCHAR(45)  NULL DEFAULT NULL,
  `friend_name` VARCHAR(255) CHARACTER SET utf8 DEFAULT NULL ,
  `friend_type` VARCHAR(45) DEFAULT NULL ,
  `level` INT(11)  NULL DEFAULT NULL,
  `time_request` VARCHAR(255) CHARACTER SET utf8 DEFAULT NULL ,
  `viewer_Number` BIGINT  NULL DEFAULT NULL ,
  `gold_extra` INT(11)  NULL DEFAULT NULL,
  `movie_id` INT(11)  NULL DEFAULT NULL,
  `movie_name` VARCHAR(255)  NULL DEFAULT NULL,
  `movie_image`  VARCHAR(45) NULL DEFAULT NULL ,
  `drop_item_id` INT(11)  NULL DEFAULT NULL,
  `drop_item_name` VARCHAR(255)  NULL DEFAULT NULL,
  `drop_item_image` VARCHAR(45) NULL DEFAULT NULL ,
  `drop_item_coin` INT(11)  NULL DEFAULT NULL,
  `drop_item_gold` INT(11)  NULL DEFAULT NULL,
  `xp_earning` BIGINT  NULL DEFAULT NULL ,
  `coin_earning` BIGINT  NULL DEFAULT NULL ,
  `love_point_earning` Float  NULL DEFAULT NULL,
  `floor_number` INT(11)  NULL DEFAULT NULL,
  `index_number` INT(11)  NULL DEFAULT NULL,
  `active` INT(11) NULL default NULL,
  `item_type` TINYINT(1)  NULL DEFAULT NULL,
  `event_id` INT(11)  NULL DEFAULT NULL,
  `event_image` VARCHAR(45) NULL DEFAULT NULL ,
  `message` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;