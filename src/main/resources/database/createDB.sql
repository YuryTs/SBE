CREATE TABLE IF NOT EXISTS products
(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(100) NOT NULL
);

-- TRUNCATE TABLE products;

CREATE TABLE IF NOT EXISTS prices
(
    id     BIGSERIAL  PRIMARY KEY,
    price  NUMERIC(12, 2),
    date   DATE,
    products_id   BIGSERIAL   REFERENCES products (id)
);