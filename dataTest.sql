create index idx_tbReceiptDetailID on tbReceiptDetail(id);
drop index idx_tbReceiptDetailID on tbReceiptDetail;

insert into tbUser(nome, usuário, senha, perfil, estado) values
("Ramadan Ibraimo", "ramad", "123", "user", true),
("Ramadan Ismael", "ramaan", "123", "admin", true),
("Ramadan", "raan", "123", "admin", true),
("Ibraimo Ismael", "ramada", "123", "admin", true),
("Ismael", "raadan", "123", "admin", true),
("braimo Ismael", "raman", "123", "admin", true),
("Ramadan Ibraim", "madan", "123", "admin", true),
("Ramadansdf", "ramadanfgj", "123", "admin", true),
("Ramadan Ibrafghty", "ramagfhdan", "123", "admin", true),
("Ramadan Ibraimo Ismtt", "ramyadan", "123", "admin", true),
("Ramadan Ibraimo xcvIsmael", "ramadadvn", "123", "admin", true),
("Ramadan Ibraimovcbmnh Ismael", "rahgmadan", "123", "admin", true),
("Ramadan Ibraimo Ismatyel", "rasfghmadan", "123", "admin", true),
("Ramadan Ibraimo Ijjjsmael", "radfmadan", "123", "admin", true),
("Ramadan Ibraimo nmIsmael", "ramgbadan", "123", "admin", true),
("Ramadan Ibraimo HIsmael", "ramadzxan", "123", "admin", true),
("Ramadan Ibraimo ISDGsmael", "ramadsdan", "123", "admin", true),
("Ramadan Ibraimo I12smael", "ramadean", "123", "admin", true),
("Ramadan Ibraimo IASDsmael", "ramadaqn", "123", "admin", true)
;
insert into tbUser(nome, usuário, senha, perfil, estado) values
("Ramadan Admin 1", "ramAdmin1", "123456", "admin", true),
("Ramadan Admin 0", "ramAdmin0", "123456", "admin", false),
("Ramadan User 1", "ramUser1", "123456", "user", true),
("Ramadan User 0", "ramUser0", "123456", "user", false)
;

select nome, usuário from tbUser where
nome = "Ramadan Ibraimo Ismael" and
usuário = "ramadan";
select * from tbUser;
drop table tbUser;
DELETE FROM tbUser where ID = 1;

insert into tbUser_controller(nome, usuário, perfil) values("Zeeshan Khan", "khan", "user");
select * from tbUser_controller;
drop table tbUser_controller;

call Proced_tbUser_logs_Inserir("ramadan", "admin", "acessando o sistema");
select * from tbUser_logs order by ULTIMO_LOGIN desc;
drop procedure Proced_tbUser_logs_Inserir;
drop table tbUser_logs;

select * from tbidioma;
update tbidioma set ESTADO = false where ID = 1;	/*INGLÊS*/
update tbidioma set ESTADO = true where ID = 2;		/*PORTUGUÊS*/

update tbidioma set ESTADO = true where ID = 1;		/*INGLÊS*/
update tbidioma set ESTADO = false where ID = 2;	/*PORTUGUÊS*/

select * from tbTema;
update tbTema set ESTADO = false where ID = 1;		/*CLARO*/
update tbTema set ESTADO = true where ID = 2;		/*ESCURO*/

update tbTema set ESTADO = true where ID = 1;		/*CLARO*/
update tbTema set ESTADO = false where ID = 2;		/*ESCURO*/


select * from tbReceiptDetail;
drop table tbReceiptDetail;
INSERT INTO tbReceiptDetail
(reciboNr, tipo, estudanteID, exmo_sr, valor, desconto, valorPago, quantia, metodo, dia, mes, ano, horas)
VALUES("000123", "Mensalidade", "EWE-213434", "Ibraimo Abdula", 250, 0, 200, "Duzentos e Cinquenta Meticais", "E-Mola", "09", "Novembro", 2024, "13:23:01");