CREATE TABLE IF NOT EXISTS endereco
(
    id serial NOT NULL PRIMARY KEY,
    cep character varying(14) NOT NULL,
    logradouro character varying(255) NOT NULL,
    numero character varying(10) NOT NULL,
    bairro character varying(255) NOT NULL,
    estado character varying(2) NOT NULL,
    cidade character varying(255) NOT NULL
);