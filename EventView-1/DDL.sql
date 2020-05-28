CREATE TABLE `users`
(
    `user_id`          int          NOT NULL auto_increment,
    `first_name`       varchar(100) NOT NULL,
    `last_name`        varchar(100) NOT NULL,
    `phone`            char(10),
    `email`            varchar(100) DEFAULT NULL UNIQUE,
    `create_timestamp` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    `update_timestamp` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_id`),
    INDEX usr_id (`user_id`)
) ENGINE = InnoDB;
CREATE TABLE `eventtypes`
(
    `event_type_id`    int          NOT NULL auto_increment,
    `event_type`       varchar(100) NOT NULL,
    `create_timestamp` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `update_timestamp` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`event_type_id`, `event_type`),
    INDEX evnt_type (`event_type_id`)
) ENGINE = InnoDB;
CREATE TABLE `events`
(
    `event_id`         int  NOT NULL AUTO_INCREMENT,
    `user_id`          int  NOT NULL,
    `event_type_id`    int  NOT NULL,
    `event_date`       date NOT NULL,
    `create_timestamp` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `update_timestamp` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    constraint evnt_pk primary key (`event_id`),
    CONSTRAINT evnt_fk1 FOREIGN KEY (`user_id`) REFERENCES users (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    constraint evnt_fk2 foreign key (`event_type_id`) REFERENCES eventtypes (`event_type_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX evt_id (`event_id`),
    INDEX evt1_id (`event_type_id`)
) ENGINE = InnoDB;
