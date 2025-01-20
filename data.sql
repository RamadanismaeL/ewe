CREATE DATABASE db_englishwithexperts;
USE db_englishwithexperts;

-- TABELA USUÁRIO
create table tbUser (
ID int unsigned primary key auto_increment not null,
NOME varchar(50) unique not null,
USUÁRIO varchar(40) unique not null,
SENHA varchar(200) not null,
PERFIL enum('admin', 'pro', 'user') not null,
ESTADO boolean default false not null,
DATA_CRIAÇÃO datetime default current_timestamp
) engine=InnoDB default charset=utf8mb4;
create index idx_tbUser_nome on tbUser(NOME);
create index idx_tbUser_usuario on tbUser(USUÁRIO);
create index idx_tbUser_perfil on tbUser(PERFIL);
insert into tbUser(nome, usuário, senha, perfil, estado) values("Ramadan Ibraimo Ismael", "ramadan", "123", "admin", true);
insert into tbUser(nome, usuário, senha, perfil, estado) values("Danny Abdula", "danny", "123", "user", true);

-- TABELA USUÁRIO_CONTROLLER
create table tbUser_controller (
ID int unsigned primary key auto_increment,
NOME varchar(50) unique not null,
USUÁRIO varchar(40) unique not null,
PERFIL enum('admin', 'pro', 'user') not null,
DATA_LOGIN datetime default current_timestamp,
constraint fk_tbuser_controller_nome foreign key(NOME) references tbUser(NOME),
constraint fk_tbuser_controller_usuario foreign key(USUÁRIO) references tbUser(USUÁRIO)
) engine=InnoDB default charset=utf8mb4;
create index idx_tbUser_controller_nome on tbUser_controller(NOME);
create index idx_tbUser_controller_usuario on tbUser_controller(USUÁRIO);
create index idx_tbUser_controller_perfil on tbUser_controller(PERFIL);
insert into tbUser_controller(nome, usuário, perfil) values("Ramadan Ibraimo Ismael", "ramadan", "admin");

-- TABELA IDIOMA
create table tbidioma (
ID int unsigned primary key auto_increment,
IDIOMA varchar(20) unique not null,
ESTADO boolean default false not null
) engine=InnoDB default charset=utf8mb4;
create index idx_tbidioma_idioma on tbidioma(IDIOMA);
insert into tbidioma(IDIOMA, ESTADO) values("English", false), ("Potuguês", true);

-- TABELA TEMA
create table tbTema (
ID int unsigned primary key auto_increment,
TEMA varchar(20) unique not null,
ESTADO boolean default false not null
) engine=InnoDB default charset=utf8mb4;
create index idx_tbTema_Tema on tbTema(TEMA);
insert into tbTema(TEMA, ESTADO) values("Claro", true), ("Escuro", false);

-- RECIBO
create table tbReceiptDetail (
id int unsigned primary key auto_increment,
reciboNr varchar(50) unique not null,
tipo varchar(50) not null,
estudanteID varchar(50) unique not null,
exmo_sr varchar(50) not null,
valor decimal(10,2) not null,
desconto decimal(10,2) default 0.0,
valorPago decimal(10,2) not null,
quantia varchar(150) not null,
metodo varchar(50) not null,
dia varchar(30) not null,
mes varchar(50) not null,
ano int not null,
horas varchar(50),
dataRegistro datetime default current_timestamp
) engine=InnoDB default charset=utf8mb4;
create index idx_reciboNr on tbReceiptDetail(reciboNr);
/*
-- FORMULARIO DE INSCRICAO
CREATE TABLE tbformularioDeInscricao (
id int unsigned primary key auto_increment,
estudanteID varchar(50) unique not null,
nivel varchar(45) not null,
modalidade boolean not null,
horario varchar(45) not null,
duracao varchar(25) not null,

) engine=InnoDB default charset=utf8mb4;

-- MENSALIDADE
CREATE TABLE tbMensalidade (
id int unsigned primary key auto_increment
) engine=InnoDB default charset=utf8mb4;
*/
DELIMITER //
create procedure Proced_tbUser_logs_Inserir (in usernameP varchar(40), in profileP enum('admin', 'pro', 'user'), in descriptionP varchar(100))
begin
	declare erro_sql tinyint default false;
    declare continue handler for sqlexception set erro_sql=true;
    start transaction;
    insert into tbUser_logs(USUÁRIO, DESCRICAO, PERFIL) values(usernameP, descriptionP, profileP);
    if erro_sql or usernameP is null or descriptionP is null then
		rollback;
        select "Ocorreu um Erro no Proced_tbUser_logs_Inserir" as mensagem;
	else
		commit;
        select "Operação concluída com susseco!" as mensagem;
	end if;
end;
//
DELIMITER ;