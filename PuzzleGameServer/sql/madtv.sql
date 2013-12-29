SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `madtv` DEFAULT CHARACTER SET latin1 ;
USE `madtv`;

-- -----------------------------------------------------
-- Table `tbl_item_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_item_category` ;

CREATE  TABLE IF NOT EXISTS `tbl_item_category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `parent_id` INT(11),
  `name` VARCHAR(255) NOT NULL ,
  UNIQUE KEY (`name`),
  PRIMARY KEY (`id`),
  FOREIGN KEY (`parent_id`) REFERENCES tbl_item_category(`id`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- insert data for tbl_item_category
-- -----------------------------------------------------
INSERT INTO `tbl_item_category` (id,parent_id,name) VALUES (1,null,'Present');
INSERT INTO `tbl_item_category` (id,parent_id,name) VALUES (2,null,'Equitement');

-- -----------------------------------------------------
-- Table `tbl_level`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_level` ;

CREATE  TABLE IF NOT EXISTS `tbl_level` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `from_xp` BIGINT NOT NULL DEFAULT 0 ,
  `to_xp` BIGINT NOT NULL DEFAULT 0 ,
  `number_of_friend_can_share` INT NOT NULL DEFAULT 0 ,
  `max_number_of_studio_can_buy` INT NOT NULL DEFAULT 0 ,
  `number_of_movie_can_share` INT NOT NULL DEFAULT 0 , 
  `number_of_item_can_share` INT NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_item` ;

CREATE  TABLE IF NOT EXISTS `tbl_item` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(64) NOT NULL ,
  `XP` BIGINT NOT NULL DEFAULT 0 ,
  `love_point` FLOAT NOT NULL DEFAULT 0 ,
  `required_level` INT(11) NOT NULL ,
  `item_category_id` INT(11) NOT NULL ,
  `image` VARCHAR(45) NULL DEFAULT NULL ,
  `money_gold` INT(11) NOT NULL DEFAULT 0 ,
  `money_coin` INT(11) NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_TBL_ITEM_CATEGORY` (`item_category_id` ASC) ,
  INDEX `FK_TBL_LEVEL` (`required_level` ASC) ,
  CONSTRAINT `FK_TBL_ITEM_CATEGORY`
    FOREIGN KEY (`item_category_id` )
    REFERENCES `tbl_item_category` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_TBL_LEVEL`
    FOREIGN KEY (`required_level` )
    REFERENCES `tbl_level` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_show_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_show_category` ;

CREATE  TABLE IF NOT EXISTS `tbl_show_category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `image` VARCHAR(45) NULL DEFAULT NULL ,
  `reduce_factor_money` FLOAT NOT NULL DEFAULT 0 ,
   UNIQUE KEY (`name`),
   PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_screen_play`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_screen_play` ;

CREATE  TABLE IF NOT EXISTS `tbl_screen_play` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `category_id` INT(11) NOT NULL ,
  `episodes_number` INT(11) NULL DEFAULT 1 ,
  `required_level` INT(11) NOT NULL DEFAULT 1 ,
  `production_time` FLOAT NOT NULL ,
  `XP` INT(11) NOT NULL DEFAULT 0 ,
  `love_point` FLOAT NOT NULL DEFAULT 0 ,
  `estimated_viewer` FLOAT NOT NULL DEFAULT 0 ,
  `selling_price_after_production` INT NOT NULL DEFAULT 0 ,
  `money_gold` INT(11) NOT NULL DEFAULT 0 ,
  `money_coin` INT(11) NOT NULL DEFAULT 0 ,
  `reduce_rate` FLOAT NOT NULL DEFAULT 0 ,
  `COLLECTION_ITEM` VARCHAR(255) NULL DEFAULT NULL ,
  `DROPPED_RATE` VARCHAR(45) NULL DEFAULT NULL ,
  `image` VARCHAR(45) NULL DEFAULT NULL ,
  `duration` FLOAT NOT NULL DEFAULT 0 ,
  `broadcast_number` int NOT NULL DEFAULT 0 ,
  `viewer_decrease` FLOAT NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_SCREEN_PLAY_TBL_LEVEL` (`required_level` ASC) ,
  INDEX `FK_SCREEN_PLAY_TBL_SHOW_CATEGORY` (`category_id` ASC) ,
  CONSTRAINT `FK_SCREEN_PLAY_TBL_LEVEL`
    FOREIGN KEY (`required_level` )
    REFERENCES `tbl_level` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SCREEN_PLAY_TBL_SHOW_CATEGORY`
    FOREIGN KEY (`category_id` )
    REFERENCES `tbl_show_category` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_screen_play_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_screen_play_item` ;

