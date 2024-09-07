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
    FOREIGN KEY (endereco_cobranca_id) REFERENCES endereco (id) ON UPDATE NO ACTION ON DELETE CASCADE,
    FOREIGN KEY (endereco_domiciliar_id) REFERENCES endereco (id) ON UPDATE NO ACTION ON DELETE CASCADE
);