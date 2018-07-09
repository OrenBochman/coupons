DROP DATABASE CouponsDataBase;
CREATE DATABASE CouponsDataBase;

USE CouponsDataBase;

CREATE TABLE `user` (
  `id`         BIGINT NOT NULL AUTO_INCREMENT,
  `name`       VARCHAR(100)  NOT NULL,
  `email`      VARCHAR(100)  NOT NULL,
  `password`   VARCHAR(100) NOT NULL,
 -- `role`       ENUM('ADMIN','CUSTOMER','COMPANY') DEFAULT 'CUSTOMER' NOT NULL ,
  `role`       INT DEFAULT 1 NOT NULL ,
  PRIMARY KEY (`id`));
  
CREATE TABLE `company` (
  `id`           bigint(20) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) NOT NULL UNIQUE,
  `email`        varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `customer` (
  `id`        bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `coupon` (
  `id`         bigint(20) NOT NULL AUTO_INCREMENT,            -- The Coupon ID
  `company_id` bigint(20) NOT NULL,
  `title`      varchar(255) NOT NULL,                         -- Short coupon description
  `start_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,  -- Creation date
  `end_date`   datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,  -- Expiry Date
  `amount`     int(10) NOT NULL,                              -- Amount of coupons created
--`type`       ENUM('RESTURANT','ELECTRICITY','FOOD','HEALTH','SPORTS','CAMPING','TRAVELING') NOT NULL,
  `coupon_type` varchar(255) NOT NULL,
  `message`    varchar(255) NOT NULL,                         -- Coupon text
  `price`      bigint(20) NOT NULL,                           -- Coupon sales price
  `image`      varchar(255) NOT NULL,                         -- Coupons url of coupons image
   PRIMARY KEY (`id`),
   CONSTRAINT  `compid@coupon_exists` FOREIGN KEY (`company_id`) REFERENCES company(`id`)
) ;

CREATE TABLE `customer_coupon` (
  `customer_id` bigint(20) NOT NULL,                  -- The Customer ID (Forign key for Customer table)
  `coupon_id` 	bigint(20) NOT NULL,                  -- The Compnay ID  (Forign key for Coupon table)
  `amount`    INT NOT NULL,						      -- The amount of coupons owned by the user
  PRIMARY KEY (`customer_id`,`coupon_id`) ,   
  CONSTRAINT  `customer_id_exists` FOREIGN KEY (`customer_id`)   REFERENCES customer(`id`)  ON DELETE NO ACTION ON UPDATE NO ACTION ,  
  CONSTRAINT  `coupon_id_exists`   FOREIGN KEY (`coupon_id`) 	REFERENCES coupon(`id`)  ON DELETE NO ACTION ON UPDATE NO ACTION          
);