CREATE  TABLE IF NOT EXISTS `tbl_screen_play_item` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `item_id` INT(11) NOT NULL ,
  `screen_play_id` INT(11) NOT NULL ,
  `quantity` INT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_SCREEN_PLAY_ITEM_TBL_ITEM` (`item_id` ASC) ,
  INDEX `FK_SCREEN_PLAY_ITEM_TBL_SCREENPLAY` (`screen_play_id` ASC) ,
  CONSTRAINT `FK_SCREEN_PLAY_ITEM_TBL_ITEM`
    FOREIGN KEY (`item_id` )
    REFERENCES `tbl_item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SCREEN_PLAY_ITEM_TBL_SCREENPLAY`
    FOREIGN KEY (`screen_play_id` )
    REFERENCES `tbl_screen_play` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_player_option`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_player_option` ;

CREATE  TABLE IF NOT EXISTS `tbl_player_option` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) CHARACTER SET utf8 NOT NULL ,
  `XP` BIGINT NOT NULL DEFAULT 0 ,
  `love_point` FLOAT NOT NULL DEFAULT 0 ,
  `coins_money` VARCHAR(45) NULL DEFAULT 0 ,
  `bill_gold` VARCHAR(45) NULL DEFAULT 0 ,
  `level_id` INT(11) NOT NULL DEFAULT 1 ,
  `number_of_floor` INT NOT NULL ,
  `number_of_friends` INT(11) NOT NULL DEFAULT 0 ,
  `number_movie_shared` INT(11) NOT NULL DEFAULT 0 ,
  `number_item_shared` INT(11) NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_PLAYER_TBL_LEVEL` (`level_id` ASC) ,
  CONSTRAINT `FK_PLAYER_TBL_LEVEL`
    FOREIGN KEY (`level_id` )
    REFERENCES `tbl_level` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_collection`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_collection` ;

CREATE  TABLE IF NOT EXISTS `tbl_collection` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `item1_id` INT(11) NOT NULL ,
  `item2_id` INT(11) NOT NULL ,
  `item3_id` INT(11) NOT NULL ,
  `item4_id` INT(11) NOT NULL ,
  `collection_type` TINYINT(1) NOT NULL DEFAULT true,
  `earn_coin` INT(11) NOT NULL DEFAULT 0 ,
  `earn_gold` INT(11) NOT NULL DEFAULT 0 ,
  `xp` INT NOT NULL DEFAULT 0 ,
  `love_point` FLOAT NOT NULL DEFAULT 0 ,
  `collection_image` VARCHAR(45) NULL DEFAULT NULL ,
  `item1_image` VARCHAR(45) NULL DEFAULT NULL ,
  `item2_image` VARCHAR(45) NULL DEFAULT NULL ,
  `item3_image` VARCHAR(45) NULL DEFAULT NULL ,
  `item4_image` VARCHAR(45) NULL DEFAULT NULL ,
  UNIQUE KEY (`name`),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `tbl_news_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_news_category` ;

CREATE  TABLE IF NOT EXISTS `tbl_news_category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `required_level` INT(11) NOT NULL ,
  `image` VARCHAR(45) NULL DEFAULT NULL ,
  UNIQUE KEY (`name`),
  PRIMARY KEY (`id`),
  INDEX `FK_NEWS_TBL_LEVEL` (`required_level` ASC) ,
  CONSTRAINT `FK_NEWS_TBL_LEVEL`
  FOREIGN KEY (`required_level` )
  REFERENCES `tbl_level` (`id` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_news_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_news_item` ;

CREATE  TABLE IF NOT EXISTS `tbl_news_item` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `new_category_id` INT(11) NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `new_text` VARCHAR(1024) NOT NULL ,
  `xp` INT NOT NULL DEFAULT 0 ,
  `money_gold` INT(11) NOT NULL DEFAULT 0 ,
  `money_coin` INT(11) NOT NULL DEFAULT 0 ,
  `love_point` FLOAT NOT NULL DEFAULT 0 ,
  `advertisement_factor` FLOAT NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_NEWS_ITEM_TBL_NEW_CATEGORY` (`new_category_id` ASC) ,
  CONSTRAINT `FK_NEWS_ITEM_TBL_NEW_CATEGORY`
    FOREIGN KEY (`new_category_id` )
    REFERENCES `tbl_news_category` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_room_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_room_type` ;

