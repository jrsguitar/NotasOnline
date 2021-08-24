alter table aluno add constraint fk_aluno_escola foreign key (escola_id) references escola (id);
alter table professor add constraint fk_professor_escola foreign key (escola_id) references escola (id);
alter table aluno add constraint fk_aluno_turma foreign key (turma_id) references turma (id);
alter table avaliacao add constraint fk_avaliacao_aluno foreign key (aluno_id) references aluno (id);
alter table avaliacao add constraint fk_avaliacao_disciplina foreign key (disciplina_id) references disciplina (id);
alter table disciplina add constraint fk_disciplina_professor foreign key (professor_id) references professor (id);
alter table disciplina add constraint fk_disciplina_turma foreign key (turma_id) references turma (id);
alter table historico_medias_disciplinas add constraint fk_historico_aluno foreign key (aluno_id) references aluno (id);
alter table historico_medias_disciplinas add constraint fk_historico_disciplina foreign key (disciplina_id) references disciplina (id);