CREATE TABLE escritor (
    id SERIAL PRIMARY KEY,
    identificacion VARCHAR(255) NOT NULL UNIQUE,  -- Restricción UNIQUE
    nombre VARCHAR(255) NOT NULL
);