CREATE  TABLE IF NOT EXISTS `tbl_room_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- insert data for 'tbl_room_type'
-- -----------------------------------------------------
insert into `tbl_room_type` values (1,'program_office');
insert into `tbl_room_type` values (2,'archive');
insert into `tbl_room_type` values (3,'studio');
insert into `tbl_room_type` values (4,'pretty');
insert into `tbl_room_type` values (5,'movie_agency');
insert into `tbl_room_type` values (6,'screen_play');
insert into `tbl_room_type` values (7,'advertising');
insert into `tbl_room_type` values (8,'shop');
insert into `tbl_room_type` values (9,'news');
insert into `tbl_room_type` values (10,'elevator');

-- -----------------------------------------------------
-- Table `tbl_room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_room` ;

CREATE  TABLE IF NOT EXISTS `tbl_room` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `floor_number` INT NOT NULL DEFAULT 0 ,
  `index_number` INT NOT NULL DEFAULT 0 ,
  `name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(1024) NOT NULL ,
  `required_level` INT(11) NOT NULL DEFAULT 1 ,
  `image` VARCHAR(45) NULL ,
  `money_gold` INT(11) NOT NULL DEFAULT 0 ,
  `money_coin` INT(11) NOT NULL DEFAULT 0 ,
  `room_type` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_ROOM_TBL_LEVEL` (`required_level` ASC) ,
  INDEX `FK_ROOM_TBL_ROOM_TYPE` (`room_type` ASC) ,
  CONSTRAINT `FK_ROOM_TBL_LEVEL`
    FOREIGN KEY (`required_level` )
    REFERENCES `tbl_level` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ROOM_TBL_ROOM_TYPE`
    FOREIGN KEY (`room_type` )
    REFERENCES `tbl_room_type` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_advertisement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_advertisement` ;

CREATE  TABLE IF NOT EXISTS `tbl_advertisement` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `image` VARCHAR(45) NULL ,
  `xp` INT(11) NOT NULL DEFAULT 0 ,
  `earning` FLOAT NOT NULL DEFAULT 0 ,
  `penalty` FLOAT NOT NULL DEFAULT 0 ,
  `broadcast_number` INT NOT NULL DEFAULT 0 ,
  `required_viewer` INT(11) NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `tbl_player_advertisement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_player_advertisement` ;

CREATE  TABLE IF NOT EXISTS `tbl_player_advertisement` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `player_id` INT(11) NOT NULL ,
  `advertisement_id` INT(11) NOT NULL ,
  `broadcast_number` INT(11) NOT NULL ,
  `start_time` DATETIME,
  `expired_time` DATETIME  ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_PLAYER_ADVERTISEMENT_TBL_ADVERTISEMENT` (`advertisement_id` ASC) ,
  INDEX `FK_PLAYER_ADVERTISEMENT_TBL_PLAYER` (`player_id` ASC) ,
  CONSTRAINT `FK_PLAYER_ADVERTISEMENT_TBL_ADVERTISEMENT`
    FOREIGN KEY (`advertisement_id` )
    REFERENCES `tbl_advertisement` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PLAYER_ADVERTISEMENT_TBL_PLAYER`
    FOREIGN KEY (`player_id` )
    REFERENCES `tbl_player_option` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `tbl_advertisement_show_factor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_advertisement_show_factor` ;

CREATE  TABLE IF NOT EXISTS `tbl_advertisement_show_factor` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `advertisement_id` INT(11) NOT NULL ,
  `show_category_id` INT(11) NOT NULL ,
  `factor_money` FLOAT NOT NULL DEFAULT 1 ,
  `factor_xp` FLOAT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_ADVERTISEMENT_SHOW_FACTOR_TBL_ADVERTISEMENT` (`advertisement_id` ASC) ,
  INDEX `FK_ADVERTISEMENT_SHOW_FACTOR_TBL_SHOW_CATEGORY` (`show_category_id` ASC) ,
  CONSTRAINT `FK_ADVERTISEMENT_SHOW_FACTOR_TBL_ADVERTISEMENT`
    FOREIGN KEY (`advertisement_id` )
    REFERENCES `tbl_advertisement` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ADVERTISEMENT_SHOW_FACTOR_TBL_SHOW_CATEGORY`
    FOREIGN KEY (`show_category_id` )
    REFERENCES `tbl_show_category` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_show`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_show` ;

CREATE  TABLE IF NOT EXISTS `tbl_show` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(255) NOT NULL ,
  `xp` INT(11) NOT NULL DEFAULT 0 ,
  `love_point` FLOAT NOT NULL DEFAULT 0 ,
  `show_category_id` INT(11) NOT NULL ,
  `required_level` INT(11) NOT NULL ,
  `image` VARCHAR(45) NULL ,
  `estimated_viewer` FLOAT NOT NULL DEFAULT 0 ,
  `number_of_episodes` INT NOT NULL DEFAULT 1 ,
  `viewer_decrease` FLOAT NOT NULL DEFAULT 0 ,
  `money_gold` INT(11) NOT NULL DEFAULT 0 ,
  `money_coin` INT(11) NOT NULL DEFAULT 0 ,
  `reduce_rate` FLOAT NOT NULL DEFAULT 0 ,
  `generated` TINYINT(1) NOT NULL DEFAULT true ,
  `duration` FLOAT NOT NULL DEFAULT 0 ,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`id`) ,
  INDEX `FK_SHOW_TBL_SHOW_CATEGORY` (`show_category_id` ASC) ,
  INDEX `FK_SHOW_TBL_LEVEL` (`required_level` ASC) ,
  CONSTRAINT `FK_SHOW_TBL_SHOW_CATEGORY`
    FOREIGN KEY (`show_category_id` )
    REFERENCES `tbl_show_category` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SHOW_TBL_LEVEL`
    FOREIGN KEY (`required_level` )
    REFERENCES `tbl_level` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `tbl_friend`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_friend` ;

CREATE  TABLE IF NOT EXISTS `tbl_friend` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `player_id` INT(11) NOT NULL ,
  `friend_account` VARCHAR(45) NOT NULL ,
  `friend_account_type` VARCHAR(45) NOT NULL ,
  `status` INT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_FRIEND_TBL_PLAYER` (`player_id` ASC) ,
  CONSTRAINT `FK_FRIEND_TBL_PLAYER`
    FOREIGN KEY (`player_id` )
    REFERENCES `tbl_player_option` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_player_account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_player_account` ;

CREATE  TABLE IF NOT EXISTS `tbl_player_account` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `player_id` INT(11) NOT NULL ,
  `account_id` VARCHAR(255) DEFAULT NULL ,
  `account_type` VARCHAR(45) DEFAULT NULL ,
  `account_name` VARCHAR(255) CHARACTER SET utf8 DEFAULT NULL ,
  `local_account_email` VARCHAR(255) CHARACTER SET utf8 NOT NULL ,
  
  PRIMARY KEY (`id`) ,
  INDEX `FK_PLAYER_ACCOUNT_TBL_PLAYER_OPTION` (`player_id` ASC) ,
  CONSTRAINT `FK_PLAYER_ACCOUNT_TBL_PLAYER_OPTION`
    FOREIGN KEY (`player_id` )
    REFERENCES `tbl_player_option` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `tbl_player_screen_play`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_player_screen_play` ;

