CREATE TABLE products.inventory(
    seller_id VARCHAR NOT NULL,
    product_id VARCHAR NOT NULL,
    value JSONB NOT NULL,
    PRIMARY KEY (seller_id, product_id)
);