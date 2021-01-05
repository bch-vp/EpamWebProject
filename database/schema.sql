DROP SCHEMA IF EXISTS `web_database`;
CREATE SCHEMA IF NOT EXISTS `web_database` DEFAULT CHARACTER SET utf8mb4;

USE `web_database`;

CREATE TABLE IF NOT EXISTS `web_database`.`users`
(
    `id`       BIGINT      NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(10) NOT NULL,
    `password` VARCHAR(10) NOT NULL,
    `name`    VARCHAR(10) NOT NULL,
    `surname` VARCHAR(10) NOT NULL,
    `phone`    VARCHAR(17) NOT NULL,
    `email` VARCHAR(35) NOT NULL,
    UNIQUE (`login`),
    UNIQUE (`phone`),
    UNIQUE (`email`),
    PRIMARY KEY (`id`)
)ENGINE = InnoDB;

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



