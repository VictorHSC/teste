CREATE TABLE IF NOT EXISTS agendamento
(
    id serial NOT NULL PRIMARY KEY,
    cliente_id integer NOT NULL,
    data date NOT NULL,
    hora time without time zone NOT NULL,
    observacao text,
    FOREIGN KEY (cliente_id) REFERENCES cliente (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);