CREATE TABLE IF NOT EXISTS `user`
(

    `id`            int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `display_name`          varchar(50),
    `email`         varchar(50),
    `password` varchar(255),
    `created_by` int,
    `created_at` datetime,
    `updated_at` datetime on update current_timestamp,
    `status` tinyint,

    FOREIGN KEY (created_by) REFERENCES user(id)

) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;