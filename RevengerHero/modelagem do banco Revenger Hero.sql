CREATE database dbRevenge;
USE dbRevenge;

CREATE TABLE mundo(PK_mundo int(4) primary key,
				  camX int(4) not null,
				  camY int(4) not null,
				  jogadorX int(4) not null,
			      jogadorY int(4) not null,
				  nivel int(2)not null);

CREATE TABLE jogador(PK_jogador int (4)  primary key,
                     FK_classe int(4)not null,
                     FK_mundo int(4) not null,
                     FK_sexo int (4) not null,
                     nome varchar(10) not null,
					 pontos_restantes int(4) not null,
                     estamina int (5) not null,
                     forca int (5) not null,
					 inteligencia int(5) not null,
					 defesa int(5) not null);

CREATE TABLE sexo(PK_sexo int (4) auto_increment primary key,
                  nome varchar(6));

CREATE TABLE classe(PK_classe int(4)  auto_increment primary key,
                    nome varchar(10));



CREATe TABLE inimigo(PK_inimigo int(4) auto_increment primary key,
                     FK_tipo_inimigo int(4) not null,
                     nome varchar(15) not null,
                     vida int(5) not null,
                     forca int (5) not null,
					 defesa int(5) not null);

CREATE TABLE tipo_inimigo(PK_tipo_inimigo int(4) auto_increment primary key,
                          nome varchar(10) not null);

CREATE TABLE registro_inimigo(PK_registro_inimigo int(4) primary key,
                              FK_mundo int(4) not null,
                              FK_inimigo int(4) not null,
                              status_morte boolean); 

alter table jogador add constraint FK_sexo foreign key (FK_sexo) references sexo(PK_sexo);
alter table jogador add constraint FK_classe foreign key (FK_classe) references classe(PK_classe);
alter table registro_inimigo add constraint FK_inimigo foreign key (FK_inimigo) references inimigo(PK_inimigo);
alter table registro_inimigo add constraint FK_mundo foreign key (FK_mundo) references mundo(PK_mundo);
alter table inimigo add constraint FK_tipo_inimigo foreign key (FK_tipo_inimigo) references tipo_inimigo(PK_tipo_inimigo);

/*Inserções no banco de dados entidade sexo*/
INSERT INTO sexo (nome) VALUES("MALE");
INSERT INTO sexo (nome) VALUES("FEMALE");
/*Inserções no banco de dados entidade classe*/
INSERT INTO classe (nome) value("Knight");
INSERT INTO classe (nome) value("Crusader");
INSERT INTO classe (nome) value("Wizard");
INSERT INTO classe (nome) value("Priest");
/*Inserções no banco de dados entidade tipo de inimigo*/
INSERT INTO tipo_inimigo(nome)values("Monstro");
INSERT INTO tipo_inimigo(nome)values("Sub Boss");
INSERT INTO tipo_inimigo(nome)values("BossFinal");
/*Inserções no banco de dados entidade inimigos*/

INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(1,"01 Undead", 2500, 90, 12);
INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(1,"02 Pirate", 2000, 70, 10);
INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(1,"03 Aliot", 3200, 120, 5);
INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(1,"04 Belzebull", 3800, 170, 35);

INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(1,"05 Turtle", 4500, 150, 60);
INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(1,"06 Invoker", 4200, 280, 2);
INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(1,"07 Incubus", 4700, 225, 10);
INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(1,"08 Angel", 5250, 300, 15);

INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(1,"09 Yun", 6200, 380, 20);
INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(1,"10 Zenfi", 7500, 420, 1);
INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(1,"11 Loki", 8500, 480, 25);
INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(1,"12 Baphometh", 9250, 540, 2);

INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(1,"13 Blood", 10500, 585, 20);
INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(1,"14 Wolf", 11000, 650, 10);
INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(1,"15 Shinobi", 12250, 725, 25);
INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(1,"16 Bijou", 15000, 750, 40);

INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(2,"SB01 Dracula", 4500, 250, 5);
INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(2,"SB02 Giant", 6500, 325, 5);
INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(2,"SB03 Samurai", 12000, 580, 25);
INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(2,"SB04 Valkyr", 18500, 780, 15);

INSERT INTO inimigo(FK_tipo_inimigo,nome,vida,forca,defesa)values(3,"Dark Lord", 25000, 650, 5);


