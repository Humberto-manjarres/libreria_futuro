CREATE TABLE escritor (
    id SERIAL PRIMARY KEY,
    identificacion VARCHAR(255) NOT NULL UNIQUE,  -- Restricción UNIQUE
    nombre VARCHAR(255) NOT NULL
);

CREATE TABLE categoria (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);


CREATE TABLE editorial (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT null,
    direccion VARCHAR(255) NOT null
);