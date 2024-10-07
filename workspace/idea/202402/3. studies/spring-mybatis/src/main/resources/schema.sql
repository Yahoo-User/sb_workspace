DROP TABLE IF EXISTS city;

CREATE TABLE city (
    id INT PRIMARY KEY auto_increment,
    name VARCHAR,
    state VARCHAR,
    country VARCHAR
);

DROP TABLE IF EXISTS hotel;

CREATE TABLE hotel (
    city INT REFERENCES city(id),
    name VARCHAR,
    address VARCHAR,
    zip VARCHAR
);

