CREATE TABLE `admin_log` (
`log_id`  int NOT NULL ,
`admin_id`  int NOT NULL ,
`submit_time`  datetime NULL ON UPDATE CURRENT_TIMESTAMP ,
`action_type`  int NULL ,
PRIMARY KEY (`log_id`)
)
;