CREATE  TABLE IF NOT EXISTS `tbl_player_screen_play` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `producted` TINYINT(1) NOT NULL DEFAULT false ,
  `player_id` INT(11) NOT NULL ,
  `screen_play_id` INT(11) NOT NULL ,
  `cost` INT(11) NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_PLAYER_SCREEN_PLAY_TBL_SCREEN_PLAY` (`screen_play_id` ASC) ,
  INDEX `FK_PLAYER_SCREEN_PLAY_TBL_PLAYER` (`player_id` ASC) ,
  CONSTRAINT `FK_PLAYER_SCREEN_PLAY_TBL_SCREEN_PLAY`
    FOREIGN KEY (`screen_play_id` )
    REFERENCES `tbl_screen_play` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PLAYER_SCREEN_PLAY_TBL_PLAYER`
    FOREIGN KEY (`player_id` )
    REFERENCES `tbl_player_option` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_player_show`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_player_show` ;

CREATE  TABLE IF NOT EXISTS `tbl_player_show` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `player_id` INT(11) NOT NULL ,
  `show_id` INT(11) NOT NULL ,
  `show_xp` INT(11) NOT NULL DEFAULT 0 ,
  `show_love_point` FLOAT NOT NULL DEFAULT 0 ,
  `cost` INT(11) NOT NULL DEFAULT 0 ,
  `broadcast_number` INT NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_PLAYER_SHOW_TBL_PLAYER` (`player_id` ASC) ,
  INDEX `FK_PLAYER_SHOW_TBL_SHOW` (`show_id` ASC) ,
  CONSTRAINT `FK_PLAYER_SHOW_TBL_PLAYER`
    FOREIGN KEY (`player_id` )
    REFERENCES `tbl_player_option` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PLAYER_SHOW_TBL_SHOW`
    FOREIGN KEY (`show_id` )
    REFERENCES `tbl_show` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_show_schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_show_schedule` ;

CREATE  TABLE IF NOT EXISTS `tbl_show_schedule` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `player_id` INT(11) NOT NULL ,
  `show_id` INT(11) NOT NULL ,
  `advertisment_id` INT(11) NOT NULL ,
  `share_by` INT(11) NULL ,
  `type` VARCHAR(1) NOT NULL ,
  `start_time` DATETIME NOT NULL,
  `end_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`) ,
  INDEX `FK_SHOW_SCHEDULE_TBL_ADVERTISEMENT` (`advertisment_id` ASC) ,
  INDEX `FK_SHOW_SCHEDULE_TBL_SHOW` (`show_id` ASC) ,
  INDEX `FK_SHOW_SCHEDULE_TBL_PLAYER` (`player_id` ASC) ,
  CONSTRAINT `FK_SHOW_SCHEDULE_TBL_ADVERTISEMENT`
    FOREIGN KEY (`advertisment_id` )
    REFERENCES `tbl_advertisement` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SHOW_SCHEDULE_TBL_SHOW`
    FOREIGN KEY (`show_id` )
    REFERENCES `tbl_show` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SHOW_SCHEDULE_TBL_PLAYER`
    FOREIGN KEY (`player_id` )
    REFERENCES `tbl_player_option` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `tbl_screenplay_schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_screenplay_schedule` ;

CREATE  TABLE IF NOT EXISTS `tbl_screenplay_schedule` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `player_id` INT(11) NOT NULL ,
  `screenplay_id` INT(11) NOT NULL ,
  `start_time` DATETIME NOT NULL,
  `end_time` DATETIME NOT NULL,
  `floor_number` INT(11) NULL ,
  `index_number` INT(11) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_SCREENPLAY_SCHEDULE_TBL_SCREENPLAY` (`screenplay_id` ASC) ,
  INDEX `FK_SCREENPLAY_SCHEDULE_TBL_PLAYER` (`player_id` ASC) ,
  CONSTRAINT `FK_SCREENPLAY_SCHEDULE_TBL_SCREENPLAY`
    FOREIGN KEY (`screenplay_id` )
    REFERENCES `tbl_screen_play` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SCREENPLAY_SCHEDULE_TBL_PLAYER`
    FOREIGN KEY (`player_id` )
    REFERENCES `tbl_player_option` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `tbl_player_items`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_player_items` ;

