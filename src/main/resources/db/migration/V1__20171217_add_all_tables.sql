-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
`user_id`  int NOT NULL AUTO_INCREMENT ,
`email`  varchar(25) NULL ,
`password`  varchar(25) NULL ,
`name`  varchar(25) NULL ,
`score`  int(10) NULL ,
PRIMARY KEY (`user_id`)
);

-- ----------------------------
-- Table structure for game_history
-- ----------------------------
CREATE TABLE `game_history` (
`game_id`  int NOT NULL AUTO_INCREMENT ,
`start_time`  datetime NULL ON UPDATE CURRENT_TIMESTAMP ,
`end_time`  datetime NULL ON UPDATE CURRENT_TIMESTAMP ,
PRIMARY KEY (`game_id`)
);

-- ----------------------------
-- Table structure for user_game_history
-- ----------------------------
CREATE TABLE `user_game_history` (
`game_id`  int NOT NULL ,
`user_id`  int NOT NULL ,
`score`  int(10) NULL ,
PRIMARY KEY (`game_id`, `user_id`)
);

-- ----------------------------
-- Table structure for questions
-- ----------------------------
CREATE TABLE `questions` (
`question_id`  int(11) NOT NULL AUTO_INCREMENT ,
`type`  int(4) NULL DEFAULT NULL ,
`content`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`true_ans`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`false_ans1`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`false_ans2`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`false_ans3`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`question_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1
ROW_FORMAT=DYNAMIC
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



