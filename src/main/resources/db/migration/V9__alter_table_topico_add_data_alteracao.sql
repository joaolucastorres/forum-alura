ALTER TABLE topico ADD COLUMN data_alteracao date;
INSERT INTO topico(titulo, mensagem, data_criacao, status, curso_id, autor_id)
VALUES ('Duvida Kotlin', 'Variaveis no Kotlin', '2023-08-29T16:22:10.1793271', 'NAO_RESPONDIDO', 1, 1 );