CREATE  TABLE IF NOT EXISTS `tbl_player_items` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `player_id` INT(11) NOT NULL ,
  `item_id` INT(11) NOT NULL ,
  `quantity` INT NULL DEFAULT 1 ,
  `cost` INT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_PLAYER_ITEM_TBL_ITEM` (`item_id` ASC) ,
  INDEX `FK_PLAYER_ITEM_TBL_PLAYER` (`player_id` ASC) ,
  CONSTRAINT `FK_PLAYER_ITEM_TBL_ITEM`
    FOREIGN KEY (`item_id` )
    REFERENCES `tbl_item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PLAYER_ITEM_TBL_PLAYER`
    FOREIGN KEY (`player_id` )
    REFERENCES `tbl_player_option` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_config`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_config` ;

CREATE  TABLE IF NOT EXISTS `tbl_config` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `cf_key` VARCHAR (45) NOT NULL DEFAULT 0,
  `cf_value` VARCHAR(45) NOT NULL ,
  `cf_group` VARCHAR(45) NOT NULL ,
  `cf_description` VARCHAR(100) NULL,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;
-- -----------------------------------------------------
-- insert data for 'tbl_config'
-- -----------------------------------------------------
insert into `tbl_config` values (1,'DEFAULT_VIEWER','1000000','GAME_PLAY','DEFAULT_VIEWER');
insert into `tbl_config` values (2,'DEFAULT_COINS','20000','GAME_PLAY','DEFAULT_COINS');
insert into `tbl_config` values (3,'DEFAULT_GOLD','5000','GAME_PLAY','DEFAULT_GOLD');
insert into `tbl_config` values (4,'SELL_WITH_PRICE_REDUCTION','0.25','GAME_PLAY','SELL_WITH_PRICE_REDUCTION');
insert into `tbl_config` values (5,'MAX_OF_MOVIES_IN_ARCHIVE','25','GAME_PLAY','MAX_OF_MOVIES_IN_ARCHIVE');
insert into `tbl_config` values (6,'MAX_OF_SCREENPLAY_IN_ARCHIVE','25','GAME_PLAY','MAX_OF_SCREENPLAY_IN_ARCHIVE');
insert into `tbl_config` values (7,'MAX_OF_ADVERTISEMENT_IN_ARCHIVE','25','GAME_PLAY','MAX_OF_ADVERTISEMENT_IN_ARCHIVE');
insert into `tbl_config` values (8,'ADVERTISEMENT_AVAILABLE_TIME','7','GAME_PLAY','ADVERTISEMENT_AVAILABLE_TIME');
insert into `tbl_config` values (9,'UI_CONFIG_FILE','ui_cfg.xml','GAME_PLAY','UI_CONFIG_FILE');
insert into `tbl_config` values (10,'TIME_TABLE_MAX_SLOT',5,'GAME_PLAY','TIME_TABLE_MAX_SLOT');
insert into `tbl_config` values (11,'LOVE_POINT_REDUCED_PER_DAY',5,'GAME_PLAY','LOVE_POINT_REDUCED_PER_DAY');
insert into `tbl_config` values (12,'PER_GOLD_TO_COIN',2000,'GAME_PLAY','PER_GOLD_TO_COIN');
insert into `tbl_config` values (13,'DELAY_TIME_SCHEDULER',1,'GAME_PLAY','DELAY_TIME_SCHEDULER');
-- -----------------------------------------------------
-- Table `tbl_player_collection`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_player_collection` ;

