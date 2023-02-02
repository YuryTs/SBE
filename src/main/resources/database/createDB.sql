CREATE TABLE IF NOT EXISTS products
(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(100) NOT NULL
);

-- DROP TABLE  product

CREATE TABLE IF NOT EXISTS priceOfProducts
(
    id     BIGSERIAL  PRIMARY KEY,
    price  SERIAL,
    date   DATE,
    product_id   BIGSERIAL   REFERENCES products (id)
);