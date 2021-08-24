create table avaliacao (
	id bigint not null auto_increment,
	nota decimal(10,2),
    peso decimal(10,2) not null,
	data datetime not null,
    aluno_id bigint,
    disciplina_id bigint,
	
	primary key (id)
) engine=InnoDB default charset=utf8;

create table disciplina (
	id bigint not null auto_increment,
	nome varchar(80) not null,
    professor_id bigint,
    turma_id bigint,
	
	primary key (id)
) engine=InnoDB default charset=utf8;

CREATE TABLE historico_medias_disciplinas (
  id bigint not null auto_increment,
  media decimal(10,2),
  peso_acumulado decimal(10,2),
  aluno_id bigint not null,
  disciplina_id bigint not null,
  primary key (id)
) engine=InnoDB default charset=utf8;
