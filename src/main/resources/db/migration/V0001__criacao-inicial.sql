CREATE TABLE escola (
	id bigint not null AUTO_INCREMENT,
    nome varchar(80) NOT NULL,
    
    PRIMARY KEY(id)    
)engine=InnoDB default charset=utf8;

CREATE TABLE aluno (
	id bigint not null AUTO_INCREMENT,
    nome varchar(80) NOT NULL,
    matricula varchar(255) NOT NULL,
    escola_id bigint,
    turma_id bigint,
    
    PRIMARY KEY(id)    
)engine=InnoDB default charset=utf8;

CREATE TABLE professor (
	id bigint not null AUTO_INCREMENT,
    nome varchar(80) NOT NULL,
    senha varchar(255) NOT NULL,
    escola_id bigint,
    
    PRIMARY KEY(id)    
)engine=InnoDB default charset=utf8;

CREATE TABLE turma (
	id bigint not null AUTO_INCREMENT,
    nome varchar(80) NOT NULL,
    ano datetime NOT NULL,
    turno bigint(16),
    
    PRIMARY KEY(id)    
)engine=InnoDB default charset=utf8;