-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
`user_id`  int NOT NULL AUTO_INCREMENT ,
`nickname`  varchar(255) NULL DEFAULT '' ,
`email`  varchar(255) NOT NULL ,
`password`  varchar(255) NOT NULL ,
PRIMARY KEY (`user_id`)
);
