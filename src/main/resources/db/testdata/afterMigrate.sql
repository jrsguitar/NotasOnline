set foreign_key_checks = 0;

delete from aluno;
delete from aluno_avaliacoes;
delete from avaliacao;
delete from disciplina;
delete from escola;
delete from historico_medias_disciplinas;
delete from professor;
delete from professor_turma;
delete from turma;
delete from turma_alunos; 

set foreign_key_checks = 1;

alter table aluno auto_increment = 1;
alter table avaliacao auto_increment = 1;
alter table disciplina auto_increment = 1;
alter table escola auto_increment = 1;
alter table historico_medias_disciplinas auto_increment = 1;
alter table professor auto_increment = 1;
alter table turma auto_increment = 1;
