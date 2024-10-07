DELETE FROM city;
DELETE FROM hotel;

INSERT INTO city (name, state, country) VALUES ('San Francisco', 'CA', 'US');
INSERT INTO hotel (city, name, address, zip) VALUES (1, 'Las Vegas Hotel', 'address', 'zip001');
