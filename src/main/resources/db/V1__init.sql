CREATE TABLE shop (
    id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(191) NOT NULL,
    email VARCHAR(191) NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;

CREATE TABLE product (
    id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    shop_id BIGINT(20) UNSIGNED NOT NULL,
    name VARCHAR(191) NOT NULL,
    price DECIMAL(19,2) DEFAULT 0.00 NOT NULL,
    currency SMALLINT(3) UNSIGNED ZEROFILL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_shop_id FOREIGN KEY ( shop_id )
                REFERENCES  shop(id)
                ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;


INSERT INTO shop (id, name, email) VALUES (1, 'Shop - 1', 'shop01@test.com');
INSERT INTO shop (id, name, email) VALUES (2, 'Shop - 2', 'shop02@test.com');
INSERT INTO product (shop_id, name, price, currency) VALUES (1, 'Apple', 5.0, 344);
INSERT INTO product (shop_id, name, price, currency) VALUES (1, 'Orange', 6.2, 344);
INSERT INTO product (shop_id, name, price, currency) VALUES (2, 'Pen', 10.0, 344);
INSERT INTO product (shop_id, name, price, currency) VALUES (2, 'Pencil', 12.5, 344);