CREATE  TABLE IF NOT EXISTS `tbl_player_collection` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `player_id` INT(11) NOT NULL ,
  `collection_id` INT(11) NOT NULL ,
  `item1_id` INT(11) NOT NULL ,
  `item2_id` INT(11) NOT NULL ,
  `item3_id` INT(11) NOT NULL ,
  `item4_id` INT(11) NOT NULL ,
  `collection_type` INT(11) NOT NULL ,
  `status` TINYINT(1) NOT NULL DEFAULT true ,
  `collection_image` VARCHAR(45) NULL DEFAULT NULL ,
  `item1_image` VARCHAR(45) NULL DEFAULT NULL ,
  `item2_image` VARCHAR(45) NULL DEFAULT NULL ,
  `item3_image` VARCHAR(45) NULL DEFAULT NULL ,
  `item4_image` VARCHAR(45) NULL DEFAULT NULL ,
  `earn_coin` INT(11) NOT NULL DEFAULT 0 ,
  `earn_gold` INT(11) NOT NULL DEFAULT 0 ,
  `xp` INT(11) NOT NULL DEFAULT 0 ,
  `love_point` FLOAT NOT NULL DEFAULT 0 ,
  `collection_quantity` INT(11) NOT NULL DEFAULT 0 ,
  `item1_Quantity` INT(11) NOT NULL DEFAULT 0 ,
  `item2_Quantity` INT(11) NOT NULL DEFAULT 0 ,
  `item3_Quantity` INT(11) NOT NULL DEFAULT 0 ,
  `item4_Quantity` INT(11) NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `tbl_gcm`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_gcm` ;

CREATE  TABLE IF NOT EXISTS `tbl_gcm` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `udid` VARCHAR(255) NOT NULL ,
  `player_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_TBL_PLAYER` (`player_id` ASC) ,
  CONSTRAINT `FK_TBL_PLAYER`
    FOREIGN KEY (`player_id` )
    REFERENCES `tbl_player_option` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_user` ;

CREATE  TABLE IF NOT EXISTS `tbl_user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `user_name` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  UNIQUE KEY (`user_name`),
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_role` ;

CREATE  TABLE IF NOT EXISTS `tbl_role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `role_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_user_role` ;

CREATE  TABLE IF NOT EXISTS `tbl_user_role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `user_id` INT(11) NOT NULL ,
  `role_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_TBL_USER` (`user_id` ASC) ,
  INDEX `FK_TBL_ROLE` (`role_id` ASC) ,
  CONSTRAINT `FK_TBL_USER`
    FOREIGN KEY (`user_id` )
    REFERENCES `tbl_user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_TBL_ROLE`
    FOREIGN KEY (`role_id` )
    REFERENCES `tbl_role` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_dropable_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_dropable_item` ;

CREATE  TABLE IF NOT EXISTS `tbl_dropable_item` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `item_id` INT(11) NOT NULL ,
  `dropped_rate` FLOAT NOT NULL DEFAULT 0 ,
  `show_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_TBL_SHOW` (`show_id` ASC) ,
  CONSTRAINT `FK_TBL_SHOW`
    FOREIGN KEY (`show_id` )
    REFERENCES `tbl_show` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_session`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_session` ;

CREATE  TABLE IF NOT EXISTS `tbl_session` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `player_id` INT(11) NOT NULL ,
  `session_id` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_player_news`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_player_news` ;

CREATE  TABLE IF NOT EXISTS `tbl_player_news` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `player_id` INT(11) NOT NULL ,
  `news_id` INT(11) NOT NULL ,
  `broadcasted` TINYINT(1) NULL DEFAULT NULL ,
  `start_time` DATETIME ,
  `end_time` DATETIME ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_PLAYER_NEWS_TBL_NEWS_ITEM` (`news_id` ASC) ,
  INDEX `FK_PLAYER_NEWS_TBL_PLAYER` (`player_id` ASC) ,
  CONSTRAINT `FK_PLAYER_NEWS_TBL_NEWS_ITEM`
    FOREIGN KEY (`news_id` )
    REFERENCES `tbl_news_item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PLAYER_NEWS_TBL_PLAYER`
    FOREIGN KEY (`player_id` )
    REFERENCES `tbl_player_option` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_shared_show`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_shared_show` ;

CREATE  TABLE IF NOT EXISTS `tbl_shared_show` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `share_by` INT(11) NOT NULL ,
  `shared_player` INT(11) NOT NULL ,
  `required_level` INT(11) NOT NULL ,
   `xp` INT(11) NOT NULL DEFAULT 0 ,
  `love_point` FLOAT NOT NULL DEFAULT 0 ,
  `title` VARCHAR(255) NOT NULL ,
  `image` VARCHAR(45) NULL DEFAULT NULL ,
  `estimated_viewer` FLOAT NOT NULL DEFAULT 0 ,
  `show_id` INT(11) NOT NULL ,
  `status` TINYINT(1) NOT NULL DEFAULT false ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_SHARED_SHOW_TBL_LEVEL` (`required_level` ASC) ,
  INDEX `FK_SHARED_SHOW_PLAYER_SHARE_BY` (`share_by` ASC) ,
  INDEX `FK_SHARED_SHOW_TBL_SHOW` (`show_id` ASC) ,
  CONSTRAINT `FK_SHARED_SHOW_TBL_LEVEL`
    FOREIGN KEY (`required_level` )
    REFERENCES `tbl_level` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SHARED_SHOW_PLAYER_SHARE_BY`
    FOREIGN KEY (`share_by` )
    REFERENCES `tbl_player_option` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SHARED_SHOW_TBL_SHOW`
    FOREIGN KEY (`show_id` )
    REFERENCES `tbl_show` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_shared_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_shared_item` ;

