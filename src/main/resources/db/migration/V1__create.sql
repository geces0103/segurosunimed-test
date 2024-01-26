CREATE TABLE customer
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    gender    VARCHAR(50) NOT NULL
);

INSERT INTO customer (name, email, gender) VALUES ('Homem Aranha', 'aranha@vingadores.com', 'M');
INSERT INTO customer (name, email, gender) VALUES ('Thor', 'thor@vingadores.com', 'M');
INSERT INTO customer (name, email, gender) VALUES ('Viuva Negra', 'viuva@vingadores.com', 'F');
INSERT INTO customer (name, email, gender) VALUES ('Namor', 'namor@vingadores.com', 'M');
INSERT INTO customer (name, email, gender) VALUES ('Gamora', 'gamora@vingadores.com', 'F');


CREATE TABLE address
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    cep           VARCHAR(255) NOT NULL,
    logradouro    VARCHAR(255) NOT NULL,
    complemento   VARCHAR(50) NOT NULL,
    bairro        VARCHAR(50) NOT NULL,
    localidade    VARCHAR(50) NOT NULL,
    uf            VARCHAR(50) NOT NULL,
    ibge          VARCHAR(50) NOT NULL,
    gia           VARCHAR(50) NOT NULL,
    ddd           VARCHAR(50) NOT NULL,
    siafi         VARCHAR(50) NOT NULL,
    id_customer   INT
);

ALTER TABLE address ADD FOREIGN KEY (id_customer) REFERENCES customer (id);