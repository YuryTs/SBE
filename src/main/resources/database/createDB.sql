CREATE TABLE IF NOT EXISTS Products
(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(100) NOT NULL
);

-- DROP TABLE  product

CREATE TABLE IF NOT EXISTS Prices
(
    id     BIGSERIAL  PRIMARY KEY,
    price  SERIAL,
    date   DATE,
    product_id   BIGSERIAL   REFERENCES products (id)
);