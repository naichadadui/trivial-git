ALTER TABLE `user`
MODIFY COLUMN `score`  int(10) NULL DEFAULT 0 AFTER `name`;

ALTER TABLE `user_game_history`
MODIFY COLUMN `score`  int(10) NULL DEFAULT 0 AFTER `user_id`;

ALTER TABLE `questions`
MODIFY COLUMN `type`  int(4) NULL DEFAULT 0 AFTER `question_id`,
MODIFY COLUMN `content`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' AFTER `type`,
MODIFY COLUMN `true_ans`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' AFTER `content`,
MODIFY COLUMN `false_ans1`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' AFTER `true_ans`,
MODIFY COLUMN `false_ans2`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' AFTER `false_ans1`,
MODIFY COLUMN `false_ans3`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' AFTER `false_ans2`;

