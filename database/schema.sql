DROP SCHEMA IF EXISTS `web_database`;
CREATE SCHEMA IF NOT EXISTS `web_database` DEFAULT CHARACTER SET utf8mb4;

USE `web_database`;

DROP TABLE IF EXISTS `web_database`.`users`;
CREATE TABLE IF NOT EXISTS `web_database`.`users`
(
    `id`               BIGINT      NOT NULL AUTO_INCREMENT,
    `login`            VARCHAR(15) NOT NULL,
    `password`         VARCHAR(50) NOT NULL,
    `first_name`       VARCHAR(15) NOT NULL,
    `last_name`        VARCHAR(15) NOT NULL,
    `telephone_number` VARCHAR(17) NOT NULL,
    `email`            VARCHAR(55) NOT NULL,
    `role`             tinyint     NOT NULL,
    `is_activated`     boolean DEFAULT FALSE,
    `avatar`           MEDIUMBLOB,
    UNIQUE (`login`),
    UNIQUE (`telephone_number`),
    UNIQUE (`email`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

# DROP TABLE IF EXISTS `web_database`.`details`;
# CREATE TABLE IF NOT EXISTS `web_database`.`details`
# (
#     `id`       BIGINT      NOT NULL AUTO_INCREMENT,
#     `name`     VARCHAR(30) NOT NULL,
#     `quantity` INT         NOT NULL,
#     PRIMARY KEY (`id`)
# ) ENGINE = InnoDB;
#
# DROP TABLE IF EXISTS `web_database`.`projects`;
# CREATE TABLE IF NOT EXISTS `web_database`.`projects`
# (
#     `id`   BIGINT      NOT NULL AUTO_INCREMENT,
#     `name` VARCHAR(30) NOT NULL,
#     PRIMARY KEY (`id`)
# ) ENGINE = InnoDB;
#
# DROP TABLE IF EXISTS `web_database`.`details_projects`;
# CREATE TABLE IF NOT EXISTS `web_database`.`details_projects`
# (
#     `id_detail`        BIGINT NOT NULL,
#     `quantity_of_used` INT    NOT NULL,
#     PRIMARY KEY (`id_detail`),
#     FOREIGN KEY (`id_detail`)
#         REFERENCES details (`id`) ON DELETE CASCADE
# #         ON DELETE RESTRICT ,
#
# ) ENGINE = InnoDB;
#
# INSERT INTO details(name, quantity)
# VALUES ('detail_1', 30),
#        ('detail_2', 30);



