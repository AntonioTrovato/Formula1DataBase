CREATE DATABASE progetto;
USE progetto;

CREATE TABLE GARA (
  Nome varchar(50) NOT NULL,
  Citta varchar(20) NOT NULL,
  Nazione varchar(20) NOT NULL,
  N_Giri int NOT NULL,
  PRIMARY KEY (Nome)
);

create table GOMME (
	Nome	varchar(30)	not null,
	Tipo	varchar(20)	not null,
    foreign key(Nome) references GARA (Nome) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE SQUADRA (
  Nome varchar(50) NOT NULL,
  AnnoFormazione int NOT NULL,
  Punti_Squadra int,
  PRIMARY KEY (Nome)
);

CREATE TABLE PILOTA (
  Nome varchar(20) NOT NULL,
  Cognome varchar(20) NOT NULL,
  Nazionalita varchar(20) NOT NULL,
  ID char(5) NOT NULL,
  Datan date NOT NULL,
  Numero int NOT NULL,
  NomeSquadra varchar(50) NOT NULL,
  Punti_Pilota int,
  PRIMARY KEY (ID),
  FOREIGN KEY (NomeSquadra) REFERENCES SQUADRA (Nome) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE RESPONSABILE (
  Nome varchar(20) NOT NULL,
  Cognome varchar(20) NOT NULL,
  Nazione varchar(20) NOT NULL,
  ID char(5) NOT NULL,
  Datan date NOT NULL,
  NomeSquadra varchar(50) NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (NomeSquadra) REFERENCES SQUADRA (Nome) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE TECNICO (
  Nome varchar(20) NOT NULL,
  Cognome varchar(20) NOT NULL,
  Mansione varchar(20) NOT NULL,
  ID char(5) NOT NULL,
  Datan date NOT NULL,
  NomeSquadra varchar(50) NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (NomeSquadra) REFERENCES SQUADRA (Nome) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE PARTECIPA (
  NomeGara varchar(50) NOT NULL,
  NomeSquadra varchar(50) NOT NULL,
  PRIMARY KEY (NomeGara, NomeSquadra),
  FOREIGN KEY (NomeGara) REFERENCES GARA (Nome) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (NomeSquadra) REFERENCES SQUADRA (Nome) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE A_PUNTI (
	NomeGara varchar(50) NOT NULL,
    IDPilota varchar(20) NOT NULL,
    Punti int NOT NULL,
    Posizione int NOT NULL,
    PRIMARY KEY (NomeGara, IDPilota),
    FOREIGN KEY (NomeGara) REFERENCES GARA (Nome) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (IDPilota) REFERENCES PILOTA (ID) ON DELETE CASCADE ON UPDATE CASCADE
);