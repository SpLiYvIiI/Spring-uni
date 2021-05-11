CREATE TABLE weather (
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    country varchar(255),
    city varchar(255),
    temperature integer,
    windy boolean
);

INSERT INTO weather (country, city, temperature, windy) VALUES ('Georgia', 'Tbilisi',25,True);
INSERT INTO weather (country, city, temperature, windy) VALUES ('USA', 'New York',18,False);
INSERT INTO weather (country, city, temperature, windy) VALUES ('England', 'London',15,False);
INSERT INTO weather (country, city, temperature, windy) VALUES ('Japan', 'Tokyo',16,False);
INSERT INTO weather (country, city, temperature, windy) VALUES ('Russia', 'moscow',-9231,True);
