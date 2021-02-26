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
INSERT INTO `user_roles`(`name`)
VALUES ('GUEST'),
       ('CLIENT'),
       ('ADMIN');

-- -----------------------------------------------------
-- Table `user_statuses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_statuses`;
CREATE TABLE IF NOT EXISTS `user_statuses`
(
    `id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(15) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE (`name`)
) ENGINE = InnoDB;
INSERT INTO `user_statuses`(`name`)
VALUES ('ACTIVATED'),
       ('NOT_ACTIVATED'),
       ('BANNED');

-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
# DROP TABLE IF EXISTS `users`;
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
    `avatar_url`       VARCHAR(100),
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
    `id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(15) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE (`name`)
) ENGINE = InnoDB;
INSERT INTO `product_statuses`(`name`)
VALUES ('ACTIVE'),
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
    `image_url`   VARCHAR(100),
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
    `id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(15) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE (`name`)
) ENGINE = InnoDB;
INSERT INTO `order_statuses`(`name`)
VALUES ('CONFIRMED'),
       ('NOT_CONFIRMED');

-- -----------------------------------------------------
-- Table `orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders`
(
    `id`          BIGINT         NOT NULL AUTO_INCREMENT,
    `comment`     VARCHAR(100),
    `address`     VARCHAR(50),
    `created_at`  long         NOT NULL,
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


# -------------------------------------
INSERT INTO categories(name)
VALUES ('others'),
       ('category_1'),
       ('category_2'),
       ('category_3');

INSERT INTO users(login, password, first_name, last_name, telephone_number, email,role_id, status_id )
VALUES('ilya', '46315d1d58cae3d8df137cd2ad9c4a70', 'gaeg', 'gaeg', 'aegag', 'gaega', 3, 1) ;

INSERT INTO users(login, password, first_name, last_name, telephone_number, email)
VALUES ('fawf', 'aegag', 'gaeg', 'eagga', 'gaeg', 'gaeg'),
       ('ffawawf', 'aegag', 'gaeg', 'eagga', 'gafeg', 'gaefg');

# 46315d1d58cae3d8df137cd2ad9c4a70
INSERT INTO products(name, info, price, category_id, status_id)
VALUES ('product_1', 'Some description of product', 243, 1, 1),
       ('product_2', 'Some description of product', 2243, 1, 1),
       ('product_3', 'Some description of product', 343, 1, 1),
       ('product_4', 'Some description of product', 243, 1, 1),
       ('product_5', 'Some adescription of product', 243, 1, 1),
       ('product_6', 'Some description of product', 3, 1, 1),
       ('product_7', 'Some description of product', 223, 1, 1),
       ('product_8', 'Some description of product', 23, 1, 1),
       ('product_9', 'Some description of product', 253, 1, 1),
       ('product_10', 'Some description of product', 23, 1, 1),
       ('product_11', 'Some description of product', 2243, 1, 1),
       ('product_12', 'Some description of product', 23, 1, 1),
       ('product_13', 'Some description of product', 23, 1, 1),
       ('product_14', 'Some description of product', 223, 1, 1),
       ('product_15', 'Some description of product', 23, 1, 1),
       ('product_16', 'Some description of product', 23, 1, 1),
       ('product_17', 'Some description of product', 223, 2, 1),
       ('product_18', 'Some description of product', 223, 2, 1),
       ('product_19', 'Some description of product', 28, 2, 1),
       ('product_20', 'Some description of product', 13, 2, 1),
       ('product_21', 'Some description of product', 873, 2, 1),
       ('product_22', 'Some description of product', 64, 2, 1),
       ('product_23', 'Some description of product', 463, 3, 1),
       ('product_24', 'Some description of product', 463, 3, 1),
       ('product_25', 'Some description of product', 643, 3, 1),
       ('product_26', 'Some description of product', 2, 3, 1),
       ('product_27', 'Some description of product', 243, 3, 1),
       ('product_28', 'Some description of product', 2, 3, 1),
       ('product_29', 'Some description of product', 63, 3, 1),
       ('product_30', 'Some description of product', 23, 4, 1),
       ('product_31', 'Some description of product', 73, 4, 1),
       ('product_32', 'Some description of product', 223, 4, 1);

# DELETE FROM categories WHERE id=2