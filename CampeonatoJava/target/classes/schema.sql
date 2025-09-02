-- Schema em memória (H2)
CREATE TABLE IF NOT EXISTS atleta (
  ID_atleta INT PRIMARY KEY,
  Nome VARCHAR(100) NOT NULL,
  Posicao VARCHAR(50),
  Federacao VARCHAR(50),
  Data_Nasc DATE,
  Numero INT
);