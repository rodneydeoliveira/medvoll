    create table pacientes (
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    nome varchar(100) not null,
    email varchar(100) unique not null,
    telefone varchar(20) not null,
    cpf varchar(20) not null,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf varchar(2) not null,
    cidade varchar(100) not null
 )