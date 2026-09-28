CREATE TABLE product (
                         id UUID PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         price DOUBLE PRECISION NOT NULL,
                         category VARCHAR(255),
                         brand VARCHAR(255),
                         in_stock BOOLEAN
);