CREATE  TABLE IF NOT EXISTS `tbl_shared_item` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `item_id` INT(11) NOT NULL ,
  `from_player_id` INT(11) NOT NULL ,
  `to_player_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_SHARED_ITEM_TBL_PLAYER` (`from_player_id` ASC) ,
  CONSTRAINT `FK_SHARED_ITEM_TBL_PLAYER`
    FOREIGN KEY (`from_player_id` )
    REFERENCES `tbl_player_option` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_player_room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_player_room` ;

CREATE  TABLE IF NOT EXISTS `tbl_player_room` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `player_id` INT(11) NOT NULL ,
  `room_id` INT(11) NOT NULL ,
  `floor_number` INT NOT NULL ,
  `index_number` INT NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(1024) NOT NULL ,
  `image` VARCHAR(45),
  `room_type` VARCHAR(45) NULL ,
  `required_level` INT(11) NOT NULL default 1 ,
  `actived` INT(11) NOT NULL default 1,
  PRIMARY KEY (`id`) ,
  INDEX `FK_PLAYER_ROOM_TBL_PLAYER` (`player_id` ASC) ,
  INDEX `FK_PLAYER_ROOM_TBL_LEVEL` (`required_level` ASC) ,
  CONSTRAINT `FK_PLAYER_ROOM_TBL_LEVEL`
    FOREIGN KEY (`required_level` )
    REFERENCES `tbl_level` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PLAYER_ROOM_TBL_PLAYER`
    FOREIGN KEY (`player_id` )
    REFERENCES `tbl_player_option` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `tbl_player_studio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_player_studio` ;

CREATE  TABLE IF NOT EXISTS `tbl_player_studio` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `player_id` INT(11) NOT NULL ,
  `studio_amount` INT NOT NULL default 1 ,
  `studio_amount_current_activity` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_PLAYER_STUDIO_TBL_PLAYER` (`player_id` ASC) ,
  CONSTRAINT `FK_PLAYER_STUDIO_TBL_PLAYER`
    FOREIGN KEY (`player_id` )
    REFERENCES `tbl_player_option` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `tbl_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_event` ;

CREATE  TABLE IF NOT EXISTS `tbl_event` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `collection_id` INT(11) NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(255) NULL,
  `image` VARCHAR(45) NULL DEFAULT NULL ,
  `start_time` DATETIME,
  `end_time` DATETIME  ,
  UNIQUE KEY (`name`),
  PRIMARY KEY (`id`) ,
  INDEX `FK_EVENT_TBL_COLLECTION` (`collection_id` ASC) ,
  CONSTRAINT `FK_EVENT_TBL_COLLECTION`
    FOREIGN KEY (`collection_id` )
    REFERENCES `tbl_collection` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `tbl_config_gold_to_coin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_config_gold_to_coin` ;

CREATE  TABLE IF NOT EXISTS `tbl_config_gold_to_coin` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `gold` INT(11) NOT NULL DEFAULT 0 ,
  `coin` INT(11) NOT NULL DEFAULT 0 ,
  UNIQUE KEY (`gold`),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- insert data for 'tbl_config_gold_to_coin'
-- -----------------------------------------------------
insert into `tbl_config_gold_to_coin` values (1, 5, 10000);
insert into `tbl_config_gold_to_coin` values (2, 10, 20000);
insert into `tbl_config_gold_to_coin` values (3, 20, 40000);
insert into `tbl_config_gold_to_coin` values (4, 40, 80000);
insert into `tbl_config_gold_to_coin` values (5, 80, 160000);
insert into `tbl_config_gold_to_coin` values (6, 100, 200000);

-- -----------------------------------------------------
-- insert data for 'tbl_room'
-- -----------------------------------------------------

insert into `tbl_room` values (1,1,0,'Elevator','elevator',1,null,0,0,10);
insert into `tbl_room` values (2,2,1,'Program_Office','room 1',1,null,0,0,1);
insert into `tbl_room` values (3,2,2,'Shop','room 2',1,null,0,0,8);
insert into `tbl_room` values (4,2,3,'Archive','room 3',1,null,0,0,2);
insert into `tbl_room` values (5,3,1,'Movie_Agency','room 4',1,null,0,0,5);
insert into `tbl_room` values (6,3,2,'Advertising','room 5',1,null,0,0,7);
insert into `tbl_room` values (7,3,3,'News','room 6',1,null,0,0,9);
insert into `tbl_room` values (8,4,1,'Screen_Play','room 7',1,null,0,0,6);
insert into `tbl_room` values (9,4,2,'Studio','room 8',1,null,0,0,3);
insert into `tbl_room` values (10,5,-1,'Pretty','pretty',1,null,0,0,4);

-- -----------------------------------------------------
-- Table `tbl_level_up_extra `
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_level_up_extra` ;

