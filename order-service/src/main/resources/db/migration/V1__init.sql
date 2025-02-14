CREATE TABLE T_orders (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `orderNumber` VARCHAR(255) DEFAULT NULL,
    `skuCode` VARCHAR(255),
    `price` DECIMAL(19,2),
    `quantity` INT(11),
    PRIMARY KEY (`id`)
);