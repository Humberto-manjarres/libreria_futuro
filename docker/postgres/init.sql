CREATE TABLE IF NOT EXISTS escritor (
    id SERIAL PRIMARY KEY,
    identificacion VARCHAR(255) NOT NULL UNIQUE,  -- Restricción UNIQUE
    nombre VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS categoria (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS editorial (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT null,
    direccion VARCHAR(255) NOT null
);


CREATE TABLE IF NOT EXISTS libro (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT null,
    numero_paginas VARCHAR(255) NOT null,
    id_escritor VARCHAR(255) NOT null,
    descripcion VARCHAR(255) NOT null,
    id_categoria VARCHAR(255) NOT null,
    id_editorial VARCHAR(255) NOT null
);


--URL Mock Server
--https://eb107633-5226-4b0c-ae5a-a070da6705c3.mock.pstmn.io/caratula/libro/