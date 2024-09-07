-- Criação dos ENUMs, se necessário
CREATE TYPE situacao AS ENUM ('EM_ANDAMENTO', 'CONCLUIDO');
CREATE TYPE tipo_usuario AS ENUM ('SECRETARIA', 'PROFISSIONAL_SAUDE');

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

CREATE TABLE IF NOT EXISTS cliente
(
    id serial NOT NULL PRIMARY KEY,
    cpf character varying(14) NOT NULL UNIQUE,
    endereco_cobranca_id integer NOT NULL,
    endereco_domiciliar_id integer NOT NULL,
    nome character varying(255) NOT NULL,
    sobrenome character varying(255) NOT NULL,
    data_nascimento date NOT NULL,
    telefone character varying(15) NOT NULL,
    whatsapp character varying(15),
    email character varying(255) NOT NULL,
    FOREIGN KEY (endereco_cobranca_id) REFERENCES endereco (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    FOREIGN KEY (endereco_domiciliar_id) REFERENCES endereco (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS agendamento
(
    id serial NOT NULL PRIMARY KEY,
    cliente_id integer NOT NULL,
    data date NOT NULL,
    hora time without time zone NOT NULL,
    observacao text,
    FOREIGN KEY (cliente_id) REFERENCES cliente (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS consulta
(
    id serial NOT NULL PRIMARY KEY,
    agendamento_id integer NOT NULL,
    situacao situacao NOT NULL,
    anotacoes text NOT NULL,
    pontos_atencao text NOT NULL,
    FOREIGN KEY (agendamento_id) REFERENCES agendamento (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS usuario
(
    id serial NOT NULL PRIMARY KEY,
    nome_usuario character varying(50) NOT NULL UNIQUE,
    senha character varying(255) NOT NULL,
    tipo_usuario tipo_usuario NOT NULL
);