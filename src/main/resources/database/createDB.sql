CREATE TABLE IF NOT EXISTS products
(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(100) NOT NULL
);

-- TRUNCATE TABLE products;

CREATE TABLE IF NOT EXISTS prices
(
    id     BIGSERIAL  PRIMARY KEY,
    price  SERIAL,
    date   DATE,
    product_id   BIGSERIAL   REFERENCES products (id)
);