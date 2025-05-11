
CREATE TABLE brands (
    brand_id int NOT NULL,
    brand_name varchar(255),
    PRIMARY KEY (brand_Id)

);

CREATE TABLE prices (
    price_id int NOT NULL,
    brand_id int NOT NULL,
    start_date TIMESTAMP DEFAULT  CURRENT_TIMESTAMP,
    end_date TIMESTAMP DEFAULT  CURRENT_TIMESTAMP,
    price_list int,
    product_id int,
    priority int,
    total_price DOUBLE(40,2),
    currency varchar(20),
    PRIMARY KEY (price_Id),
    FOREIGN KEY (brand_Id) REFERENCES brands(brand_Id)
);

INSERT INTO brands (brand_id,brand_name) VALUES (1,'Zara');

INSERT INTO prices (price_id,brand_id,start_date, end_date,price_list,product_id,priority,total_price,currency) VALUES (1,1,'2020-06-14 00.00.00','2020-12-31 23.59.59',1,35455,0,35.50,'EUR');
INSERT INTO prices (price_id,brand_id,start_date, end_date,price_list,product_id,priority,total_price,currency) VALUES (2,1,'2020-06-14 15.00.00','2020-06-14 18.30.00',2,35455,1,25.45,'EUR');
INSERT INTO prices (price_id,brand_id,start_date, end_date,price_list,product_id,priority,total_price,currency) VALUES (3,1,'2020-06-15 00.00.00','2020-06-15 11.00.00',3,35455,1,30.50,'EUR');
INSERT INTO prices (price_id,brand_id,start_date, end_date,price_list,product_id,priority,total_price,currency) VALUES (4,1,'2020-06-15 16.00.00','2020-12-31 23.59.59',4,35455,1,38.95,'EUR');
