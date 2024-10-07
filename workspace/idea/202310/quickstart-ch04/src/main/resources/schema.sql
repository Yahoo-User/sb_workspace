DROP TABLE IF EXISTS person;

CREATE TABLE person (
    id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);

show columns from person;

DROP TABLE IF EXISTS phone;

CREATE TABLE phone (
    id BIGINT NOT NULL,
    number VARCHAR(255) NOT NULL,
    person_id BIGINT,   -- FK

    PRIMARY KEY (id)
);

ALTER TABLE phone ADD CONSTRAINT person_id_fk
FOREIGN KEY (person_id) REFERENCES person(id);

SHOW COLUMNS FROM phone;