CREATE  TABLE IF NOT EXISTS `tbl_level_up_extra` (
  `level_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `gold_extra` INT(11) NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`level_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `tbl_number_studio `
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_number_studio` ;

CREATE  TABLE IF NOT EXISTS `tbl_number_studio` (
  `number_of_studio` INT(11) NOT NULL AUTO_INCREMENT ,
  `studio_cost_in_coin` INT(11) NOT NULL DEFAULT 0 ,
  `studio_cost_in_gold` INT(11) NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`number_of_studio`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- insert data for 'tbl_number_studio'
-- -----------------------------------------------------
insert into `tbl_number_studio` values (1,0,0);

-- -----------------------------------------------------
-- Table `tbl_number_studio `
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_love_point_extra` ;

CREATE  TABLE IF NOT EXISTS `tbl_love_point_extra` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `love_point` FLOAT NOT NULL DEFAULT 0 ,
  `increase_rate` FLOAT NOT NULL DEFAULT 0 ,
  UNIQUE KEY (`love_point`),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- insert data for 'tbl_love_point_extra'
-- -----------------------------------------------------
insert into `tbl_love_point_extra` values (1,50,1.1);
insert into `tbl_love_point_extra` values (2,80,1.2);
insert into `tbl_love_point_extra` values (3,100,1.3);

-- -----------------------------------------------------
-- Table `tbl_config_money_to_coin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_config_money_to_coin` ;

CREATE  TABLE IF NOT EXISTS `tbl_config_money_to_coin` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `money` INT(11) NOT NULL DEFAULT 0 ,
  `coin` INT(11) NOT NULL DEFAULT 0 ,
  UNIQUE KEY (`money`),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- insert data for 'tbl_config_money_to_coin'
-- -----------------------------------------------------
insert into `tbl_config_money_to_coin` values (1, 1, 10000);
insert into `tbl_config_money_to_coin` values (2, 5, 50000);
insert into `tbl_config_money_to_coin` values (3, 10, 110000);
insert into `tbl_config_money_to_coin` values (4, 20, 240000);
insert into `tbl_config_money_to_coin` values (5, 50, 550000);
insert into `tbl_config_money_to_coin` values (6, 100, 1200000);

-- -----------------------------------------------------
-- Table `tbl_config_money_to_gold`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_config_money_to_gold` ;

CREATE  TABLE IF NOT EXISTS `tbl_config_money_to_gold` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `money` INT(11) NOT NULL DEFAULT 0 ,
  `gold` INT(11) NOT NULL DEFAULT 0 ,
  UNIQUE KEY (`money`),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- insert data for 'tbl_config_money_to_gold'
-- -----------------------------------------------------
insert into `tbl_config_money_to_gold` values (1, 1, 4);
insert into `tbl_config_money_to_gold` values (2, 5, 20);
insert into `tbl_config_money_to_gold` values (3, 10, 42);
insert into `tbl_config_money_to_gold` values (4, 20, 85);
insert into `tbl_config_money_to_gold` values (5, 50, 240);
insert into `tbl_config_money_to_gold` values (6, 100, 500);

-- -----------------------------------------------------
-- insert data for 'tbl_level'
-- -----------------------------------------------------
insert into `tbl_level` values (1, 0, 1000,1,2,1,1);
insert into `tbl_level` values (2, 1001, 1500,2,3,2,2);
insert into `tbl_level` values (3, 1501, 2000,3,4,3,3);
insert into `tbl_level` values (4, 2001, 3000,4,5,4,4);
insert into `tbl_level` values (5, 3001, 4000,5,6,5,5);
insert into `tbl_level` values (6, 4001, 5000,6,7,6,6);
insert into `tbl_level` values (7, 5001, 6000,7,8,7,7);
insert into `tbl_level` values (8, 6001, 7000,8,9,8,8);
insert into `tbl_level` values (9, 7001, 8000,9,10,9,9);
insert into `tbl_level` values (10, 8001, 9000,10,11,10,10);

-- -----------------------------------------------------
-- insert data for 'tbl_user'
-- -----------------------------------------------------
insert into `tbl_user` values (1, 'Admin', '5f4dcc3b5aa765d61d8327deb882cf99');

-- -----------------------------------------------------
-- insert data for 'tbl_role'
-- -----------------------------------------------------
insert into `tbl_role` values (1,'Super Administrator');
insert into `tbl_role` values (2,'Administrator');

-- -----------------------------------------------------
-- insert data for 'tbl_user_role'
-- -----------------------------------------------------
insert into `tbl_user_role` values (1,1,1);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

