DROP SCHEMA IF EXISTS `web_database`;
CREATE SCHEMA IF NOT EXISTS `web_database` DEFAULT CHARACTER SET utf8mb4;

USE `web_database`;

DROP TABLE IF EXISTS `web_database`.`users`;
CREATE TABLE IF NOT EXISTS `web_database`.`users`
(
    `id`               BIGINT         NOT NULL AUTO_INCREMENT,
    `login`            VARCHAR(15)    NOT NULL,
    `password`         VARCHAR(50)    NOT NULL,
    `first_name`       VARCHAR(15)    NOT NULL,
    `last_name`        VARCHAR(15)    NOT NULL,
    `telephone_number` VARCHAR(17)    NOT NULL,
    `email`            VARCHAR(55)    NOT NULL,
    `balance`          DECIMAL(10, 2) NOT NULL,
    `role`             tinyint        NOT NULL,
    `is_activated`     boolean DEFAULT FALSE,
    `avatar`           MEDIUMBLOB,
    PRIMARY KEY (`id`),
    UNIQUE (`login`),
    UNIQUE (`telephone_number`),
    UNIQUE (`email`)
) ENGINE = InnoDB;


DROP TABLE IF EXISTS `web_database`.`categories`;
CREATE TABLE IF NOT EXISTS `web_database`.`categories`
(
    `id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(15) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE (`name`)
) ENGINE = InnoDB;


DROP TABLE IF EXISTS `web_database`.`products`;
CREATE TABLE IF NOT EXISTS `web_database`.`products`
(
    `id`          BIGINT         NOT NULL AUTO_INCREMENT,
    `category_id` BIGINT,
    `name`        VARCHAR(15)    NOT NULL,
    `info`        VARCHAR(50),
    `price`       DECIMAL(10, 2) NOT NULL,
    `image`       MEDIUMBLOB,
    PRIMARY KEY (`id`),
    FOREIGN KEY (category_id) REFERENCES categories (Id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    UNIQUE (`name`)
) ENGINE = InnoDB;