CREATE TABLE IF NOT EXISTS products
(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS prices
(
    id     BIGSERIAL  PRIMARY KEY,
    price  NUMERIC(12, 2),
    date   DATE,
    product_id   BIGSERIAL   REFERENCES products (id)
);