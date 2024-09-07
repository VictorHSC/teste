CREATE TYPE tipo_usuario AS ENUM ('PADRAO', 'SECRETARIA', 'PROFISSIONAL_SAUDE');

CREATE TABLE IF NOT EXISTS usuario
(
    id serial NOT NULL PRIMARY KEY,
    nome character varying(50) NOT NULL UNIQUE,
    senha character varying(255) NOT NULL,
    tipo tipo_usuario NOT NULL
);