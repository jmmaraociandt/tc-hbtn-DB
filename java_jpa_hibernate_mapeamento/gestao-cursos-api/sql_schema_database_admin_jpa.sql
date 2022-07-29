-- TABLE
CREATE TABLE alunos (id  integer, email varchar(255), matricula varchar(255), nascimento date, nome_completo varchar(255), primary key (id));
CREATE TABLE cursos (id  integer, nome varchar(255), sigla varchar(255), material_curso_id bigint, professor_id bigint, primary key (id));
CREATE TABLE curso_aluno (aluno_id bigint not null, curso_id bigint not null, primary key (aluno_id, curso_id));
CREATE TABLE enderecos (id  integer, bairro varchar(255), cep integer, cidade varchar(255), endereco varchar(255), estado varchar(255), logradouro varchar(255), numero varchar(255), aluno_id bigint, primary key (id));
CREATE TABLE materiais_curso (id  integer, url varchar(255), primary key (id));
CREATE TABLE professores (id  integer, email varchar(255), matricula varchar(255), nome_completo varchar(255), primary key (id));
CREATE TABLE telefones (id  integer, ddd varchar(255), numero varchar(255), aluno_id bigint, primary key (id));
 
-- INDEX
 
-- TRIGGER
 
-- VIEW
 
