CREATE TABLE alimento (
    id UUID PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL,
    quantidade INT NOT NULL,
    unidade VARCHAR(255) NOT NULL, 
    data_validade DATE NOT NULL,
    situacao VARCHAR(255) NOT NULL,
    despensa_id UUID,
    FOREIGN KEY (despensa_id) REFERENCES despensa(id) ON DELETE SET NULL
);
