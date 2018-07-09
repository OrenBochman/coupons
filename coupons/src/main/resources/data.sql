--INSERT INTO user (name,password,role) VALUES ('Alice','Pass123','ADMIN');
--INSERT INTO user (name,password,role) VALUES ('Bill','Pass123','CUSTOMER');
--INSERT INTO user (name,password,role) VALUES ('Charlie','Pass123','CUSTOMER');
--INSERT INTO user (name,password,role) VALUES ('Duke','Pass123','CUSTOMER');
--INSERT INTO user (name,password,role) VALUES ('Elvis','Pass123','CUSTOMER');
--INSERT INTO user (name,password,role) VALUES ('Freddie','Pass123','CUSTOMER');
--INSERT INTO user (name,password,role) VALUES ('Gloria','Pass123','CUSTOMER');
--INSERT INTO user (name,password,role) VALUES ('Hapoalim','Pass123','COMPANY');
--INSERT INTO user (name,password,role) VALUES ('IDF','Pass123','COMPANY');
--INSERT INTO user (name,password,role) VALUES ('Japanica','Pass123','COMPANY');
--INSERT INTO user (name,password,role) VALUES ('KSP','Pass123','COMPANY');
--INSERT INTO user (name,password,role) VALUES ('Leumi','Pass123','COMPANY');

INSERT INTO user (name,password,email,role) VALUES 
('Alice','Pass123','alice@abc.com','0'),
('Bill','Pass123','billbo@msn.com','1'),
('Charlie','Pass123','bird@gmail.com','1'),
('Duke','Pass123','ellington@hotmail.com','1'),
('Elvis','Pass123','elvisp@gmail.com','1'),
('Freddie','Pass123','fred@verb.com','1'),
('Gloria','Pass123','glory@text.tv','1'),
('Hapoalim','Pass123','bank@hapoalim.co.il','2'),
('Intel','Pass123','andy@intel.com','2'),
('Google','Pass123','frank@google.com','2'),
('Cellcom','Pass123','postmaster@cellcom.co.il','2'),
('Toyota','Pass123','watoto@toyota.com','2');

INSERT INTO `company` (`company_name`,`email`) 
VALUES ('Intel', 'boss@intel.com'),
       ('Google',  'boss@gmail.com'),
       ('Cellcom',  'boss@cellcom.com'),
       ('Toyota',  'boss@toyota.co.il'),
       ('Mako',  'boss@mako.net'),
       ('Zer4U',  'boss@zer4you.com'),
       ('RavBarich',  'boss@ravbariach.com'),
       ('BaitBalev',  'boss@baitbalev.com'),
       ('Beeper', 'boss@beeper.com');

INSERT INTO `customer`(`customer_name`) 
VALUES 
('Alice' ),
('Billbo' ),
('Charlie'),
('Duncan'),
('Eufrat'),
('Fafhard'),
('Grayjoy'),
('Haidie'),
('Ian'),
('Jackline'),
('Kyle'),
('Lewis');

INSERT INTO `coupon` (`company_id`,`title`,   `start_date`,`end_date`  ,`amount`,`coupon_type`,`message`,`price`,`image`) 
VALUES 
('1','Happy Meal',      '2017-01-1 03:14:07','2018-08-11 03:14:07','1000','FOOD',       'Buy it to try it','12.44', 'IMAGE1'),
('2','Free Dessert',    '2017-01-1 03:14:07','2018-08-12 03:14:07','2000','RESTURANT',  'Buy it to try it','14.33', 'IMAGE2'),
('3','Spa for Two',     '2017-01-1 03:14:07','2018-08-13 03:14:07','1000','SPORTS',     'Buy it to try it','10.00','IMAGE3'),
('4','Weekend on Mars', '2017-01-1 03:14:07','2018-08-14 03:14:07','2000','TRAVELING',  'Buy it to try it','13.99', 'IMAGE4'),
('5','Omega 3 Life',    '2017-01-1 03:14:07','2018-08-15 03:14:07','1000','HEALTH',     'Buy it to try it','14.99', 'IMAGE5'),
('6','Tents 2+1',       '2017-01-1 03:14:07','2018-08-16 03:14:07','2000','CAMPING',    'Buy it to try it','12.99', 'IMAGE6'),
('7','All you can Eat', '2017-01-1 03:14:07','2018-08-17 03:14:07','1000','FOOD',       'Buy it to try it','19.99', 'IMAGE7'),
('8','Wireless Charger','2017-01-1 03:14:07','2018-08-18 03:14:07','2000','ELECTRICITY','Buy it to try it','11.99', 'IMAGE8'),
('9','Multi Vitamin',   '2017-01-1 03:14:07','2018-08-19 03:14:07','1000','HEALTH',     'Buy it to try it','99.00', 'IMAGE9')
;

INSERT INTO `customer_coupon` (`customer_id`, `coupon_id`,`amount`) 
VALUES 
('1','1','20'),
('2','3','10'),
('3','4','20'),
('4','5','20'),
('5','5','20');