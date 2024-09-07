CREATE TYPE situacao AS ENUM ('EM_ANDAMENTO', 'CONCLUIDO');

CREATE TABLE IF NOT EXISTS consulta
(
    id serial NOT NULL PRIMARY KEY,
    agendamento_id integer NOT NULL,
    situacao situacao NOT NULL,
    anotacoes text NOT NULL,
    pontos_atencao text NOT NULL,
    FOREIGN KEY (agendamento_id) REFERENCES agendamento (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);