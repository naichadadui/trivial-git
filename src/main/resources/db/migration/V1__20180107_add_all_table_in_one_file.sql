-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
`user_id`  int NOT NULL AUTO_INCREMENT ,
`email`  varchar(25) NULL ,
`password`  varchar(25) NULL ,
`name`  varchar(25) NULL ,
`score`  int(10) NULL DEFAULT 0,
PRIMARY KEY (`user_id`)
);

-- ----------------------------
-- Table structure for game_history
-- ----------------------------
CREATE TABLE `game_history` (
`game_id`  int NOT NULL AUTO_INCREMENT ,
`start_time`  datetime NULL ,
`end_time`  datetime NULL ,
 `winner_id`  int(11) NULL ,
PRIMARY KEY (`game_id`)
);

-- ----------------------------
-- Table structure for user_game_history
-- ----------------------------
CREATE TABLE `user_game_history` (
`game_id`  int NOT NULL ,
`user_id`  int NOT NULL ,
`score`  int(10) NULL DEFAULT 0,
PRIMARY KEY (`game_id`, `user_id`)
);

-- ----------------------------
-- Table structure for questions
-- ----------------------------
CREATE TABLE `questions` (
`question_id`  int(11) NOT NULL AUTO_INCREMENT ,
`type`  int(4) NULL DEFAULT 0 ,
`content`  varchar(255) NULL DEFAULT '' ,
`true_ans`  varchar(255) NULL DEFAULT '' ,
`false_ans1`  varchar(255) NULL DEFAULT '' ,
`false_ans2`  varchar(255) NULL DEFAULT '' ,
`false_ans3`  varchar(255) NULL DEFAULT '' ,
PRIMARY KEY (`question_id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8
;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
CREATE TABLE `admin` (
`admin_id`  int NOT NULL ,
`email`  varchar(25) NULL ,
`password`  varchar(25) NULL ,
`name`  varchar(25) NULL ,
PRIMARY KEY (`admin_id`)
)
;

-- ----------------------------
-- Table structure for map
-- ----------------------------
CREATE TABLE `Map` (
`map_id`  int NOT NULL ,
`point_id`  int NOT NULL ,
`top`  int NULL ,
`left`  int NULL ,
`state`  tinyint NULL ,
PRIMARY KEY (`map_id`, `point_id`)
)
;


CREATE TABLE `admin_log` (
`log_id`  int NOT NULL ,
`admin_id`  int NOT NULL ,
`submit_time`  datetime NULL,
`action_type`  int NULL ,
PRIMARY KEY (`log_id`)
)
;


