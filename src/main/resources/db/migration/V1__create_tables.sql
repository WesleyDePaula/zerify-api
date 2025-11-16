CREATE TABLE usuario(
    id    UUID PRIMARY KEY NOT NULL,
    nome  VARCHAR(255),
    email VARCHAR(255),
    senha VARCHAR(255),
    plano VARCHAR(255)
);

CREATE TABLE despensa(
    id         UUID PRIMARY KEY,
    nome       VARCHAR(255) NOT NULL,
    usuario_id UUID,
    FOREIGN KEY (usuario_id) REFERENCES usuario (id) ON DELETE SET NULL
);

CREATE TABLE alimento(
    id            UUID PRIMARY KEY,
    nome          VARCHAR(255) NOT NULL,
    categoria     VARCHAR(255) NOT NULL,
    quantidade    INT          NOT NULL,
    unidade       VARCHAR(255) NOT NULL,
    data_validade DATE         NOT NULL,
    situacao      VARCHAR(255) NOT NULL,
    despensa_id   UUID,
    FOREIGN KEY (despensa_id) REFERENCES despensa (id) ON DELETE SET NULL
);