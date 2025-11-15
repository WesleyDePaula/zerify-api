CREATE TABLE usuario (
    id UUID PRIMARY KEY NOT NULL,
    nome  VARCHAR(255),
    email VARCHAR(255),
    senha VARCHAR(255),
    plano VARCHAR(255)
);