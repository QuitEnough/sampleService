CREATE TABLE products.taxonomy(
    seller_id VARCHAR NOT NULL,
    taxonomy_id VARCHAR NOT NULL,
    value JSONB NOT NULL,
    PRIMARY KEY (seller_id, taxonomy_id)
);