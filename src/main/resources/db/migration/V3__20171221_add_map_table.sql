CREATE TABLE `Map` (
`map_id`  int NOT NULL ,
`point_id`  int NOT NULL ,
`top`  int NULL ,
`left`  int NULL ,
`state`  tinyint NULL ,
PRIMARY KEY (`map_id`, `point_id`)
)
;

