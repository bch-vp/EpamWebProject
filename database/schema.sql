DROP SCHEMA IF EXISTS `web_database`;
CREATE SCHEMA IF NOT EXISTS `web_database` DEFAULT CHARACTER SET utf8mb4;

USE `web_database`;

-- -----------------------------------------------------
-- Table `user_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE IF NOT EXISTS `user_roles`
(
    `id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(15) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE (`name`)
) ENGINE = InnoDB;
INSERT INTO `user_roles`(`name`) VALUES ('GUEST'),
                                        ('CLIENT'),
                                        ('ADMIN');

-- -----------------------------------------------------
-- Table `user_statuses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_statuses`;
CREATE TABLE IF NOT EXISTS `user_statuses`
(
    `id`   BIGINT     NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(15) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE (`name`)
) ENGINE = InnoDB;
INSERT INTO `user_statuses`(`name`) VALUES ('ACTIVATED'),
                                           ('NOT_ACTIVATED'),
                                           ('BANNED');

-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users`
(
    `id`               BIGINT      NOT NULL AUTO_INCREMENT,
    `login`            VARCHAR(15) NOT NULL,
    `password`         VARCHAR(32) NOT NULL,
    `first_name`       VARCHAR(15) NOT NULL,
    `last_name`        VARCHAR(15) NOT NULL,
    `telephone_number` VARCHAR(17) NOT NULL,
    `email`            VARCHAR(55) NOT NULL,
    `role_id`          BIGINT DEFAULT 1,
    `status_id`        BIGINT DEFAULT 1,
    `avatar`           MEDIUMBLOB,
    PRIMARY KEY (`id`),
    UNIQUE (`login`),
    UNIQUE (`telephone_number`),
    UNIQUE (`email`),
    FOREIGN KEY (`role_id`) REFERENCES `user_roles` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (`status_id`) REFERENCES `user_statuses` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `product_statuses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `product_statuses`;
CREATE TABLE IF NOT EXISTS `product_statuses`
(
    `id`   BIGINT     NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(15) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE (`name`)
) ENGINE = InnoDB;
INSERT INTO `product_statuses`(`name`) VALUES ('ACTIVE'),
                                              ('INACTIVE'),
                                              ('BLOCKED');

-- -----------------------------------------------------
-- Table `categories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories`
(
    `id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(15) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE (`name`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products`
(
    `id`          BIGINT         NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(15)    NOT NULL,
    `info`        VARCHAR(100)   NOT NULL,
    `price`       DECIMAL(10, 2) NOT NULL,
    `category_id` BIGINT DEFAULT 1,
    `status_id`   BIGINT DEFAULT 1,
    `image`       MEDIUMBLOB,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (`status_id`) REFERENCES `product_statuses` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    UNIQUE (`name`)
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order_statuses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order_statuses`;
CREATE TABLE IF NOT EXISTS `order_statuses`
(
    `id`               BIGINT      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(15) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE (`name`)
)ENGINE = InnoDB;
INSERT INTO `order_statuses`(`name`) VALUES ('CONFIRMED'),
                                            ('NOT_CONFIRMED');

-- -----------------------------------------------------
-- Table `orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders`
(
    `id`          BIGINT         NOT NULL AUTO_INCREMENT,
    `comment`     VARCHAR(100),
    `address`     VARCHAR(100),
    `created_at`  BIGINT         NOT NULL,
    `total_price` DECIMAL(12, 2) NULL,
    `client_id`   BIGINT         NOT NULL,
    `status_id`   BIGINT         NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`client_id`) REFERENCES `users` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (`status_id`) REFERENCES `order_statuses` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orders_products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orders_products`;
CREATE TABLE IF NOT EXISTS `orders_products`
(
    `order_id`   BIGINT NOT NULL,
    `product_id` BIGINT NOT NULL,
    PRIMARY KEY (`order_id`, `product_id`),
    FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE = InnoDB;