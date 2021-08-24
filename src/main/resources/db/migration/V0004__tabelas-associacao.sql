create table aluno_avaliacoes (
    aluno_id bigint not null, 
    avaliacoes_id bigint not null,
    unique key uk_aluno_avaliacao (aluno_id, avaliacoes_id),
    constraint fk_aluno_avaliacoes foreign key (aluno_id) references aluno (id),
    constraint fk_avaliacoes_aluno foreign key (avaliacoes_id) references avaliacao (id)
)engine=InnoDB default charset=utf8;

create table professor_turma (
    professor_id bigint not null, 
    turmas_id bigint not null,
    unique key uk_professor_turma (professor_id, turmas_id),
    constraint fk_professor_tuma foreign key (professor_id) references professor (id),
    constraint fk_turma_professor foreign key (turmas_id) references turma (id)
)engine=InnoDB default charset=utf8;


