CREATE TABLE users (
    id numeric(19) PRIMARY KEY NOT NULL,
    username varchar NOT NULL UNIQUE,
    password varchar NOT NULL,
    role varchar NOT NULL
);